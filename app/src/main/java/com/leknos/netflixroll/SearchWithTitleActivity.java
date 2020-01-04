package com.leknos.netflixroll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SearchWithTitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_with_title);
        getSupportActionBar().setTitle("Find Movie");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent intent;
        switch (item.getItemId()){
            case R.id.saved_movies :
                intent = new Intent(SearchWithTitleActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.search_with_title :
                intent = new Intent(SearchWithTitleActivity.this, SearchWithTitleActivity.class);
                startActivity(intent);
                break;
            case R.id.search_with_director :
                intent = new Intent(SearchWithTitleActivity.this, SearchWithPersonActivity.class);
                startActivity(intent);
                break;
            default: break;
        }

        return super.onOptionsItemSelected(item);
    }
}
