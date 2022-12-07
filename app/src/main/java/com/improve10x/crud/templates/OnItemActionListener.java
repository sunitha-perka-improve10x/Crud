package com.improve10x.crud.templates;

import com.improve10x.crud.templates.Template;

public interface OnItemActionListener {
    void OnItemClicked (Template template);
    void OnItemDelete(Template template);
    void OnItemEdit(Template template);

}
