package io.marvingardens;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@EnableAsync
@ComponentScan(basePackages = "io.marvingardens")
public class AppMain {

	public static String scriptPath = "";

	public static void main(String[] args) {

		args = new String[] {
				"java -jar \"/home/kyle/code/Java/EclipseWorkspaces/eclipse-pi-alarm-clock/PiClockJob/target/PiClockJob-0.0.1-SNAPSHOT-jar-with-dependencies.jar\"" };
		if (args.length != 1) {
			printAndExit();
		}
		scriptPath = args[0];
		SpringApplication.run(AppMain.class, args);

	}

	private static void printAndExit() {
		System.out.println("Args: <script path>");
		System.exit(-1);
	}
}