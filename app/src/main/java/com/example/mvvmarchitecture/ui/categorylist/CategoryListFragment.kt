package com.example.mvvmarchitecture.ui.categorylist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.mvvmarchitecture.base.BaseFragment
import com.example.mvvmarchitecture.databinding.FragmentCategoryListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.mvvmarchitecture.R
import com.example.mvvmarchitecture.utils.widgets.SpacesItemDecoration
import kotlinx.android.synthetic.main.fragment_category_list.*

/**
 * Created by Quang Nguyen on 6/20/20.
 */
class CategoryListFragment : BaseFragment<FragmentCategoryListBinding, CategoryListViewModel>() {

    override val viewModel: CategoryListViewModel by viewModel()

    override val layoutId: Int = R.layout.fragment_category_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryAdapter = CategoryAdapter()
        val decoration = SpacesItemDecoration(context ?: return)
        recyclerCategory?.apply {
            adapter = categoryAdapter
            addItemDecoration(decoration)
        }

        viewModel.apply {
            categoryList.observe(viewLifecycleOwner, Observer {
                if (it.isNullOrEmpty()) {
                    Toast.makeText(context, "Data empty", Toast.LENGTH_SHORT).show()
                } else {
                    categoryAdapter.submitList(it.toMutableList())
                }
            })
        }
    }
}