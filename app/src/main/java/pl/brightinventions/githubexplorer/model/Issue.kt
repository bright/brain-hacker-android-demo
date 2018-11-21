package pl.brightinventions.githubexplorer.model

data class Issue(
    val id: Int,
    val number: Int,
    val title: String,
    val body: String,
    val user: User
)
