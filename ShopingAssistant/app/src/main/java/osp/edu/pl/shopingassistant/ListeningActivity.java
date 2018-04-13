package osp.edu.pl.shopingassistant;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.speech.RecognizerIntent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import osp.edu.pl.shopingassistant.GoodsListDataContract.GoodsListData;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ListeningActivity extends AppCompatActivity {


   // private Button ok;
    private TextView article;
    private ImageButton btnSpeak;
    private ImageButton btnBack;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    GoodsListDataHelper dbObj;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbObj = new GoodsListDataHelper(getBaseContext());
        db = dbObj.getWritableDatabase();
        //ok = findViewById(R.id.button) ;
        setContentView(R.layout.activity_listening);
        article = findViewById(R.id.editText);
        btnSpeak = findViewById(R.id.btnSpeak);
        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

    }

    public void addArticle(View view){
        String text = article.getText().toString();
        if(text.length() < 2) return;
        else {
            //add add to database;
            ContentValues values = new ContentValues();
            values.put(GoodsListData.COLUMN_NAME_NAME, text.trim());
            long newRowId = db.insert(GoodsListData.TABLE_NAME, null, values);
            System.out.println("id of article added: " + newRowId);
            article.setText("");
            return;
        }

    }

    public void returnToList(View view){
            Intent intent = new Intent(this, ScrollingActivity.class);
            startActivity(intent);
    }

    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    article.setText(result.get(0));
                }
                break;
            }

        }
    }

    @Override
    protected void onDestroy() {
        dbObj.close();
        super.onDestroy();
    }

}
