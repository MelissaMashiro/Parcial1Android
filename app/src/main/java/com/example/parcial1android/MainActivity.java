package com.example.parcial1android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    Button  agregar,cancelar;
    ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
    EditText nombrePel, director, genero,idioma;
    String textSpin,rdb;
    //radiobuton
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView nombrePeli,directorPel,idiomaPel,generoPel,nombreF;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        String nombre = i.getStringExtra("pelicula");

        agregar = findViewById(R.id.btnAgregar);
        cancelar= findViewById(R.id.btnCancelar);
        nombrePel = findViewById(R.id.edtPelicula);
        director = findViewById(R.id.edtDirector);
        //textViews
        nombrePeli= findViewById(R.id.txtPelicula);
        directorPel= findViewById(R.id.txtDirector);
        idiomaPel= findViewById(R.id.txtIdioma);
        generoPel= findViewById(R.id.txtGeneros);

        //radiobuton
        radioGroup = findViewById(R.id.radioGroup);


        //spiner
        Spinner genero = findViewById(R.id.spinGenero);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.generos, android.R.layout.simple_spinner_item);
                 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                 genero.setAdapter(adapter);
         genero.setOnItemSelectedListener(this);

         agregar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        registerForContextMenu(nombrePel);
        registerForContextMenu(director);


    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(final View v) {
        switch(v.getId()){

            case R.id.btnAgregar:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("¿Desea agregar esta pelicula?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                checkButton(v);//guarda la seleccion del radiobtn
                                agregarPelicula();
                                // personas.add("Persona " + (personas.size()+1));
                                Toast.makeText(getApplicationContext()," Pelicula agregada", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alerta = builder.create();
                alerta.show();

                break;

            case R.id.btnCancelar :


                break;
        }
    }

    private void agregarPelicula() {

        if(radioButton.getText()==null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Alguno de los campos estan incompletos")
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alerta = builder.create();
            alerta.show();

        }else{
            peliculas.add(new Pelicula(nombrePel.getText().toString(), director.getText().toString(), rdb, textSpin));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mainactivity, menu);
        return true;
    }


    public void verListado(){
        Intent i = new Intent(this,MoviesList.class);
        i.putParcelableArrayListExtra("pel", peliculas);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.mnuListado:
                verListado();
                //return true;
                break;
            case R.id.mnuMAYUS:
                nombrePel.setText(nombrePel.getText().toString().toUpperCase());
                director.setText(director.getText().toString().toUpperCase());
                break;
            case R.id.mnuChnColor:
                nombrePeli.setTextColor(Color.BLUE);
                directorPel.setTextColor(Color.CYAN);
                idiomaPel.setTextColor(Color.MAGENTA);
                generoPel.setTextColor(Color.CYAN);
                break;
            case R.id.mnuOrderName:
                ordenarNombre();
                verListado();
                break;
            case R.id.mnuOrderGenero:
                ordenarGenero();
                verListado();
                break;
            case R.id.mnuInvertir:
                invertirArray();
                break;
            case R.id.mnuDeleteFirst:
                borrarPrimerElemento();
                verListado();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //METODO PARA LA LISTA DE PELICULAS
    public void  ordenarNombre(){
        ArrayList<String> nomPelis = new ArrayList<>();
        ArrayList<Pelicula> aux = new ArrayList<>();

        for(Pelicula p : peliculas){
            nomPelis.add(p.getNombre());
        }

        Collections.sort(nomPelis); //ordenando por nombre

        for (String nom:nomPelis) {
            for (Pelicula p : peliculas) {
                if (p.getNombre().equalsIgnoreCase(nom)){
                    aux.add(p);
                }
            }
        }
        peliculas=aux;

    }

    public void  ordenarGenero(){
        ArrayList<String> genPelis = new ArrayList<>();
        ArrayList<Pelicula> aux = new ArrayList<>();

        for(Pelicula p : peliculas){
            genPelis.add(p.getGenero());
        }

        Collections.sort(genPelis); //ordenando por genero

        for (String gen : genPelis) {
            for (Pelicula p : peliculas) {
                if (p.getGenero().equalsIgnoreCase(gen)){
                    aux.add(p);
                }
            }
        }
        peliculas=aux;

    }

    public void  invertirArray(){

        ArrayList<Pelicula> aux = new ArrayList<>();

        //guardando de atras hacia adelante
        for(int i=peliculas.size();i>=0;i--){
            aux.add(peliculas.get(i));
        }

        peliculas=aux;

    }

    public void borrarPrimerElemento(){

        peliculas.remove(1);

    }

    //RADIOBUTTON

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);
        rdb=radioButton.getText().toString();

        Toast.makeText(this, "Selected Radio Button: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();
    }
//SPINNER
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         textSpin = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), textSpin, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




}
