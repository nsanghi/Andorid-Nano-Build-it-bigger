package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.test.AndroidTestCase;

import org.mockito.Mock;

import java.util.concurrent.TimeUnit;

/**
 * Created by nimish on 3/4/16.
 */
public class TestAsync extends AndroidTestCase {

    EndpointsAsyncTask task;
    String result;
    @Mock Context mockContext;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        result = null;
        task = new EndpointsAsyncTask() {

            @Override
            protected void onPostExecute(String joke) {
            }
        };
    }

    public void testAsyncFetch() {

        try {
            task.execute(mockContext);
            result = task.get(10, TimeUnit.SECONDS);
            assertNotNull(result);
        } catch (Exception e) {
            fail("Timed out");
        }

    }
}
