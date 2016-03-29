/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.ereinecke.backend;

import com.ereinecke.javaJokes.Joker;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;


/** An endpoint class we are exposing */
@Api(
  name = "jokeApi",
  version = "1a",
  description = "An API serving random jokes",
  namespace = @ApiNamespace(
    ownerDomain = "backend.ereinecke.com",
    ownerName   = "backend.ereinecke.com",
    packagePath = ""
  )
)
public class MyEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "tellJoke")
    public MyBean tellJoke() {
        MyBean response = new MyBean();
        response.setData(refreshJoke());

        return response;
    }

    /* pull random joke from javaJoke library */
    private String refreshJoke() {
        Joker myJoker = new Joker();
        return myJoker.getJoke();
    }

}
