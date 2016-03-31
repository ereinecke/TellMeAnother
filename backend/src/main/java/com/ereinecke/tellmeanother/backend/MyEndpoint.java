
package com.ereinecke.tellmeanother.backend;

import com.ereinecke.tellmeanother.javaJokes.Joker;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;


/** An endpoint class we are exposing */
@Api(
  name = "jokeApi",
  version = "1a",
  description = "An API serving random jokes",
  namespace = @ApiNamespace(
    ownerDomain = "backend.tellmeanother.ereinecke.com",
    ownerName   = "backend.tellmeanother.ereinecke.com",
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
