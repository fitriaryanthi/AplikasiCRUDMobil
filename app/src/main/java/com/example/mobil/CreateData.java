package com.example.mobil;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateData extends Activity implements OnClickListener {
    private Button buttonSubmit;
    private EditText edNama;
    private EditText edMerk;
    private EditText edHarga;
    private DBDataSource dataSource;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_data);

        buttonSubmit = (Button) findViewById(R.id.buttom_submit);
        buttonSubmit.setOnClickListener(this);
        edNama = (EditText) findViewById(R.id.nama_mobil);
        edHarga = (EditText) findViewById(R.id.harga_mobil);
        edMerk = (EditText) findViewById(R.id.merk_mobil);

        dataSource = new DBDataSource (this);

        dataSource.open();
    }

    @Override
    public void onClick (View v) {
        String nama = null;
        String merk = null;
        String harga = null;
        @SuppressWarnings("unused")
                Mobil mobil = null;
        if (edNama.getText()!=null && edMerk.getText()!=null && edHarga.getText()!=null)
        {
            nama = edNama.getText().toString();
            merk = edMerk.getText().toString();
            harga = edHarga.getText().toString();
        }
        switch (v.getId())
        {
            case R.id.buttom_submit:
                mobil = dataSource.createMobil(nama, merk, harga);

                Toast.makeText(this, "masuk Mobil\n" +
                        "nama" + mobil.getNama_mobil() +
                        "merk" + mobil.getMerk_mobil() +
                        "harga" + mobil.getHarga_mobil(), Toast.LENGTH_LONG).show();
                break;
        }
    }
}
