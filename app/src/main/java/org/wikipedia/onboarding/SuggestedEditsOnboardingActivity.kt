package org.wikipedia.onboarding

import android.content.Context
import android.content.Intent
import org.wikipedia.activity.SingleFragmentActivity
import org.wikipedia.onboarding.SuggestedEditsOnboardingFragment.Companion.newInstance

class SuggestedEditsOnboardingActivity : SingleFragmentActivity<SuggestedEditsOnboardingFragment>() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SuggestedEditsOnboardingActivity::class.java)
        }
    }

    override fun createFragment(): SuggestedEditsOnboardingFragment {
        return newInstance()
    }
}