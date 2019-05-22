package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class viewOrderAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ViewOrder> ViewOrderList;

    public viewOrderAdapter(Context context, int layout, List<ViewOrder> ViewOrderList) {
        this.context = context;
        this.layout = layout;
        this.ViewOrderList = ViewOrderList;
    }

    @Override
    public int getCount() {
        return ViewOrderList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView =inflater.inflate(layout,null);
        TextView txtTen =(TextView) convertView.findViewById(R.id.textTen);
        TextView txtMota = (TextView) convertView.findViewById(R.id.textMota);
        TextView txtSoluong = (TextView) convertView.findViewById(R.id.soluong);
        TextView txtTongtien = (TextView) convertView.findViewById(R.id.tongtien);
        ViewOrder viewOrder = ViewOrderList.get(position);

        txtTen.setText(viewOrder.getTen());
        txtMota.setText(viewOrder.getGiatien());
        txtSoluong.setText(viewOrder.getSoluong());
        txtTongtien.setText(viewOrder.getTongtien());

        return convertView;
    }
}
