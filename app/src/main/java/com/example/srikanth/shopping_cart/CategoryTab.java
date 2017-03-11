package com.example.srikanth.shopping_cart;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by Srikanth on 3/9/2017.
 */

public class CategoryTab extends Fragment {

    Button _myGroceries;
    Button _homeDecor;
    Button _electronics;
    DatabaseHelper db;
    ArrayList<ItemDetails> itemsCat = new ArrayList<ItemDetails>();
    final Context con=this.getContext();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_categeries, container, false);

        _myGroceries = (Button) rootView.findViewById(R.id.groceries);
        _homeDecor = (Button) rootView.findViewById(R.id.homeDecor);
        _electronics = (Button) rootView.findViewById(R.id.electronics);

        final Context con=this.getContext();

        db = MainActivity.db;
        _myGroceries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {







            }
        });

        _homeDecor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        _electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Cursor c = db.getCategoryDetails("ELECTRONICS");
                String name,offer,desc,image;
                int price;
                while(c.moveToNext())
                {
                    name = c.getString(c.getColumnIndex(db.ITEM_NAME));
                    offer = c.getString(c.getColumnIndex(db.ITEM_OFFER));
                    price = c.getInt(c.getColumnIndex(db.ITEM_PRICE));
                    desc = c.getString(c.getColumnIndex(db.ITEM_DESCRIPTION));
                    image = c.getString(c.getColumnIndex(db.ITEM_IMAGE));


                    String strPrice = String.valueOf(price);

                    Log.d("Name ",name);
                    Log.d("offer",offer);
                    Log.d("price",String.valueOf(price));
                    itemsCat.add(new ItemDetails(name,strPrice,offer,desc,image));
                }
                GridView _gv = (GridView) rootView.findViewById(R.id.gv_items);
                ItemsAdaptor adapter = new ItemsAdaptor(con,itemsCat);
                _gv.setAdapter(adapter);

            }
        });



        return rootView;
    }



}
