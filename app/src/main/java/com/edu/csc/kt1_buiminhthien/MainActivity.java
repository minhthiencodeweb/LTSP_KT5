package com.edu.csc.kt1_buiminhthien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.edu.csc.kt1_buiminhthien.Adapter.Adapterproduct;
import com.edu.csc.kt1_buiminhthien.model.DataSale;
import com.edu.csc.kt1_buiminhthien.model.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvmainjava;
    Adapterproduct adapterproduct;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvmainjava=(ListView) findViewById(R.id.lvmainxml);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("List View Nâng cao vd1");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //khởi tạo các sản phẩm từ DataSale
        DataSale dataSale = DataSale.get();
        dataSale.generateProducts();

        //lấy các product từ DataSale
        ArrayList products = dataSale.getProducts();
        adapterproduct= new Adapterproduct(this,products);
        lvmainjava.setAdapter(adapterproduct);

        //click để xóa
        lvmainjava.setOnItemLongClickListener(new ItemLongClickRemove());


        //click để sửa
        lvmainjava.setOnItemClickListener((parent, view, position, id) ->{
            Intent intent = new Intent(MainActivity.this, ProductActivity .class);
            intent.putExtra( ProductActivity .EXTRA_POSITION,position);
            startActivity(intent);
        });
    }


    private class ItemLongClickRemove implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView parent, View view, final int position, long id) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setMessage("Bán có muốn xóa sản phẩm này!");
            alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // xóa sp đang nhấn giữ
                    DataSale.get().getProducts().remove(position);
                    //cập nhật lại listview
                    adapterproduct.notifyDataSetChanged();

                }
            });
            alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //không làm gì
                }
            });
            alertDialogBuilder.show();
            return true;
        }
    }
    //menu actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    @Override //xử lý sự kiện khi bấm nút
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.btnAdd:
                openMyCustom();
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openMyCustom() {
        //Khởi tọa AlertDialog từ đối tượng Builder. Tham số truyền vào ở đây là context.
        final AlertDialog builder = new AlertDialog.Builder(this).create();

        //Khỏi tạo đối tượng View từ file activity_product
        final View alert = LayoutInflater.from(this).inflate(R.layout.activity_product,null);

        //Set layout cho alert dialog
        builder.setView(alert);

        final EditText maintxtName;
        final EditText maintxtUnit;
        final EditText manitxtPrice;
        Button buttonOK;
        Button buttonCancel;

        //Tham chiếu các đối tượng có trên giao dienje dialog vừa được set
        maintxtName = alert.findViewById(R.id.editnamexml);
        maintxtUnit = alert.findViewById(R.id.editunitxml);
        manitxtPrice = alert.findViewById(R.id.editpricexml);
        buttonOK = alert.findViewById(R.id.btnOK);
        buttonCancel = alert.findViewById(R.id.btnCancel);

        //Tạo dialog và hiển thị
        builder.show();


        //luu vào list view
        //Bắt sự kiện click vào list vào nút lưu
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = new Product();
                product.setProductName(maintxtName.getText().toString());
                product.setProductUnit(maintxtUnit.getText().toString());
                String s = manitxtPrice.getText().toString();
                s=s.replace(",","");
                double price = Double.parseDouble(s);
                product.setProductPrice(price);
                adapterproduct.add(product);
                adapterproduct.notifyDataSetChanged();
                builder.dismiss();
            }
        });


        //Bắt sự kiện click vào nut thoát
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.dismiss();
            }
        });



















    }


}