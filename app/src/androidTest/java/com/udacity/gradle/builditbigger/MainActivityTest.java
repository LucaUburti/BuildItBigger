package com.udacity.gradle.builditbigger;

import android.support.test.InstrumentationRegistry;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;


public class MainActivityTest implements EndpointsAsyncTask.AsyncResponseListener {
    private String joke = null;
    private EndpointsAsyncTask endpointsAsyncTask;
    private CountDownLatch signal = new CountDownLatch(1);

    @Test
    public void checkAsyncTask() throws InterruptedException {
        endpointsAsyncTask = new EndpointsAsyncTask();
        endpointsAsyncTask.callback = this;
        endpointsAsyncTask.execute(InstrumentationRegistry.getContext());

        signal.await();
        if (joke.contains("failed to connect")) {
            Assert.fail("failed to connect!");
        }

        Assert.assertNotNull(joke);
    }

    @Override
    public void processFinish(String output) {
        joke = output;
        signal.countDown();

    }
}