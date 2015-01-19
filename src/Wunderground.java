
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
    private static String city = "";
    private static String zip = "";
    private static String state = "";
    private static String lat = "";
    private static String longt = "";

    public void getWeatherInfo(){
        try {

            URL url = new URL("http://api.wunderground.com/api/588b3bd34a976916/geolookup/q/19104.json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream inputStream = connection.getInputStream();

            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
            String strBuffer = "";
            String line = "";
            while( (line = buffer.readLine()) != null) {
                strBuffer += line;
            }

            JSONObject jsonObject = new JSONObject(strBuffer);
            JSONObject location = jsonObject.getJSONObject("location");
            city = location.get("city").toString();
            state = location.get("state").toString();
            lat = location.get("lat").toString();
            longt = location.get("lon").toString();
            zip = location.get("zip").toString();

            System.out.printf("\nYou are in - \n\t city: %s \n\t state: %s \n\t zip: %s \n\t latitude: %s \n\t longitude: %s", city, state, zip, lat, longt);

            System.out.println("\n========================================================");

            URL hourly_url = new URL("http://api.wunderground.com/api/588b3bd34a976916/hourly/q/" + state + "/" + zip + ".json");
            connection = (HttpURLConnection) hourly_url.openConnection();
            inputStream = connection.getInputStream();
            buffer = new BufferedReader(new InputStreamReader(inputStream));
            strBuffer = "";
            while ( (line = buffer.readLine()) != null) {
                strBuffer += "\n" + line;
            }

            JSONObject hourly = new JSONObject(strBuffer);
            JSONArray forecast = hourly.getJSONArray("hourly_forecast");
            for(int i =0; i < forecast.length(); i++) {
                JSONObject hour = (JSONObject) forecast.get(i);
                System.out.printf("\n\n%s \n\t condition: %s \n\t temperature: %sF \n\t feels like: %sF \n\t snow: %s \n\t relative humidity: %s ",
                        hour.getJSONObject("FCTTIME").get("pretty"),
                        hour.get("condition"),
                        hour.getJSONObject("temp").get("english"),
                        hour.getJSONObject("feelslike").get("english"),
                        hour.getJSONObject("snow").get("english"),
                        hour.get("humidity"));
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
