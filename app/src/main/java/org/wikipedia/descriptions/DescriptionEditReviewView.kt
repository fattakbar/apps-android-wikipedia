package org.wikipedia.descriptions

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.view_description_edit_review.view.*
import org.apache.commons.lang3.StringUtils
import org.wikipedia.R
import org.wikipedia.dataclient.restbase.page.RbPageSummary
import org.wikipedia.util.L10nUtil
import org.wikipedia.util.StringUtil

class DescriptionEditReviewView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : ConstraintLayout(context, attrs, defStyle) {

    init {
        inflate(context, R.layout.view_description_edit_review, this)
        licenseView.buildLicenseNotice(false)
        licenseView.removeUnderlinesFromLinks()
    }

    val isShowing: Boolean
        get() = visibility == VISIBLE


    fun show() {
        visibility = VISIBLE
    }

    fun hide() {
        visibility = GONE
    }

    fun setPageSummary(pageSummary: RbPageSummary, description: String) {
        L10nUtil.setConditionalLayoutDirection(this, pageSummary.lang)
        articleTitle!!.text = StringUtil.fromHtml(pageSummary.displayTitle)
        articleSubtitle!!.text = StringUtils.capitalize(description)
        articleExtract!!.text = StringUtil.fromHtml(pageSummary.extractHtml)

        if (pageSummary.thumbnailUrl.isNullOrBlank()) {
            articleImage.visibility = View.GONE
            articleExtract.maxLines = ARTICLE_EXTRACT_MAX_LINE_WITHOUT_IMAGE
        } else {
            articleImage.visibility = View.VISIBLE
            articleImage.loadImage(Uri.parse(pageSummary.thumbnailUrl))
            articleExtract.maxLines = ARTICLE_EXTRACT_MAX_LINE_WITH_IMAGE
        }
    }

    companion object {
        const val ARTICLE_EXTRACT_MAX_LINE_WITH_IMAGE = 9
        const val ARTICLE_EXTRACT_MAX_LINE_WITHOUT_IMAGE = 15
    }

}
