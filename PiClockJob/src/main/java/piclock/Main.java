package piclock;

import java.time.LocalDateTime;

public class Main {

	private static final String OUTPUT_MP3 = "/tmp/alarm.mp3";

	public static void main(String[] args) {

		 args = new String[] { "kyle", "01003" };

		if (args.length != 2) {
			argsAndQuit();
		}

		String name = args[0];
		String zipCode = args[1];

		TextToSpeech textToSpeech = TextToSpeech.getInstance();
		WeatherRequester weatherRequester = WeatherRequester.getInstance();
		Weather weather = weatherRequester.requestWeather(zipCode);
		System.out.println(weather);

		String textToSpeak = String.format("%s %s. %s. %s", getGreeting(), name, getTime(), weather.toString());
		System.out.println(textToSpeak);

		textToSpeech.synthesize(textToSpeak, OUTPUT_MP3);

		Utils.executeCommand("mpg123 " + OUTPUT_MP3);
	}

	private static String getGreeting() {
		LocalDateTime time = LocalDateTime.now();

		String greeting = "";
		if (time.getHour() < 12) {
			greeting = "Good morning";
		} else if (time.getHour() < 18) {
			greeting = "Good afternoon";
		} else {
			greeting = "Good evening";
		}
		return greeting;
	}

	private static String getTime() {
		LocalDateTime time = LocalDateTime.now();

		int twelveHr = time.getHour() % 12;
		if (twelveHr == 0) {
			twelveHr = 12;
		}

		String minute = "";
		if (time.getMinute() > 0) {
			if (time.getMinute() < 10) {
				minute += "o ";
			}
			minute += String.valueOf(time.getMinute());
		}
		String timeString = String.format("The time is %d %s %s", twelveHr, minute,
				(time.getHour() < 12) ? "AM" : "PM");

		return timeString;
	}

	public static void argsAndQuit() {
		System.out.println("Args: <user's name> <town name>");
		System.exit(-1);
	}

}
