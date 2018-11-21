package pl.brightinventions.githubexplorer.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item.view.*
import pl.brightinventions.githubexplorer.R
import pl.brightinventions.githubexplorer.model.Issue

class IssuesAdapter(
    private val activity: IssuesActivity,
    private val listener: (Issue) -> Unit
) : RecyclerView.Adapter<IssuesAdapter.ViewHolder>() {

    private val items = mutableListOf<Issue>()

    fun setItems(newItems: List<Issue>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.title.text = item.title

        Glide.with(activity)
            .load(item.user.avatar_url)
            .into(holder.avatar)

        holder.itemView.setOnClickListener {
            listener(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.title
        val avatar: ImageView = itemView.avatar
    }

}