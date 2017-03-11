package com.example.srikanth.shopping_cart;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Srikanth on 3/6/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{


    public static final String DB_NAME = "ShoppingCart.db";
    private static final int DB_VERSION = 1;



    public static final String TABLE_USERS = "RegisterUsers";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRSTNAME ="name";
    public static final String COLUMN_LASTNAME = "LastName";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    public static final String CREATE_TABLE_USERS = "CREATE TABLE " +TABLE_USERS
            +"(" +COLUMN_ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT, " +COLUMN_EMAIL+
            " VARCHAR, " +COLUMN_PASSWORD+
            " VARCHAR, " +COLUMN_FIRSTNAME+
            " VARCHAR, " +COLUMN_LASTNAME+
            " VARCHAR);";


    public static final String TABLE_ITEMS = "ITEMS_LIST";
    public static final String ITEM_ID = "ID";
    public static final String ITEM_NAME ="ITEM_NAME";
    public static final String ITEM_PRICE = "ITEM_PRICE";
    public static final String ITEM_OFFER = "ITEM_OFFER";
    public static final String ITEM_DESCRIPTION = "DESCRIPTION";
    public static final String ITEM_OFFER_STATUS ="OFFER_STATUS";
    public static final String ITEM_CATEGERY ="CATAGERY";
    public static final String ITEM_IMAGE = "IMAGE";


    public static final String CREATE_TABLE_ITEMS = "CREATE TABLE " +TABLE_ITEMS
            +"(" +ITEM_ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT, " +ITEM_NAME+
            " VARCHAR, " +ITEM_PRICE+
            " INT, " +ITEM_OFFER+
            " VARCHAR, " +ITEM_DESCRIPTION+
            " VARCHAR, " +ITEM_OFFER_STATUS+
            " INT, " +ITEM_CATEGERY+
            " VARCHAR, " +ITEM_IMAGE+
            " VARCHAR);";


    public DatabaseHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("Oncreate",CREATE_TABLE_ITEMS);
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_ITEMS);
        ContentValues values = new ContentValues();
        values.put(ITEM_NAME,"Iphone 5s");
        values.put(ITEM_PRICE,"500");
        values.put(ITEM_OFFER,"20");
        values.put(ITEM_DESCRIPTION,"specifications");
        values.put(ITEM_OFFER_STATUS,String.valueOf(1));
        values.put(ITEM_CATEGERY,"ELECTRONICS");
        values.put(ITEM_IMAGE,"https://img.clipartfest.com/4851cd7a446f6fee0ca9d34fb79fe386_iphone-5-phone-icon-iphone-5s-clipart_256-256.png");
        db.insert(TABLE_ITEMS, null, values);


        values.put(ITEM_NAME,"Bru coffee");
        values.put(ITEM_PRICE,"10");
        values.put(ITEM_OFFER,"20");
        values.put(ITEM_DESCRIPTION,"ingredients");
        values.put(ITEM_OFFER_STATUS,String.valueOf(1));
        values.put(ITEM_CATEGERY,"GROCERIES");
        values.put(ITEM_IMAGE,"null");
        db.insert(TABLE_ITEMS, null, values);

        values.put(ITEM_NAME,"Dinning table");
        values.put(ITEM_PRICE,"299");
        values.put(ITEM_OFFER,"20");
        values.put(ITEM_DESCRIPTION,"Measurements");
        values.put(ITEM_OFFER_STATUS,String.valueOf(1));
        values.put(ITEM_CATEGERY,"HOME_DECOR");
        values.put(ITEM_IMAGE,"null");
        db.insert(TABLE_ITEMS, null, values);

        values.put(ITEM_NAME,"HTC_MOBILE");
        values.put(ITEM_PRICE,"250");
        values.put(ITEM_OFFER,"20");
        values.put(ITEM_DESCRIPTION,"specifications");
        values.put(ITEM_OFFER_STATUS,String.valueOf(1));
        values.put(ITEM_CATEGERY,"ELECTRONICS");
        values.put(ITEM_IMAGE,"null");
        db.insert(TABLE_ITEMS, null, values);
        //db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS" + TABLE_USERS;
        db.execSQL(sql);
        onCreate(db);

    }

    public void insertEntry(String loginName,String passwrd,String firName, String lastName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put(COLUMN_EMAIL, loginName);
        newValues.put(COLUMN_PASSWORD,passwrd);
        newValues.put(COLUMN_FIRSTNAME, firName);
        newValues.put(COLUMN_LASTNAME,lastName);
        db.insert(TABLE_USERS, null, newValues);
        db.close();
    }
    public Cursor getItemDetails()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {ITEM_NAME,ITEM_PRICE,ITEM_OFFER,ITEM_DESCRIPTION,ITEM_IMAGE};

        Cursor c = db.query(TABLE_ITEMS, projection, null, null, null, null, null);
        int i = c.getCount();
        Log.d("items count",String.valueOf(i));
        /*String name,offer;
        int price;
        while(c.moveToNext())
        {
            name = c.getString(c.getColumnIndex(ITEM_NAME));
            offer = c.getString(c.getColumnIndex(ITEM_OFFER));
            price = c.getInt(c.getColumnIndex(ITEM_PRICE));

            Log.d("Name ",name);
            Log.d("offer",offer);
            Log.d("price",String.valueOf(price));
        }*/
        return c;
    }
    public Cursor getCategoryDetails(String category)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {ITEM_NAME,ITEM_PRICE,ITEM_OFFER,ITEM_DESCRIPTION,ITEM_IMAGE};
        String selection = ITEM_CATEGERY + " = ?";
        String[] selectionArgs = {category};

        Cursor c = db.query(TABLE_ITEMS, projection, selection, selectionArgs, null, null, null);
        int i=c.getCount();
        return c;

    }


    public int getLogin(String email,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("email", email);
        Log.d("password getLogin",password);
        String sql = "SELECT  * FROM " + TABLE_USERS + " WHERE email=\'"
                + email+"\'" +" AND password=\'"+password+"\'";
        //String sql = "SELECT password FROM "+ TABLE_USERS + " WHERE name = \'"+email+"\';";
        Log.d("query",sql);
        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
        int i = c.getCount();
        Log.d("count",String.valueOf(i));
        if(c.getCount() == 1) {
        Log.d("count loop",String.valueOf(i));
        }
        return i;
    }
    public Cursor getDetails(String email,String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("getDetails",email);
        String[] projection = {COLUMN_PASSWORD,COLUMN_EMAIL,COLUMN_FIRSTNAME,COLUMN_LASTNAME};
        String selection = "email = ?";
        String[] selectionArgs = {email};
        Cursor c = db.query(TABLE_USERS, projection, selection, selectionArgs, null, null, null);
        int i = c.getCount();
        Log.d("users count",String.valueOf(i));
        String rowContent ="";
        while(c.moveToNext()){
            for(i=0;i<4;i++) {
                rowContent += c.getString(i);
            }
        }
        Log.d("database password",rowContent);
        return c;

    }
}
