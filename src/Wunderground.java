
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Justice on 1/12/2015.
 */
public class Wunderground {

    public void weatherInfo(){
        try {
            //we'll get the json data from the url and stick its head into the inputSteam for reading
            URL url = new URL("http://api.wunderground.com/api/588b3bd34a976916/geolookup/q/19104.json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();

            //we'll then read from the inputStream and stuff into a string called contents
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
            String contents = "";
            String line = "";
            while( (line = buffer.readLine()) != null) {
                contents += line;
            }

            //we parse the contents of the string to a json object for processing
            JSONObject jsonObject = new JSONObject(contents);
            JSONObject location = jsonObject.getJSONObject("location"); //the specific object we need

            //we'll use these two guys later for hourly forecast
            String state = location.get("state").toString();
            String zip = location.get("zip").toString();

            //gives a pretty nice output about location information
            System.out.printf("\nYou are in - \n\t city: %s \n\t state: %s \n\t zip: %s \n\t latitude: %s \n\t longitude: %s",
                    location.get("city").toString(),
                    state,
                    zip,
                    location.get("lat").toString(),
                    location.get("lon").toString());

            //marking boundaries
            System.out.println("\n=============================================");

            //using the state & zip, we construct a new url, download it and stick its head into the inputStream
            URL hourlyUrl = new URL("http://api.wunderground.com/api/588b3bd34a976916/hourly/q/" + state + "/" + zip + ".json");
            connection = (HttpURLConnection) hourlyUrl.openConnection();
            inputStream = connection.getInputStream();

            //again, we suck the contents of the inputStream and stuff into a string for future use
            buffer = new BufferedReader(new InputStreamReader(inputStream));
            contents = "";
            while ( (line = buffer.readLine()) != null) {
                contents += "\n" + line;
            }

            //we parse the contents to a our json object for processing
            JSONObject hourlyObject = new JSONObject(contents);
            JSONArray forecastArray = hourlyObject.getJSONArray("hourly_forecast");

            //we loop through our array and do some pretty printing of hourly weather information
            for(int i =0; i < forecastArray.length(); i++) {
                JSONObject hour = (JSONObject) forecastArray.get(i);
                System.out.printf("\n\n%s \n\t condition: %s \n\t temperature: %sF \n\t feels like: %sF \n\t snow: %s \n\t relative humidity: %s ",
                        hour.getJSONObject("FCTTIME").get("pretty"),
                        hour.get("condition"),
                        hour.getJSONObject("temp").get("english"),
                        hour.getJSONObject("feelslike").get("english"),
                        hour.getJSONObject("snow").get("english"),
                        hour.get("humidity"));
            }

        } catch (MalformedURLException rat) {
            rat.printStackTrace(); //I see you
        } 
        catch (Exception rat) {
            rat.printStackTrace();
        }
    }
}
