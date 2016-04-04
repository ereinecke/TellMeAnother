package com.ereinecke.tellmeanother;

/* Many, many thanks to Matt_from_Pestulon
*  https://discussions.udacity.com/t/async-task-test-where-to-even-begin/159593/4
*  */

import android.content.Context;
import android.test.AndroidTestCase;

import org.mockito.Mock;

import java.util.concurrent.TimeUnit;


public class testEndpointsAsyncTask extends AndroidTestCase {

        EndpointsAsyncTask task;
        String result;
        @Mock Context mockContext;

        @Override
        protected void setUp() throws Exception {
            super.setUp();

            result = null;
            task = new EndpointsAsyncTask(mockContext) {
                // We're overriding onPostExecute because we don't want to launch the intent
                // we do want to test if the context is being passed (see notes from josen on
                // above post
                @Override
                protected void onPostExecute(String joke) {
                    assertNotNull(mockContext);
                }
            };
        }

        public void testAsyncReturnType() {

            try {
                // Default timeout for the GCM server is 20 seconds
                // if the .get can't get the result in 10 seconds, something is wrong anyway
                // Greater than 20 seconds results in a error string returned and requires further
                // investigation
                task.execute();
                result = task.get(10, TimeUnit.SECONDS);
                assertNotNull(result);
            } catch (Exception e) {
                fail("Timed out");
        }
    }


}