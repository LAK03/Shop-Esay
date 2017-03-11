package com.example.srikanth.shopping_cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridView;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Button _myList;
    Button _myCheckout;
    Button _profile;
    GridView _gv;

    TabLayout tabLayout;



    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();


    public static DatabaseHelper db;
    ArrayList<ItemDetails> items = new ArrayList<ItemDetails>();



/*    Button _fbButton;
    Button _googleButton;
    Button _shopCartButton;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _myList =(Button) findViewById(R.id.mylist);
        _myCheckout =(Button) findViewById(R.id.checkout);
        _profile =(Button) findViewById(R.id.profile);
        _gv = (GridView) findViewById(R.id.gv_items);

        tabLayout =(TabLayout)findViewById(R.id.tabs);



        db = new DatabaseHelper(this);


        ViewPager viewPager = (ViewPager)findViewById(R.id.container);
        prepareDataresource();
        ItemsFragmentAdapter fadaptor = new ItemsFragmentAdapter(getSupportFragmentManager(),fragmentList,titleList);
        viewPager.setAdapter(fadaptor);
        tabLayout.setupWithViewPager(viewPager);


        _profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MainActivity.this,LoginRegActivity.class);
                startActivity(i);

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Shop Esay");
        toolbar.setLogo(R.drawable.cart_icon);
        //toolbar.setLogo(R.drawable.searchicon);

        setSupportActionBar(toolbar);



      /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void prepareDataresource()
    {
        fragmentList.add(new ForYouFragment());
        titleList.add("FOR YOU");
        fragmentList.add(new CategoryTab());
        titleList.add("CATEGORIES");
    }




    /*public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    ForYouTab fu = new ForYouTab();
                    return fu;
                case 1:
                    CategoryTab tab = new CategoryTab();
                    return tab;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "FOR YOU";
                case 1:
                    return "CATEGORIES";
            }
            return null;
        }
    }*/
}
