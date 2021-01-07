package co.anggarasuci.news.ui.source

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import co.anggarasuci.news.R
import co.anggarasuci.news.data.model.Source
import kotlinx.android.synthetic.main.item_source.view.*

class SourcesAdapter(
    private val onItemClicked: (id: String) -> Unit
) : RecyclerView.Adapter<SourcesAdapter.ViewHolder>() {

    private var _items = mutableListOf<Source>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_source, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return _items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            val model = _items[adapterPosition]
            name?.text = model.name
            description?.text = model.description
            categoryName?.text = model.category.capitalize().trim()
            container?.setOnClickListener { onItemClicked(model.id) }
        }
    }

    fun setNewData(sources: List<Source>) {
        clear()
        addData(sources)
    }

    fun addData(sources: List<Source>?) {
        sources?.let { _items.addAll(it) }
        notifyDataSetChanged()
    }

    fun clear() {
        _items.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.name_txt
        val description: TextView = view.description_txt
        val categoryName: TextView = view.category_txt
        val container: ConstraintLayout = view.container
    }
}
