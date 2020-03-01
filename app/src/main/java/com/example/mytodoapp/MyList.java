package com.example.mytodoapp;

public class MyList {

    String itemTitle, desc, time, key;


    public MyList() {

    }

    public MyList(String itemTitle, String desc, String time, String key) {
        this.itemTitle = itemTitle;
        this.desc = desc;
        this.time = time;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
