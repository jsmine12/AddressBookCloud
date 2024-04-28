package com.AddressBookClient.Utils;
import javafx.scene.AccessibleAttribute;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableView;
public class MyTableView<S> extends TableView<S> {
    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        // 这里是关键
        ScrollBar scrollBar = (ScrollBar) queryAccessibleAttribute(AccessibleAttribute.HORIZONTAL_SCROLLBAR);
        if (scrollBar != null && scrollBar.isVisible()) {
            scrollBar.setPrefHeight(0);
            scrollBar.setMaxHeight(0);
            scrollBar.setOpacity(1);
            scrollBar.setVisible(false);
        }
    }
}