import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    public static String getWeather(String message, Model model) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=d7ecf196c5b071e72ea2f9ed05e00ae7");

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";

        while (in.hasNext()) {
            result += in.nextLine();
        }

        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemperature(main.getDouble("temperature"));
        model.setHumidity(main.getDouble("humidity"));

        JSONArray getArray = object.getJSONArray("wheather");
        for(int i = 0; i < getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            model.setMain((String) obj.get("main"));
        }

        return "Localização: " + model.getName() + ", Brazil" + "\n" +
                "Temperatura: " + model.getTemperature() + "C" + "\n" +
                "Humidade: " + model.getHumidity() + "%" + "\n" +
                "Clima: " + model.getMain();
    }
}
