package com.zonekey.testrefresh;

import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.xiaoqiang.refresh.SwipeRecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mLists = new ArrayList<>();
    private MainAdapter mainAdapter;
    private SwipeRecyclerView swipeRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialView();
        initialData();
    }

    private void initialView() {
        swipeRecyclerView = (SwipeRecyclerView) findViewById(R.id.swipeRefresh);
//        rv_main = (RecyclerView) findViewById(R.id.rv_main);
//
        for (int i = 0; i < 18; i++) {
            mLists.add(i + "");
        }

    }

    private void initialData() {
        mainAdapter = new MainAdapter(mLists);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        swipeRecyclerView.getRecyclerView().setLayoutManager(layoutManager);
        swipeRecyclerView.setAdapter(mainAdapter);

        View emptyView = LayoutInflater.from(MainActivity.this).inflate(R.layout.empty_page, null);
        swipeRecyclerView.setEmptyView(emptyView);

        swipeRecyclerView.setOnLoadListener(new SwipeRecyclerView.OnLoadListener() {
            @Override
            public void onRefresh() {
                mLists.clear();
                mainAdapter.notifyDataSetChanged();
               new RefreshThread().start();
            }

            @Override
            public void onLoadMore() {
                new LoadMoreThread().start();
            }
        });
    }


    class RefreshThread extends Thread {
        @Override
        public void run() {
            super.run();
            SystemClock.sleep(3000);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 18; i++) {
                        mLists.add(i + "");
                    }
                    swipeRecyclerView.complete();
                    mainAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    class LoadMoreThread extends Thread {
        @Override
        public void run() {
            super.run();
            SystemClock.sleep(3000);
            for (int i = 0; i < 6; i++) {
                mLists.add(i + "");
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    swipeRecyclerView.stopLoadingMore();
                    mainAdapter.notifyDataSetChanged();
                }
            });
        }
    }

}
