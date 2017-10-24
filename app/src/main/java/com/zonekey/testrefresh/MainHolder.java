package com.zonekey.testrefresh;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by xu.wang
 * Date on  2017/10/24 09:11:18.
 *
 * @Desc
 */

public class MainHolder extends RecyclerView.ViewHolder {

    public TextView tv_main;

    public MainHolder(View itemView) {
        super(itemView);
        tv_main = (TextView) itemView.findViewById(R.id.tv_item_main);
    }
}
