package com.example.parcial1android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MoviesList extends AppCompatActivity  {


    ListView listado;
    ArrayList<String> peliculas;
    ArrayAdapter<Pelicula> arrayAdapter;
    ArrayList<Pelicula> pelis;
    TextView nombrePeli,directorPel,idiomaPel,generoPel,nombreF;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);


        peliculas = new ArrayList<>();
        Intent in = getIntent();
        pelis = in.getParcelableArrayListExtra("pel");
        Toast.makeText(this,"Peliculas ="+ pelis.size(),Toast.LENGTH_LONG).show();

        nombrePeli= findViewById(R.id.txtnombreF);
        directorPel= findViewById(R.id.txtDirector);
        idiomaPel= findViewById(R.id.txtIdioma);
        generoPel= findViewById(R.id.txtGeneros);
        btn=findViewById(R.id.btnActualizar);

        for (int i = 1; i<=10; i++) {
            peliculas.add("Pelicula " + i );
        }
        listado = findViewById(R.id.lstlistado);
        arrayAdapter = new PeliculaAdapter(this, pelis);

        listado.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                String dato = peliculas.get(position);
                Toast.makeText(getApplicationContext(), "Pelicula = " + dato, Toast.LENGTH_LONG).show();
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        peliculas.set(position,listado.toString());
                        arrayAdapter.notifyDataSetChanged();
                    }
                });
            }
        });



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movieslist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.mnuAddMovie:
                Intent i =new Intent(this,MainActivity.class);
                startActivity(i);

                break;

        }
        return super.onOptionsItemSelected(item);
    }



}
