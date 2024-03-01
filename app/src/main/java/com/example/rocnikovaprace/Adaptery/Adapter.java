package com.example.rocnikovaprace.Adaptery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rocnikovaprace.R;
import com.example.rocnikovaprace.SeznamKategorii;
import com.example.rocnikovaprace.Slovicka;
import com.example.rocnikovaprace.ui.Kategorie;
import com.example.rocnikovaprace.ui.SlovickoSnake;
import com.example.rocnikovaprace.ui.SpravujSlovicka.RecyclerViewClickInterface;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyView> {

    private List<SlovickoSnake> list;
    public interface onNoteListener {
    }
    RecyclerViewClickInterface recyclerViewClickInterface;
    Context context;

    public static class MyView
            extends RecyclerView.ViewHolder {


        public TextView textView;
        public ImageView obrazek;
        public CardView cardView;

        // Konstruktor s paramentrem View
        public MyView(View view) {
            super(view);


            textView = (TextView) view
                    .findViewById(R.id.textview);

            obrazek = (ImageView) view
                    .findViewById(R.id.obrazek);

            cardView = (CardView) view
                    .findViewById(R.id.cardview);


        }
    }

    //Další konstruktor
    public Adapter(List<SlovickoSnake> horizontalList, Context context, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.list = horizontalList;
        this.context = context;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    // Metoda, která se stará o rozložení a vzhled jednotlivých položek v seznamu
    @Override
    public MyView onCreateViewHolder(ViewGroup parent,
                                     int viewType) {

        // Inflate item.xml using LayoutInflator
        View itemView
                = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item,
                        parent,
                        false);

        // return itemView
        return new MyView(itemView);
    }
    //Metoda, která převádí string na bitmapu zdroj:http://www.java2s.com/example/android/graphics/convert-bitmap-to-string.html
    public static Bitmap convertStringToBitmap(String string) {
        byte[] byteArray1;
        byteArray1 = Base64.decode(string, Base64.DEFAULT);
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray1, 0,
                byteArray1.length);/* w  w  w.ja va 2 s  .  c om*/
        return bmp;
    }

    @Override
    public void onBindViewHolder(final MyView holder, @SuppressLint("RecyclerView") final int position) {

        //Nastaví text a obrázek, každé položce v seznamu
        holder.textView.setText(list.get(position).nazev);
        holder.obrazek.setImageBitmap(convertStringToBitmap(list.get(position).obrazek));

        Kategorie kategorie = SeznamKategorii.podleNazvu(list.get(position).kategorie);
        if (kategorie != null) {
            holder.cardView.setCardBackgroundColor(Color.parseColor(kategorie.getBarva()));
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewClickInterface.setClick(position);
            }
        });


    }

    // Vrátí délku seznamu
    @Override
    public int getItemCount() {
        return list.size();
    }


}
