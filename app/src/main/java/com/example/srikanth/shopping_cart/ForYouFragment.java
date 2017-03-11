package com.example.srikanth.shopping_cart;

import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by Srikanth on 3/9/2017.
 */

public class ForYouFragment extends Fragment {


    private DatabaseHelper db;
    ArrayList<ItemDetails> items = new ArrayList<ItemDetails>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_main, container, false);


        db=new DatabaseHelper(this.getContext());
        Cursor c = db.getItemDetails();

        String name,offer,image,desc;
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
            Log.d("Image",image);

            items.add(new ItemDetails(name,strPrice,offer,desc,image));
        }
        GridView _gv = (GridView) rootView.findViewById(R.id.gv_items);
        ItemsAdaptor adapter = new ItemsAdaptor(this.getContext(),items);


        _gv.setAdapter(adapter);
        return rootView;
    }
}

