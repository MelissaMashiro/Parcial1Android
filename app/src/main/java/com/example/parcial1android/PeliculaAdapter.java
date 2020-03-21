package com.example.parcial1android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class PeliculaAdapter extends ArrayAdapter<Pelicula> {

    private LayoutInflater inflater = null;

    public PeliculaAdapter(@NonNull Context context, @NonNull ArrayList<Pelicula> est) {
        super(context, 0, est);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        ViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.listado_pelicula, null);
            // Do some initialization

            //Retrieve the view on the item layout and set the value.
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }

        //Retrieve your object
        Pelicula data = (Pelicula) getItem(position);


        viewHolder.nom.setText(data.getNombre());
        viewHolder.dir.setText(data.getDirector());
        viewHolder.gen.setText(data.getGenero());
        viewHolder.idiom.setText(data.getIdioma());


        return view;

    }

    private class ViewHolder
    {
        private final TextView nom;
        private final TextView dir;
        private final TextView gen;
        private final TextView idiom;

        private ViewHolder(View view)
        {
            nom   = (TextView) view.findViewById(R.id.txtnombreF);
            dir   = (TextView) view.findViewById(R.id.txtDirector);
            gen   = (TextView) view.findViewById(R.id.txtGeneros);
            idiom = (TextView) view.findViewById(R.id.txtIdioma);
        }


    }


}
