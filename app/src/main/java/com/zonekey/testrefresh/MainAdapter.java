package com.zonekey.testrefresh;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by xu.wang
 * Date on  2017/10/24 09:11:02.
 *
 * @Desc
 */

public class MainAdapter extends RecyclerView.Adapter<MainHolder> {
    private ArrayList<String> mLists;

    public MainAdapter(ArrayList<String> lists) {
        this.mLists = lists;
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(MainHolder holder, int position) {
        holder.tv_main.setText(mLists.get(position));
    }

    @Override
    public int getItemCount() {
        return mLists == null ? 0 : mLists.size();
    }
}
