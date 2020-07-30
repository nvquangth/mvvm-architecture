package com.example.mvvmarchitecture.ui.recipelist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.mvvmarchitecture.R
import com.example.mvvmarchitecture.base.BaseFragment
import com.example.mvvmarchitecture.databinding.FragmentRecipeListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Quang Nguyen on 7/18/20.
 */
class RecipeListFragment : BaseFragment<FragmentRecipeListBinding, RecipeListViewModel>() {

    override val viewModel: RecipeListViewModel by viewModel()

    override val layoutId: Int = R.layout.fragment_recipe_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val argument: RecipeListFragmentArgs by navArgs()

        viewModel.apply {
            recipes.observe(viewLifecycleOwner, Observer {
                viewBinding.resource = it
            })

            setCategory(argument.category)
        }
    }
}