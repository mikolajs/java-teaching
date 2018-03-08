package osp.edu.pl.shopingassistant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ListeningActivity extends AppCompatActivity {

    public static final String ARTICLE_MESSAGE = "osp.edu.pl.shopingassistant.MESSAGE";


   // private Button ok;
    private TextView article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ok = findViewById(R.id.button) ;
        setContentView(R.layout.activity_listening);
        article = findViewById(R.id.articleInput);

    }

    public void addArticle(View view){
        String text = article.getText().toString();
        if(text.length() < 2) return;
        else {
            Intent intent = new Intent(this, ScrollingActivity.class);
            intent.putExtra(ARTICLE_MESSAGE, text);
            startActivity(intent);
        }

    }


}
