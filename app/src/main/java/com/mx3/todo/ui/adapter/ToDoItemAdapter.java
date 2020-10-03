package com.mx3.todo.ui.adapter;

import androidx.databinding.library.baseAdapters.BR;

import com.mx3.todo.R;
import com.mx3.todo.data.model.ToDoItem;
import com.mx3.todo.databinding.ItemTodoBinding;
import com.mx3.todo.ui.adapter.base.BaseRecyclerViewAdapter;

public class ToDoItemAdapter extends BaseRecyclerViewAdapter<ToDoItem, ItemTodoBinding> {

    public ToDoItemAdapter(OnItemClickListener<ToDoItem> onItemClickListener) {
        super(onItemClickListener);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_todo;
    }

    @Override
    protected int getViewBindingVariableId() {
        return BR.toDoItem;
    }

    @Override
    protected void onViewHolderBinding(ItemTodoBinding viewDataBinding, ToDoItem item, int position) {
    }
}
