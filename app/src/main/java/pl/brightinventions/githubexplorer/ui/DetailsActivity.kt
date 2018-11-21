package pl.brightinventions.githubexplorer.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_details.*
import pl.brightinventions.githubexplorer.R

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        intent?.getStringExtra(TITLE)?.let {
            detailsIssueTitle.text = it
        }

        intent?.getStringExtra(DESCRIPTION)?.let {
            detailsIssueDescription.text = it
        }
    }

    companion object {
        const val TITLE = "TITLE"
        const val DESCRIPTION = "DESCRIPTION"
    }
}
