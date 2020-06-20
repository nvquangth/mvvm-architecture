package com.example.mvvmarchitecture.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.mvvmarchitecture.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Created by Quang Nguyen on 6/9/20.
 */

var loadingDialog: AlertDialog? = null
var dialog: AlertDialog? = null

fun Context?.showLoadingDialog(
    cancelable: Boolean = false,
    cancelOnTouchOutside: Boolean = false
) {
    MaterialAlertDialogBuilder(this).apply {
        setView(R.layout.layout_loading_dialog)
    }.create().apply {
        setCancelable(cancelable)
        setCanceledOnTouchOutside(cancelOnTouchOutside)

        dismissLoadingDialog()
        loadingDialog = this

        show()
    }
}

fun dismissLoadingDialog() {
    if (loadingDialog != null && loadingDialog?.isShowing == true) loadingDialog?.dismiss()
}

fun Context?.showDialog(
    cancelable: Boolean = false,
    cancelOnTouchOutside: Boolean = true,
    title: String? = null,
    message: String? = null,
    textPositive: String? = this?.resources?.getString(android.R.string.ok),
    textPositiveClickListener: (() -> Unit)? = null,
    textNeutral: String? = null,
    textNeutralClickListener: (() -> Unit)? = null,
    textNegative: String? = this?.resources?.getString(android.R.string.cancel),
    textNegativeClickListener: (() -> Unit)? = null
) {
    MaterialAlertDialogBuilder(this).apply {
        setTitle(title)
        setMessage(message)
        textPositive?.let {
            setPositiveButton(it) { _, _ ->
                textPositiveClickListener?.invoke()
            }
        }
        textNeutral?.let {
            setNeutralButton(it) { _, _ ->
                textNeutralClickListener?.invoke()
            }
        }
        textNegative?.let {
            setNegativeButton(it) { _, _ ->
                textNegativeClickListener?.invoke()
            }
        }
    }.create().apply {
        setCancelable(cancelable)
        setCanceledOnTouchOutside(cancelOnTouchOutside)

        dismissDialog()
        dialog = this

        show()
    }
}

fun dismissDialog() {
    if (dialog != null && dialog?.isShowing == true) dialog?.dismiss()
}
