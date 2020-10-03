package com.mx3.todo.ui.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapters {

    @BindingAdapter("divider")
    public static void addDividerItemDecoration(RecyclerView recyclerView, int orientation) {
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), orientation));
    }
}
