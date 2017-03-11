package com.example.srikanth.shopping_cart;

import android.graphics.Bitmap;

/**
 * Created by Srikanth on 3/8/2017.
 */

public class ItemDetails {
    private String _itemName;
    private String _itemprice;
    private String _itemOffer;
    private String _itemDesc;
    private String icon;

    public ItemDetails(String _itemName, String _itemprice, String _itemOffer, String _itemDesc, String icon)
    {
        this._itemName = _itemName;
        this._itemprice = _itemprice;
        this._itemOffer = _itemOffer;
        this._itemDesc = _itemDesc;
        this.icon = icon;

    }
    public String get_itemName()
    {
        return this._itemName;
    }
    public String get_itemprice()
    {
        String temp = "$"+this._itemprice;
        return temp;
    }
    public String get_itemOffer()
    {
        String temp = this._itemOffer+"%";
        return temp;
    }
    public String get_itemDesc()
    {
        return this._itemDesc;
    }
    public String getIcon()
    {
        return this.icon;
    }
    public void set_itemName(String name)
    {
        this._itemName = name;
    }

    public void set_itemprice(String price)
    {
        this._itemprice = price;
    }
    public void set_itemOffer(String offer)
    {
        this._itemOffer = offer;
    }
    public void set_itemDesc(String desc)
    {
        this._itemDesc = desc;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }



}
