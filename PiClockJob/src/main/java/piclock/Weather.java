package piclock;

public class Weather {
	private double temp;
	private double windSpeed;
	private WeatherType type;

	public Weather(double temp, double windSpeed, int weatherID) {
		this.temp = temp;
		this.windSpeed = windSpeed;
		this.type = genType(weatherID);
	}

	public Weather() {
		this.temp = 0;
		this.windSpeed = 0;
		this.type = WeatherType.UNKNOWN;
	}

	private WeatherType genType(int id) {
		if(id == 0 || id == 800)
		{
			return WeatherType.CLEAR;
		}
		else if(id <= 233)
		{
			return WeatherType.THUNDER;
		}
		else if(id <= 321)
		{
			return WeatherType.DRIZZLE;
		}
		else if(id <= 531)
		{
			return WeatherType.RAIN;
		}
		else if(id <= 622)
		{
			return WeatherType.SNOW;
		}
		else if(id <= 701)
		{
			return WeatherType.MIST;
		}		
		else
		{
			return WeatherType.UNKNOWN;
		}
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}
	
	private String getWindString()
	{
		if(windSpeed < 5.0)
		{
			return "no wind";
		}
		else if(windSpeed < 10.0)
		{
			return String.format("light winds of approximatly %d miles per hour", (int) windSpeed);
		}
		else if(windSpeed < 20.0)
		{
			return String.format("moderate winds around %d miles per hour", (int) windSpeed);
		}
		return String.format("high winds around %d miles per hour", (int) windSpeed);
	}
	
	@Override
	public String toString() {
		String print = "";
		switch (type) {
		case CLEAR:
			print = String.format("It is currently %d degrees fahrenheit and clear with %s.", (int)temp, getWindString());
			break;

		case THUNDER:
			print = String.format("It is currently %d degrees fahrenheit with scattered thunder storms and %s.", (int)temp, getWindString());
			break;
			
		case DRIZZLE:
			print = String.format("It is currently %d degrees fahrenheit and drizzling with %s.", (int)temp, getWindString());
			break;
			
		case RAIN:
			print = String.format("It is currently %d degrees fahrenheit and raining with %s.", (int)temp, getWindString());
			break;
		
		case SNOW:
			print = String.format("It is currently %d degrees fahrenheit and snowing with %s.", (int)temp, getWindString());
			break;
			
		case CLOUDY:
			print = String.format("It is currently %d degrees fahrenheit and cloudy with %s.", (int)temp, getWindString());
			break;
			
		case MIST:
			print = String.format("It is currently %d degrees fahrenheit with a slight mist and %s.", (int)temp, getWindString());
			break;
			
		case UNKNOWN:
		default:
			print = String.format("It is currently %d degrees fahrenheit with %s.", (int)temp, getWindString());
			break;
		}
		return print;
	}

}

enum WeatherType{
	CLEAR, THUNDER, DRIZZLE, RAIN, SNOW, MIST, CLOUDY, UNKNOWN
}