

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by justiceogbonna on 1/12/15.
 * ps622@drexel
 */
public class Warmup {

    public void warm() {
        try {

            URL url = new URL("https://www.cs.drexel.edu/~wmm24/cs275_wi15/labs/wxunderground.html");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = connection.getInputStream();

            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            //while( (line = buffer.readLine()) != null)
             //   System.out.println(line);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
