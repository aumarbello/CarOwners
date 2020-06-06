package com.aumarbello.carowners.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aumarbello.carowners.R

class SpacingDecorator: RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildLayoutPosition(view)
        val lastItemPosition = parent.adapter?.itemCount?.dec() ?: position

        val space = parent.resources.getDimension(R.dimen.spacing_vertical).toInt()
        if (position == lastItemPosition) {
            outRect.top = space
            outRect.bottom = space
        } else {
            outRect.top = space
        }
    }
}