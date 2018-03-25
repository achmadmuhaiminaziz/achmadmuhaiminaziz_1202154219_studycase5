package com.example.admin.achmadmuhaiminaziz_1202154219_studycase5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    TextView shapeclr;
    int colorId;
    AlertDialog.Builder alert;
    SharedPreferences.Editor sharedPrefer;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //membuat title menjadi settings
        setTitle("Settings");
        //membuat alert dialog baru bernama alert
        alert = new AlertDialog.Builder(this);
        //menginisialisasi shared preference
        SharedPreferences shared = getApplicationContext().getSharedPreferences("Preferences", 0);
        sharedPrefer = shared.edit();
        colorId = shared.getInt("Colourground", R.color.white);
        //mengakses text view pada layout
        shapeclr = findViewById(R.id.shapeColor);
        //set shape color dengan color id yang terpilih
        shapeclr.setText(getShapeColor(colorId));
    }

    //apabila tombol back di tekan
    @Override
    public void onBackPressed() {
        //intent baru dari pengaturan menuju list to do
        Intent intent = new Intent(Settings.this, ListToDo.class);
        //memulai intent
        startActivity(intent);
        //menutup aktivity setelah intent di jalanlan
        finish();
    }

    //method yang dijalankan ketika pilihan pada menu dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            //menjalankan method onbackpressed
            this.onBackPressed();
        }
        //mengembalikan nilai true
        return true;
    }

    //membuat kondisi untuk string warna yang akan digunakan untuk mengubah shape color
    public String getShapeColor(int i){
        if (i==R.color.red){
            return "Red";
        }else if (i==R.color.green){
            return "Green";
        }else if (i==R.color.blue){
            return "Blue";
        }else{
            return "Default";
        }
    }

    //membuat kondisi untuk id dari warna yang akan digunakan
    public int getColorid(int i){
        if (i==R.color.red){
            return R.id.red;
        }else if (i==R.color.green){
            return R.id.green;
        }else if (i==R.color.blue){
            return R.id.blue;
        }else{
            return R.id.white;
        }
    }
    //jika pilihwarna disentuh
    public void pilihwarna(View view) {
        //set title dari alert dialog menjadi Shape Color
        alert.setTitle("Shape Color");
        //membuat view baru
        View view1 = getLayoutInflater().inflate(R.layout.colorsettings, null);
        //menampilkan view yang telah dibuat
        alert.setView(view1);
        //mengakses radio group pada layout
        final RadioGroup radG = view1.findViewById(R.id.radiocolor);
        radG.check(getColorid(colorId));
        //ketika menekan Ok pada alert dialog
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //mendapatkan id radio button yang di pilih
                int a = radG.getCheckedRadioButtonId();
                switch (a){
                    case R.id.red:
                        colorId = R.color.red;
                        break;
                    case R.id.green:
                        colorId = R.color.green;
                        break;
                    case R.id.blue:
                        colorId = R.color.blue;
                        break;
                    case R.id.white:
                        colorId = R.color.white;
                        break;
                }
                //set shape color menjadi color id yang dipilih
                shapeclr.setText(getShapeColor(colorId));
                //mengirim coloID dengan tipe Int pada shared preference
                sharedPrefer.putInt("Colourground", colorId);
                //commit shared preference
                sharedPrefer.commit();
            }
        });
        //ketika menekan Cancel pada alert dialog
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        //membuat dan menampilkan alert dialog
        alert.create().show();
    }

}
