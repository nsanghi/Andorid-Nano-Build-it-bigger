package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;


public class MainActivity extends ActionBarActivity {

    private ProgressBar mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinner = (ProgressBar)findViewById(R.id.progress_bar);
        mSpinner.setVisibility(View.GONE);

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

        beginJokeFetching();


    }

    private void beginJokeFetching() {

        mSpinner.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask().execute(this);
    }

}
