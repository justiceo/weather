

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by justiceogbonna on 1/12/15.
 * ps622@drexel - some guy I did this with, or kinda watched me do it.
 */

/* Warm up program to test downloading information from url:
 *
 */
public class Warmup {

    /* Downloads contents from a url and prints it
     * @pre-condition: true
     * @post-condition: homework page is printed on screen
     */
    public void turnUpTheHeat() {
        try {

            //download the data from the url and pipe into the inputstream
            URL url = new URL("https://www.cs.drexel.edu/~wmm24/cs275_wi15/labs/wxunderground.html");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();

            //suck the pipe contents into a string and print it
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while( (line = buffer.readLine()) != null)
               System.out.println(line); // prints the content of the url, uhm sorry :)

        }
        //catch url exception for errors
        catch (MalformedURLException rat) {
            rat.printStackTrace();
        }
        //all other exceptions for debugging purposes
        catch (Exception rat) {
            rat.printStackTrace();
        }
    }
}
