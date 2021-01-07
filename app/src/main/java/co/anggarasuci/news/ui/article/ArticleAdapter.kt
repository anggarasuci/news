package co.anggarasuci.news.ui.article

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import co.anggarasuci.news.R
import co.anggarasuci.news.data.model.Article
import co.anggarasuci.news.util.image.ImageUtilities
import kotlinx.android.synthetic.main.item_article.view.*

class ArticleAdapter(
    private val onItemClicked: (url: String) -> Unit
) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    private var _items = mutableListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return _items.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            val model = _items[adapterPosition]
            title?.text = model.title
            description?.text = model.description
            author?.text = "Author: ${model.author}"
            ImageUtilities.loadImageUrlWithProgress(banner, model.urlToImage)
            published?.text = "---------"
            container?.setOnClickListener { onItemClicked(model.url) }
        }
    }

    fun setNewData(articles: List<Article>?) {
        clear()
        addData(articles)
    }

    fun addData(articles: List<Article>?) {
        articles?.let { _items.addAll(it) }
        notifyDataSetChanged()
    }

    fun clear() {
        _items.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.title_txt
        val description: TextView = view.description_txt
        val author: TextView = view.author_txt
        val container: ConstraintLayout = view.container
        val banner: ImageView = view.banner_iv
        val published: TextView = view.published_txt
    }
}
