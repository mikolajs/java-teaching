package osp.edu.pl.shopingassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class ScrollingActivity extends AppCompatActivity {

    ListView itemsList;
    String message = null;
    GoodsArrayAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_activity);
        Toolbar toolbar =  findViewById(R.id.main_toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_trolley);
        setSupportActionBar(toolbar);
        itemsList = findViewById(R.id.listView);
        customAdapter = new GoodsArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_multiple_choice,
                new ArrayList<String>());
        itemsList.setAdapter(customAdapter);

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

        Intent intent = getIntent();

        message = intent.getStringExtra(ListeningActivity.ARTICLE_MESSAGE);

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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
