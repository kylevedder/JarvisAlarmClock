package piclock;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherRequester {
	private String APIkey;

	private static WeatherRequester weatherRequester = null;

	public static WeatherRequester getInstance() {
		if (weatherRequester == null)
			weatherRequester = new WeatherRequester();
		return weatherRequester;
	}

	private WeatherRequester() {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = getClass().getResourceAsStream("/WeatherCredentials.properties");

			if (input != null) {
				// load a properties file
				prop.load(input);

				APIkey = prop.getProperty("accessKey");
				System.out.println(APIkey);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Weather requestWeather(String zipCode) {

		zipCode = zipCode.replace(" ", "%20");

		String data = Utils.getRequest("http://api.openweathermap.org/data/2.5/weather?units=imperial&appid=" + APIkey
				+ "&zip=" + zipCode + ",us");

		double temp = 0;
		double windSpeed = 0;
		int weatherID = 0;

		// create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();

		// read JSON like DOM Parser
		JsonNode rootNode;
		try {
			rootNode = objectMapper.readTree(data);

			JsonNode mainInfoNode = rootNode.path("main");
			JsonNode weatherInfoArray = rootNode.path("weather");
			
			JsonNode tempNode = mainInfoNode.path("temp");

			JsonNode windInfoNode = rootNode.path("wind");
			JsonNode windSpeedNode = windInfoNode.path("speed");

			JsonNode weatherIdNode = weatherInfoArray.findValue("id");
			

			temp = tempNode.asDouble();
			windSpeed = windSpeedNode.asDouble();
			weatherID = weatherIdNode.asInt();

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new Weather(temp, windSpeed, weatherID);
	}

}
