package com.example.mvvmarchitecture.ui.categorylist

import androidx.recyclerview.widget.DiffUtil
import com.example.mvvmarchitecture.R
import com.example.mvvmarchitecture.base.BaseRecyclerViewAdapter
import com.example.mvvmarchitecture.data.model.Category
import com.example.mvvmarchitecture.databinding.ItemCategoryBinding

/**
 * Created by Quang Nguyen on 6/29/20.
 */
class CategoryAdapter : BaseRecyclerViewAdapter<Category, ItemCategoryBinding>(object :
    DiffUtil.ItemCallback<Category>() {
    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }
}) {
    override fun getLayoutRes(viewType: Int): Int = R.layout.item_category
}