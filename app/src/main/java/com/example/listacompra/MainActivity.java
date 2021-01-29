package com.example.listacompra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import static androidx.appcompat.app.AlertDialog.*;


public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private ListView et2;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.edittext);
        et2 = (ListView) findViewById(R.id.Lista);

        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,
                arrayList);

        et2.setAdapter(arrayAdapter);

        et2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

                alertDialog.setTitle("Borrar articulo");
                alertDialog.setMessage("¿Seguro que quiere eliminar este articulo de la lista?");
                alertDialog.setIcon(R.drawable.delete);

                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        arrayList.remove(position);
                        arrayAdapter.notifyDataSetChanged();


                        Toast.makeText(getApplicationContext(), "Articulo eliminado", Toast.LENGTH_SHORT).show();
                    }
                });


                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                // Showing Alert Message
                alertDialog.show();
                return true;
            }
        });

    }


    public void añadir (View view){
        String result = et1.getText().toString();
        if (et1.getText().toString().isEmpty()) {
            Toast.makeText(this, "No has añadido nada",Toast.LENGTH_SHORT).show();
        }else
        arrayList.add(result);
        arrayAdapter.notifyDataSetChanged();

        et1.setText("");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.quit){
            AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("¿Quieres cerrar la aplicación?");
            builder.setCancelable(true);

            builder.setNegativeButton("SI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
        return true;
}
}