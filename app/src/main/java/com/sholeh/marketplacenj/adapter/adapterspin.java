package com.sholeh.marketplacenj.adapter;

import android.content.Context;

import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class adapterspin extends ArrayAdapter<String> {

    public adapterspin(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public int getCount() {
        int count = super.getCount();
        return count > 0 ?count-1 : count;
    }
}
