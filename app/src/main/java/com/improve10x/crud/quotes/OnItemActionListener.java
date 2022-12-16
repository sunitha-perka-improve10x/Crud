package com.improve10x.crud.quotes;

import com.improve10x.crud.messages.Message;

public interface OnItemActionListener {
    void onItemClicked(Quote quotes);
    void onItemDelete(Quote quotes);
    void onItemEdit(Quote quotes);
}
