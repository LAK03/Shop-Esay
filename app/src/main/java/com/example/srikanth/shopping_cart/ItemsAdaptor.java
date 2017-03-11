package com.example.srikanth.shopping_cart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.HttpAuthHandler;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Srikanth on 3/8/2017.
 */

public class ItemsAdaptor extends BaseAdapter{

    private Context con;
    private final ArrayList<ItemDetails> sItems;

    public ItemsAdaptor(Context con, ArrayList<ItemDetails> sItems) {
        this.con = con;
        this.sItems = sItems;
    }



    @Override
    public int getCount() {
        return sItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View gv_items;
        LayoutInflater inflater = (LayoutInflater) con
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {

            gv_items = new View(con);
            gv_items = inflater.inflate(R.layout.activity_grid, null);
            TextView textView = (TextView) gv_items.findViewById(R.id.grid_text);
            TextView textPrice = (TextView) gv_items.findViewById(R.id.grid_price);
            TextView textOffer = (TextView) gv_items.findViewById(R.id.grid_offer);
            ImageView imgview = (ImageView) gv_items.findViewById(R.id.imgId);
            Bitmap bitmap= getBitmapFromURL(sItems.get(position).getIcon());
            String itemName = sItems.get(position).get_itemName();

            textView.setText("Product:"+itemName);
            textPrice.setText("Price:"+sItems.get(position).get_itemprice());
            textOffer.setText("Offer:"+sItems.get(position).get_itemOffer());
            if(itemName.equals("Iphone 5s"))
                imgview.setImageResource(R.drawable.iphone);
            if(itemName.equals("Bru coffee"))
                imgview.setImageResource(R.drawable.bru);
            if(itemName.equals("Dinning table"))
                imgview.setImageResource(R.drawable.dinning);
            if(itemName.equals("HTC_MOBILE"))
                imgview.setImageResource(R.drawable.htc);
            //imgview.setImageBitmap(bitmap);




        } else {
            gv_items = (View) convertView;
        }
        return gv_items;
    }
    public Bitmap getBitmapFromURL(String src)
    {
        try{

            URL url = new URL(src);
            Log.d("source",src);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            try{
                connection.connect();
            }catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }

            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
