package com.example.mvvmarchitecture.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.mvvmarchitecture.BR

/**
 * Created by Quang Nguyen on 6/3/20.
 */
abstract class BaseFragment<ViewBinding: ViewDataBinding, ViewModel: BaseViewModel>: Fragment() {

    lateinit var viewBinding: ViewBinding

    abstract val viewModel: ViewModel

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.apply {
            setVariable(BR.viewModel, viewModel)
            root.isClickable = true
            lifecycleOwner = viewLifecycleOwner
            executePendingBindings()
        }

        viewModel.apply {
            isLoading.observe(viewLifecycleOwner, Observer {

            })
            errorMessage.observe(viewLifecycleOwner, Observer {

            })
            noInternetConnectionEvent.observe(viewLifecycleOwner, Observer {

            })
            connectTimeoutEvent.observe(viewLifecycleOwner, Observer {

            })
            forceUpdateAppEvent.observe(viewLifecycleOwner, Observer {

            })
            serverMaintainEvent.observe(viewLifecycleOwner, Observer {

            })
            unknownErrorEvent.observe(viewLifecycleOwner, Observer {

            })
        }
    }

    fun showErrorMessage(message: String) {

    }

    fun showLoading() {

    }
}