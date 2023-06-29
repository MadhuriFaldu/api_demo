package com.practicaltask.ui.dashborad.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practicaltask.databinding.ItemCategoryLayoutBinding

class CategoryListAdapter(
    private val categoryList: ArrayList<Category>
) : RecyclerView.Adapter<CategoryListAdapter.VHolder>() {

    inner class VHolder(private val mBinding: ItemCategoryLayoutBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(position: Int, category: Category) {
            mBinding.tvCategory.text = category.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(
            ItemCategoryLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.bind(position, categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }


}