package com.udacity.gradle.builditbigger.paid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.R;

import uby.luca.displaylib.DisplayJokeActivity;


public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.AsyncResponseListener {
    EndpointsAsyncTask endpointsAsyncTask;
    private ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);
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

    public void tellJoke(View view) {
//        Toast.makeText(this, "derp", Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, JokeTeller.getJoke(), Toast.LENGTH_LONG).show();
//        String joke = JokeTeller.getJoke();
        spinner.setVisibility(View.VISIBLE);
        endpointsAsyncTask = new EndpointsAsyncTask();
        endpointsAsyncTask.callback = this;
        endpointsAsyncTask.execute(this);

    }

    @Override
    public void processFinish(String joke) {
        Intent i = new Intent(this, DisplayJokeActivity.class);
        i.putExtra("JOKE_KEY", joke);
        spinner.setVisibility(View.GONE);
        startActivity(i);
    }
}






