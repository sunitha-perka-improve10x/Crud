package com.improve10x.crud;

public interface OnItemActionListener {
    void onItemClicked(Messages messages);
    void onItemDelete(Messages messages);
    void onItemEdit(Messages messages);
}
