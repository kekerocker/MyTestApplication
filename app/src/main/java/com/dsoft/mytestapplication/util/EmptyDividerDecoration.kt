package com.dsoft.mytestapplication.util

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class EmptyDividerDecoration(
    context: Context,
    @DimenRes cardInsets: Int,
    private var displayMode: Int = DisplayMode.AUTO // fixme: auto is broken
) : RecyclerView.ItemDecoration() {

    private val spacing: Int = context.resources.getDimensionPixelSize(cardInsets)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildViewHolder(view).adapterPosition
        val itemCount = state.itemCount
        val layoutManager = parent.layoutManager
        setSpacingForDirection(outRect, layoutManager, position, itemCount)
    }

    private fun setSpacingForDirection(
        outRect: Rect,
        layoutManager: RecyclerView.LayoutManager?,
        position: Int,
        itemCount: Int
    ) {
        // Resolve display mode automatically
        if (displayMode == DisplayMode.AUTO) {
            displayMode = resolveDisplayMode(layoutManager)
        }

        when (displayMode) {
            DisplayMode.HORIZONTAL -> {
                outRect.left = if (position != 0) spacing else 0
                outRect.right = 0 // if (position < itemCount - 1) spacing else 0
                outRect.top = 0
                outRect.bottom = 0
            }
            DisplayMode.VERTICAL -> {
                outRect.left = 0
                outRect.right = 0
                outRect.top = if (position != 0) spacing else 0
                outRect.bottom = 0 // if (position < itemCount - 1) spacing else 0
            }
            DisplayMode.GRID -> {
                // todo
                outRect.left = if (position != 0) spacing else 0
                outRect.right = 0 // spacing
                outRect.top = 0 // if (position != 0) spacing else 0
                outRect.bottom = 0 // spacing
            }
        }
    }

    private fun resolveDisplayMode(layoutManager: RecyclerView.LayoutManager?): Int {
        return when (layoutManager) {
            is GridLayoutManager, is StaggeredGridLayoutManager -> DisplayMode.GRID
            else -> if (layoutManager!!.canScrollHorizontally()) DisplayMode.HORIZONTAL else DisplayMode.VERTICAL
        }
    }

    object DisplayMode {
        const val AUTO = -1
        const val HORIZONTAL = 0
        const val VERTICAL = 1
        const val GRID = 2
    }
}