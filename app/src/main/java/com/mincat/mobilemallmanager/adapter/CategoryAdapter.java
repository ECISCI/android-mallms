package com.mincat.mobilemallmanager.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mincat.mobilemallmanager.R;
import com.mincat.mobilemallmanager.ui.GoodsCategoryAct;

public class CategoryAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mItems;
    public static int mPosition;

    public CategoryAdapter(Context context, String[] strings) {
        this.mContext = context;
        this.mItems = strings;
    }

    @Override
    public int getCount() {
        return mItems.length;
    }

    @Override
    public Object getItem(int position) {
        return mItems[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_category_item, null);

        TextView mItemName = convertView.findViewById(R.id.tv_item_name);

        mPosition = position;

        mItemName.setText(mItems[position]);

        if (position == GoodsCategoryAct.mPosition) {

            convertView.setBackgroundColor(Color.parseColor("#ffffff"));
            mItemName.setTextColor(Color.parseColor("#4ac2bc"));

            TextPaint tp = mItemName.getPaint();
            tp.setFakeBoldText(true);

        } else {
            convertView.setBackgroundColor(Color.parseColor("#efeff4"));
            mItemName.setTextColor(Color.parseColor("#676767"));

            TextPaint tp = mItemName.getPaint();
            tp.setFakeBoldText(false);
        }
        return convertView;
    }
}
