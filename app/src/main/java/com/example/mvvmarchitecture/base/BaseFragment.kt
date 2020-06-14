package com.example.mvvmarchitecture.base

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.core.view.updatePadding
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.mvvmarchitecture.BR
import com.example.mvvmarchitecture.R
import com.example.mvvmarchitecture.util.dismissLoadingDialog
import com.example.mvvmarchitecture.util.showDialog
import com.example.mvvmarchitecture.util.showLoadingDialog

/**
 * Created by Quang Nguyen on 6/3/20.
 */
abstract class BaseFragment<ViewBinding : ViewDataBinding, ViewModel : BaseViewModel> : Fragment() {

    lateinit var viewBinding: ViewBinding

    abstract val viewModel: ViewModel

    @get:LayoutRes
    abstract val layoutId: Int

    @RequiresApi(Build.VERSION_CODES.KITKAT_WATCH)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        viewBinding.root.setOnApplyWindowInsetsListener { v, insets ->

            v.updatePadding(0, 0, 0, 0)

            insets
        }

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
                if (it == true) {
                    context?.showLoadingDialog()
                } else {
                    dismissLoadingDialog()
                }
            })
            errorMessage.observe(viewLifecycleOwner, Observer {
                dismissLoadingDialog()

                showErrorMessage(it)
            })
            noInternetConnectionEvent.observe(viewLifecycleOwner, Observer {
                showErrorMessage(getString(R.string.no_internet_connection))
            })
            connectTimeoutEvent.observe(viewLifecycleOwner, Observer {
                showErrorMessage(getString(R.string.connect_timeout))
            })
            forceUpdateAppEvent.observe(viewLifecycleOwner, Observer {
                showErrorMessage(getString(R.string.force_update_app))
            })
            serverMaintainEvent.observe(viewLifecycleOwner, Observer {
                showErrorMessage(getString(R.string.server_maintain_message))
            })
            unknownErrorEvent.observe(viewLifecycleOwner, Observer {
                showErrorMessage(getString(R.string.unknown_error))
            })
        }
    }

    fun showErrorMessage(message: String?) {
        if (message.isNullOrEmpty()) return

        context?.showDialog()
    }
}