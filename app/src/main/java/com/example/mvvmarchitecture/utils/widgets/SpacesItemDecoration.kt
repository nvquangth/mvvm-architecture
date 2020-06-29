package com.example.mvvmarchitecture.utils.widgets

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmarchitecture.utils.AppUtils
import com.example.mvvmarchitecture.utils.Constants

class SpacesItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private var space: Int = AppUtils.dpToPx(context, Constants.SPACE_CATEGORY)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val lastItemPosition = parent.adapter?.itemCount?.minus(1)
        if (position % 2 != 0) {
            outRect.left = space / 2
            outRect.right = space
            outRect.top = space
        } else {
            outRect.left = space
            outRect.right = space / 2
            outRect.top = space
        }
        if (position == lastItemPosition) {
            outRect.bottom = space
        }
    }
}