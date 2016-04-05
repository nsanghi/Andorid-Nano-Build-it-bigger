package com.udacity.gradle.builditbigger;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.lang.Override;


public class MainActivity extends ActionBarActivity {

    InterstitialAd mInterstitialAd;
    private ProgressBar mSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinner = (ProgressBar)findViewById(R.id.progress_bar);
        mSpinner.setVisibility(View.GONE);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                beginJokeFetching();
            }
        });

        requestNewInterstitial();
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

    @Override
    protected void onPause() {
        mSpinner.setVisibility(View.GONE);
        super.onPause();
    }

    public void tellJoke(View view){

        //JokeGenerator jokeGenerator = new JokeGenerator();
        //String joke = jokeGenerator.getJoke();

        // commented Toast to launch a new activity and pass the joke as
        // Intent extra to the new activity in another Lib Project
        // Toast.makeText(this, joke, Toast.LENGTH_LONG).show();

        // commented to have joke serviced from GCE
        /*
        Intent intent = new Intent(this, JokeDisplayActivity.class);
        intent.putExtra(JokeDisplayActivity.JOKE_KEY, joke);
        startActivity(intent);
        */

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            beginJokeFetching();
        }


    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                  .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                  .build();

        mInterstitialAd.loadAd(adRequest);
    }

    private void beginJokeFetching() {

        mSpinner.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask().execute(this);
    }

}
