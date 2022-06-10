package com.edu.csc.kt1_buiminhthien.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.edu.csc.kt1_buiminhthien.R;
import com.edu.csc.kt1_buiminhthien.model.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;

//kết nối biến item_sanpham với MainActivity.java
public class Adapterproduct extends ArrayAdapter<Product> {
    Activity activity;
    public Adapterproduct(Activity activity, ArrayList products){
        super(activity,0,products);
        this.activity=activity;
    }
    public View getView(int position, View convertView, ViewGroup viewGroup){
        if(convertView==null){
            LayoutInflater inflater = this.activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_sanpham,null);

        }
        TextView adProductName=(TextView) convertView.findViewById(R.id.namexml);
        TextView adProductUnit=(TextView) convertView.findViewById(R.id.unitxml);
        TextView adProductPrice=(TextView) convertView.findViewById(R.id.pricexml);

        Product p = getItem(position);
        adProductName.setText(p.getProductName());
        adProductUnit.setText(p.getProductUnit());
        String s = (new DecimalFormat("#,###.##")).format(p.getProductPrice());
        adProductPrice.setText(s);
        return convertView;
    }

}
