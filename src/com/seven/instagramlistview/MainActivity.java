package com.seven.instagramlistview;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(new HeaderAdapter());
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                    int visibleItemCount, int totalItemCount) {
                for (int i = 0; i < view.getChildCount(); i++) {
                    View child = view.getChildAt(i);
                    View header = (View) child.getTag();

                    if (i == 0) {
                        int offset = header.getHeight() - child.getBottom();
                        offset = offset >= 0 ? offset : 0;
                        header.offsetTopAndBottom(-header.getTop()
                                - child.getTop() - offset);
                    } else {
                        header.offsetTopAndBottom(-header.getTop());
                    }

                    // *** setTranslationY : requires API level 11 *** //
                    // if (i == 0) {
                    // int offset = header.getHeight() - child.getBottom();
                    // offset = offset >= 0 ? offset : 0;
                    // header.setTranslationY(-child.getTop() - offset);
                    // } else {
                    // header.setTranslationY(0);
                    // }
                }
            }
        });
    }

    private class HeaderAdapter extends BaseAdapter {

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).inflate(
                        R.layout.list_item, parent, false);
                convertView.setTag(convertView.findViewById(R.id.header));
            }
            TextView header = (TextView) convertView.findViewById(R.id.header);
            header.setText("Header " + position);
            return convertView;
        }

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }
    }
}
