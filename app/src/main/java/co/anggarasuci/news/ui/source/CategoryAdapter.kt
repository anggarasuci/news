package co.anggarasuci.news.ui.source

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.anggarasuci.news.R
import co.anggarasuci.news.ui.widget.CategoryView
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(
    private val onItemClicked: (String) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var selectedFilter = 0
    private var categories = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            val titleValue = categories[adapterPosition]
            labelCategory?.apply {
                isActive = selectedFilter == position
                title = titleValue

                setOnClickListener {
                    isActive = true
                    handleItemPressed(position)
                    onItemClicked(if (adapterPosition == 0) "" else titleValue)
                }
            }
        }
    }

    fun setData(models: List<String>) {
        categories = models
    }

    private fun handleItemPressed(position: Int) {
        val prevSelectedIndex = selectedFilter
        selectedFilter = position
        notifyItemChanged(prevSelectedIndex)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var labelCategory: CategoryView = view?.category_txt
    }
}
