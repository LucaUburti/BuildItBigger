package com.udacity.gradle.builditbigger.free;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.R;

import uby.luca.displaylib.DisplayJokeActivity;


public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.AsyncResponseListener {
    EndpointsAsyncTask endpointsAsyncTask;
    private InterstitialAd mInterstitialAd;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this,
                "ca-app-pub-3940256099942544~3347511713");

        spinner = findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mInterstitialAd = new InterstitialAd(this);
        setInterstitialAdListener();
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    private void setInterstitialAdListener() {
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                //Code to be executed when an ad request fails.
                loadJoke();
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                loadJoke();
            }
        });
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

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show(); //loadJoke will be called after the user closes the Ad (or if the Ad doesn't load)
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
            loadJoke(); //if the ad isn't ready yet, just display the joke
        }
    }

    private void loadJoke() {
        spinner.setVisibility(View.VISIBLE);
        endpointsAsyncTask=new EndpointsAsyncTask();
        endpointsAsyncTask.callback =this;
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






