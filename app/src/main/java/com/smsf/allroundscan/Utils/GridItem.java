package com.smsf.allroundscan.Utils;

import android.content.Context;

/**
 * @Description: Gridvew适配器数据项实体，用于我的文档中item数据项
 * @Author: liys
 * @CreateDate: 2020/4/28 10:02
 */

public class GridItem {
    private String itemTitle;
    private String  res_id;
    private boolean showedit;
    public boolean isShowedit() {
        return showedit;
    }

    public void setShowedit(boolean showedit) {
        this.showedit = showedit;
    }


    public GridItem(String menuTitle, String res_id) {
        this.itemTitle = menuTitle;
        this.res_id = res_id;
    }

    public String getMenuTitle() {
        return itemTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.itemTitle = menuTitle;
    }

    public String getRes_id() {
        return res_id;
    }

    public void setRes_id(String res_id) {
        this.res_id = res_id;
    }
}