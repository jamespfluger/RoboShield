package com.jamespfluger.roboshield.lists;

import com.jamespfluger.roboshield.utils.FormatUtil;

public class Item {
    public final String id;
    public final String content;
    public final String rawContent;

    public Item(String id, String content) {
        this.id = id;
        this.rawContent = content.replaceAll("[^0-9]+", "");
        this.content = FormatUtil.formatPhoneNumber(this.rawContent);
    }
}
