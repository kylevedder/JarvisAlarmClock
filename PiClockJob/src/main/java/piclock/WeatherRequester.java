package piclock;

import java.io.IOException;
import java.io.InputStream;
import java.io.*;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherRequester {
	private static WeatherRequester weatherRequester = null;

	public static WeatherRequester getInstance() {
		if (weatherRequester == null)
			weatherRequester = new WeatherRequester();
		return weatherRequester;
	}

	private WeatherRequester() {
	}

	public YahooWeather requestWeather() {

		String data = "";
		try {
			data = Utils.getRequest(
					"https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%3D12758287%20and%20u%3D%22f%22&format=json");
		} catch (Exception ex) {
		}
		double temp = 0;
		double windSpeed = 0;
		String condition = "";
		String cityName = "";

		// create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();

		// read JSON like DOM Parser
		JsonNode rootNode;
		try {
			rootNode = objectMapper.readTree(data);

			JsonNode mainInfoNode = rootNode.path("query").path("results").path("channel");

			JsonNode cityNameNode = mainInfoNode.path("location").path("city");

			JsonNode tempNode = mainInfoNode.path("item").path("condition").path("temp");

			JsonNode conditionNode = mainInfoNode.path("item").path("condition").path("text");

			JsonNode windSpeedNode = mainInfoNode.path("temp").path("speed");

			temp = tempNode.asDouble();
			windSpeed = windSpeedNode.asDouble();
			cityName = cityNameNode.asText();
			condition = conditionNode.asText();

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new YahooWeather(temp, windSpeed, cityName, condition);
	}

}
