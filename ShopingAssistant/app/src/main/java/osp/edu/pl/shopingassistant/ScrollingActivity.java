package osp.edu.pl.shopingassistant;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import osp.edu.pl.shopingassistant.GoodsListDataContract.GoodsListData;

public class ScrollingActivity extends AppCompatActivity {

    ListView itemsList;
    String message = null;
    GoodsArrayAdapter customAdapter;
    ArrayList<String> arr;
    GoodsListDataHelper dbObj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbObj = new GoodsListDataHelper(getBaseContext());
        setContentView(R.layout.scroll_activity);
        Toolbar toolbar =  findViewById(R.id.main_toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_trolley);
        setSupportActionBar(toolbar);
        itemsList = findViewById(R.id.listView);
        loadGoodsFromDB();
        customAdapter = new GoodsArrayAdapter(getBaseContext(),
                android.R.layout.simple_list_item_multiple_choice,
                arr);
        itemsList.setAdapter(customAdapter);
        //itemsList.setBackgroundColor(Color.BLACK);

//        itemsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//            @Override
//            public void onItemClick(AdapterView<?> parent, final View view,
//                                    int position, long id) {
//                final String item = (String) parent.getItemAtPosition(position);
//                view.animate().setDuration(2000).alpha(0)
//                        .withEndAction(new Runnable() {
//                            @Override
//                            public void run() {
//                                list.remove(item);
//                                adapter.notifyDataSetChanged();
//                                view.setAlpha(1);
//                            }
//                        });
//            }
//
//        });



        //if(message != null) {
        //    customAdapter.addItem(message);
       // }
        //only for test, replace by adding checkbox to list
        //Toast.makeText(getApplicationContext(), message,
        //        Toast.LENGTH_SHORT).show();

        FloatingActionButton fab = findViewById(R.id.runAddThing);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goListening(view);

            }
        });
    }

    public void goListening(View view){
        Intent intent = new Intent(this, ListeningActivity.class);
        startActivity(intent);
    }

    public void delGoods(View view){
        int itemsNum = itemsList.getAdapter().getCount();
        SparseBooleanArray checked = itemsList.getCheckedItemPositions();
        ArrayList<String> itemsToDel = new ArrayList<>();
        SQLiteDatabase db = dbObj.getWritableDatabase();
        for(int i = 0; i < itemsNum; i++){
            if(checked.get(i)) itemsToDel.add(customAdapter.getItem(i));
        }
        String selection = GoodsListData.COLUMN_NAME_NAME + " IN (";
        for( int i = 1; i < itemsToDel.size(); i++)
            selection += "?, ";
        selection += "?)";

        String[] selectionArgs = itemsToDel.toArray(new String[0]);

        int deletedRows = db.delete(GoodsListData.TABLE_NAME, selection, selectionArgs);
        System.out.println("TO DELETED ITEMS " + deletedRows);
        db.close();
        loadGoodsFromDB();
        customAdapter = new GoodsArrayAdapter(getBaseContext(),
                android.R.layout.simple_list_item_multiple_choice,
                arr);
        itemsList.setAdapter(customAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_del) {
            delGoods(getCurrentFocus());
        } else if(id == R.id.action_add) {
            goListening(getCurrentFocus());
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadGoodsFromDB(){
        arr = new ArrayList<>();
        SQLiteDatabase db = dbObj.getReadableDatabase();
        Cursor cursor = db.query(
                GoodsListData.TABLE_NAME,
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        System.out.println("Load from DB");
        System.out.println("Nubmer of column" +cursor.getCount());
        while(cursor.moveToNext())
          arr.add(cursor.getString(1));
        cursor.close();
        db.close();
    }



    @Override
    protected void onDestroy() {
        dbObj.close();
        super.onDestroy();
    }



}
