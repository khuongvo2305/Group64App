package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MenuAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Menu> menuList;

    public MenuAdapter(Context context, int layout, List<Menu> menuList) {
        this.context = context;
        this.layout = layout;
        this.menuList = menuList;
    }

    @Override
    public int getCount() {
        return menuList.size();
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
        ImageView imageMenu = (ImageView) convertView.findViewById(R.id.imageHinh);

        Menu menu = menuList.get(position);
        txtTen.setText(menu.getTen());
        txtMota.setText(menu.getMota());
        imageMenu.setImageResource(menu.getHinh());

        return convertView;
    }
}
