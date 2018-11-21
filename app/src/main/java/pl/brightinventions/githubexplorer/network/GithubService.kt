package pl.brightinventions.githubexplorer.network

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import pl.brightinventions.githubexplorer.model.Issue
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GithubService {

    @GET("repos/Kotlin/kotlin-koans/issues")
    fun getIssues(): Single<List<Issue>>

    companion object {
        const val baseUrl = "https://api.github.com"
        var serviceInstance: GithubService? = null

        fun getInstance(): GithubService {

            serviceInstance?.let { return it }

            val builder = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
                )
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = builder.create(GithubService::class.java)
            serviceInstance = service

            return service
        }
    }
}