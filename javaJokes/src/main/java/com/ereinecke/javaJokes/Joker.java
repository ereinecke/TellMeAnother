package com.ereinecke.javaJokes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Random;

/**
 * Library that returns jokes from the Webknox joke API
 * https://market.mashape.com/webknox/jokes
 *
 * Erik Reinecke
 */
public class Joker {

    private static String LOG_TAG = Joker.class.getSimpleName();

    public Joker() {

        String title;
        String joke;
        String category;
        int rating;

    }

    /* Return a joke from JSON */
    public String getJoke() {

        String joke = randomJoke(JokeJSON());
        System.out.println(LOG_TAG + "in getJoke(), joke: " + joke);

        return joke;
    }

    private String randomJoke(String jokeJSON) {

        try {
            JSONParser jsonParser = new JSONParser();
            // get an array from the JSON object
            JSONArray jokeArray = (JSONArray) jsonParser.parse(jokeJSON);

            int numJokes = jokeArray.size();
            if (numJokes == 0) {
                System.out.println(LOG_TAG + "numJokes: " + numJokes);
                return null;
            }

            // pick a random joke from the array
            Random rnd = new Random();
            int index = rnd.nextInt(numJokes - 1);

            JSONObject jsonObject = (JSONObject) jokeArray.get(index);
            String jokeStr = (String) jsonObject.get("joke");

            System.out.println(LOG_TAG + "Here's the joke: " + jokeStr);

            return jokeStr;

        /* Exceptions not needed until we start pulling from API
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
         */
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // TODO: Convert this to pull data from WebKnowx Joke API
    /* Returns a selection of jokes in JSON format.  Currently, this is hardcoded but will
     * be replaced by a call to the WebKnox Joke API */
    private String JokeJSON() {
        return "[{\"title\":\"\",\"joke\":\"How can you tell elephants love to travel ?\\nThey are always packing their trunk !\",\"category\":\"Travel and tourist\",\"rating\":1.0},{\"title\":\"\",\"joke\":\"Mrs Jones: Now, remember, children, travel is very good for you. It broadens the mind. \\nBetty, muttering: If you\\u0027re anything to go by, that\\u0027s not all it broadens!\",\"category\":\"Travel and tourist\",\"rating\":1.0},{\"title\":\"\",\"joke\":\"How can you tell when witches are carrying a time bomb? \\nYou can hear their brooms tick!\",\"category\":\"Time\",\"rating\":1.0},{\"title\":\"\",\"joke\":\"One day, the wife comes home with a spectacular diamond ring. \\n\\n\\\"Where did you get that ring?\\\" her husband asks. \\n\\n\\\"Well, she replies, \\\"my boss and I played the lotto and we won, so I bought it with my share of the winnings. \\n\\nA week later, his wife comes home with a long shiny fur coat. \\n\\n\\\"Where did you get that coat?\\\" her husband asks. \\n\\nShe replies \\\"My boss and played the lotto and we won again, so I bought it with my share of the winnings. \\n\\nAnother week later, his wife comes home, driving in a red Ferrari. \\n\\n\\\"Where did you get that car?\\\" her husband asks. Again she repeats the same story about the lotto and her share of the winnings. \\n\\nThat night, his wife asks him to pour her a nice warm bath while she gets undressed. When she enters the bathroom, she find that there is barely enough water in the bath to cover the plug at the far end. \\n\\n\\\"And this?\\\" she asks her husband. \\\"Well,\\\" he replies, \\\"we don\\u0027t want to get your lotto ticket wet, do we?!\\\"\",\"category\":\"Lotto\",\"rating\":1.0},{\"title\":\"\",\"joke\":\"Taking his seat in his chambers, the judge faced the opposing lawyers. \\\"So,\\\" he said, \\\"I have been presented, by both of you, with a bribe.\\\" Both lawyers squirmed uncomfortably. \\\"You, attorney Leon, gave me $15,000. And you, attorney Campos, gave me $10,000.\\\"\\n\\nThe judge reached into his pocket and pulled out a check. He handed it to Leon ... \\\"Now then, I\\u0027m returning $5,000, and we\\u0027re going to decide this case solely on its merits.\\\"\",\"category\":\"Judge\",\"rating\":1.0},{\"title\":\"\",\"joke\":\"Can you show me how to use the Internet?\\nI\\u0027d better - otherwise you\\u0027ll just go round and round in circles.\",\"category\":\"Internet\",\"rating\":1.0},{\"title\":\"\",\"joke\":\"What\\u0027s O. J. Simpson\\u0027s Internet address? \\n\\nSlash, slash, backslash, slash, slash, escape.\",\"category\":\"Internet\",\"rating\":1.0},{\"title\":\"\",\"joke\":\"An idiot decided to start a chicken farm, so he bought a hundred chickens to start. A month later, he returned to the dealer for another hundred chickens because all of the first lot had died. A month later he was back at the dealers for another hundred chickens for the second lot had also died. \\\"But I think I know where I\\u0027m going wrong,\\\" said the idiot. \\\"I think I am planting them too deep.\\\"\",\"category\":\"Idiot\",\"rating\":1.0},{\"title\":\"\",\"joke\":\"An idiot decided to start a chicken farm, so he bought a hundred chickens to start. A month later, he returned to the dealer for another hundred chickens because all of the first lot had died. A month later he was back at the dealers for another hundred chickens for the second lot had also died. \\\"But I think I know where I\\u0027m going wrong,\\\" said the idiot. \\\"I think I am planting them too deep.\\\"\",\"category\":\"Idiot\",\"rating\":1.0},{\"title\":\"\",\"joke\":\"Peg-Leg Baldy A bald man with a peg leg gets invited to a costume party. Being shy and self-conscious about his appearance, he goes to the best costume shop in town. When he gets there, he tells the shop owner his situation and that he would rather cover his head and leg with a costume instead instead of exploiting his apparent problems. So, the shop owner comes back with a lifeguard costume. The man says, \\\"No, no. That will show off my peg leg. I can\\u0027t hide it with that. Try again.\\\" So the shop owner leaves and comes back with a monk costume And again the man says, \\\"No, no. I can\\u0027t wear that. It will make people notice my head.\\\" Obviously pissed off, the shop owner leaves and comes back with a five-pound bag of caramels, gives it to the man and says, \\\"Here. Just take this.\\\" Confused, the man says, \\\"What am I suposed to do with a bag of caramels?\\\" Smiling, the shop owner says, \\\"Take home this bag of caramels, melt them, pour it all over your body, stick that peg leg up your ass and tell everyone you\\u0027re a caramel apple.\\\"\",\"category\":\"Hair and bald\",\"rating\":1.0}]";
    }
}