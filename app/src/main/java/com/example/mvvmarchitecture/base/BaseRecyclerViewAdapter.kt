package com.example.mvvmarchitecture.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmarchitecture.BR
import java.util.concurrent.Executors

/**
 * Created by Quang Nguyen on 6/3/20.
 */
abstract class BaseRecyclerViewAdapter<Item, ViewBinding : ViewDataBinding>(
    callback: DiffUtil.ItemCallback<Item>
) :
    ListAdapter<Item, BaseViewHolder<ViewBinding>>(
        AsyncDifferConfig.Builder<Item>(callback)
            .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor()).build()
    ) {

    override fun submitList(list: MutableList<Item>?) {
        super.submitList(ArrayList<Item>(list ?: listOf()))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ViewBinding> {
        return BaseViewHolder(DataBindingUtil.inflate<ViewBinding>(
            LayoutInflater.from(parent.context),
            getLayoutRes(viewType),
            parent,
            false
        ).apply {
            bindFirstTime(this)
        })
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding>, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            setVariable(BR.item, item)
            bindView(this, item, position)
            executePendingBindings()
        }
    }

    @LayoutRes
    abstract fun getLayoutRes(viewType: Int): Int

    protected open fun bindFirstTime(binding: ViewBinding) {}

    protected open fun bindView(binding: ViewBinding, item: Item, position: Int) {}

}

open class BaseViewHolder<ViewBinding : ViewDataBinding> constructor(val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root)