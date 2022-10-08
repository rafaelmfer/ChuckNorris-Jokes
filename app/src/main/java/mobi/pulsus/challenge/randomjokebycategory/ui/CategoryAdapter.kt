package mobi.pulsus.challenge.randomjokebycategory.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mobi.pulsus.challenge.commons.extensions.onSingleClick
import mobi.pulsus.challenge.databinding.ItemCategoryBinding
import java.util.Locale

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var categoryAction: (category: String) -> Unit = {}

    private var list: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class CategoryViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(categoryName: String) {
            val categoryCapitalized = categoryName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            binding.tvCategory.text = categoryCapitalized
            binding.mcvCategory.onSingleClick {
                categoryAction.invoke(categoryCapitalized)
            }
        }
    }

    fun setList(list: List<String>) {
        this.list.clear()
        this.list.addAll(list)
    }

    fun setCategoryAction(action: (category: String) -> Unit) {
        categoryAction = action
    }
}