package pl.brightinventions.githubexplorer.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_issues.*
import pl.brightinventions.githubexplorer.R
import pl.brightinventions.githubexplorer.model.Issue
import pl.brightinventions.githubexplorer.network.GithubService

class IssuesActivity : AppCompatActivity() {

    lateinit var adapter: IssuesAdapter

    private var request: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issues)

        adapter = IssuesAdapter(this) {
            startDetailsActivity(it)
        }

        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        fetchIssues()
    }

    private fun fetchIssues() {
        request?.dispose()

        request = GithubService.getInstance().getIssues()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val logMessage = it.take(10).joinToString("\n") { it.title }
                Log.d("IssuesActivity", "Successfully fetched data:\n$logMessage")
                adapter.setItems(it)
            }, {
                Log.e("IssuesActivity", "Could not fetch data", it)
            })
    }

    override fun onPause() {
        super.onPause()
        request?.dispose()
    }

    private fun startDetailsActivity(issue: Issue) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra(DetailsActivity.TITLE, issue.title)
            putExtra(DetailsActivity.DESCRIPTION, issue.body)
        }

        startActivity(intent)
    }
}
