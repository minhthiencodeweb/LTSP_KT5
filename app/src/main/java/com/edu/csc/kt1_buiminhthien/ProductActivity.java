package com.edu.csc.kt1_buiminhthien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.edu.csc.kt1_buiminhthien.model.DataSale;
import com.edu.csc.kt1_buiminhthien.model.Product;

import java.text.DecimalFormat;

//man hình 2
public class ProductActivity extends AppCompatActivity {
    public final static String EXTRA_POSITION="position";
    EditText proNameja, proUnitja, proPriceja;
    Product product;
    Intent it;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        //lấy các control trên layout activity.product.xml
        proNameja = findViewById(R.id.editnamexml);
        proUnitja =  findViewById(R.id.editunitxml);
        proPriceja = findViewById(R.id.editpricexml);


        //hiển thị lên màn hình nội dung lấy được từ Data
        it = getIntent();
        position = it.getExtras().getInt(EXTRA_POSITION);
        product = DataSale.get().getProducts().get(position);

        proNameja.setText(product.getProductName());
        proUnitja.setText(product.getProductUnit());
        String s = (new DecimalFormat("#,###.##")).format(product.getProductPrice());
        proPriceja.setText(s);


        //thiết đặt sự kiện khi Click vào các Button lưu sản phẩm vào mảng listview
        Button btnOKja = findViewById(R.id.btnOK);
        Button btnCancelja = findViewById(R.id.btnCancel);

        btnOKja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lấy dữ liệu từ layout để cập nhập lại các sản phẩm trong mảng
                product.setProductName(proNameja.getText().toString());
                product.setProductUnit(proUnitja.getText().toString());
                String s = proPriceja.getText().toString();
                s = s.replace(",","");
                double price = Double.parseDouble(s);
                product.setProductPrice(price);

                Intent returnIntent=new Intent();
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        btnCancelja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //không làm gì và trở về màng hình trước
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED,returnIntent);
                finish();
            }
        });


    }
}