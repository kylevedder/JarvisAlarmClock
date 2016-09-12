package piclock;

public class YahooWeather {
	private double temp;
	private double windSpeed;
	private String cityName;
	private String condition;

	public YahooWeather(double temp, double windSpeed, String cityName, String condition) {
		this.temp = temp;
		this.windSpeed = windSpeed;
		this.cityName = cityName;
		this.condition = formatCondition(condition);
	}

	private String formatCondition(String conditon) {
		if (conditon.toLowerCase().contains("thunderstorm") || conditon.toLowerCase().contains("rain")) {
			// Prepend with
			return " with " + conditon + " and ";
		}
		return conditon + " with ";
	}

	private String getWindString() {
		if (windSpeed < 5.0) {
			return "no wind";
		} else if (windSpeed < 10.0) {
			return String.format("light winds of approximatly %d miles per hour", (int) windSpeed);
		} else if (windSpeed < 20.0) {
			return String.format("moderate winds around %d miles per hour", (int) windSpeed);
		}
		return String.format("high winds around %d miles per hour", (int) windSpeed);
	}

	@Override
	public String toString() {
		return String.format("The weather in %s is currently %d degrees fahrenheit and %s %s.", cityName, (int) temp,
				condition, getWindString());
	}

}
