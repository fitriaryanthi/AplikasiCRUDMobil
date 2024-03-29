package com.example.mobil;

import java.util.ArrayList;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class ViewData extends ListActivity implements OnItemLongClickListener {

    private DBDataSource dataSource;
    private ArrayList<Mobil> values;
    private Button editButton;
    private Button delButton;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView(R.layout.viewdata);
        dataSource = new DBDataSource(this);

        dataSource.open();

        values = dataSource.getAllMobil();

        ArrayAdapter<Mobil> adapter = new ArrayAdapter<Mobil>(this, android.R.layout.simple_list_item_1,values);

        setListAdapter(adapter);

        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onItemLongClick(final AdapterView<?> adapter, View v, int pos,
                                   final long id) {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_view);
        dialog.setTitle("Pilih Aksi");
        dialog.show();
        final Mobil b = (Mobil) getListAdapter().getItem(pos);
        editButton = (Button) dialog.findViewById(R.id.button_edit_data);
        delButton = (Button) dialog.findViewById(R.id.button_delete_data);


        editButton.setOnClickListener(
                new OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        switchToEdit(b.getId());
                        dialog.dismiss();
                    }
                }
        );

        delButton.setOnClickListener(
                new OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        dataSource.deleteMobil(b.getId());
                        dialog.dismiss();
                        finish();
                        startActivity(getIntent());
                    }
                }
        );

        return true;
    }

    public void switchToEdit(long id)
    {
        Mobil b = dataSource.getMobil(id);
        Intent i = new Intent(this, EditData.class);
        Bundle bun = new Bundle();
        bun.putLong("id", b.getId());
        bun.putString("nama", b.getNama_mobil());
        bun.putString("merk", b.getMerk_mobil());
        bun.putString("harga", b.getHarga_mobil());
        i.putExtras(bun);
        finale();
        startActivity(i);
    }

    public void finale()
    {
        ViewData.this.finish();
        dataSource.close();
    }
    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }

}
