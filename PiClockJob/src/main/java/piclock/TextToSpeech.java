package piclock;


/*
 * Copyright 2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 *    http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.ivona.services.tts.IvonaSpeechCloudClient;
import com.ivona.services.tts.model.CreateSpeechRequest;
import com.ivona.services.tts.model.CreateSpeechResult;
import com.ivona.services.tts.model.Input;
import com.ivona.services.tts.model.Voice;


/**
 * Class that generates sample synthesis and retrieves audio stream.
 */
public class TextToSpeech {

	private static TextToSpeech textToSpeech = null;

	public static TextToSpeech getInstance() {
		if (textToSpeech == null)
			textToSpeech = new TextToSpeech();
		return textToSpeech;
	}

	private IvonaSpeechCloudClient speechCloud;

	private TextToSpeech() {
		speechCloud = new IvonaSpeechCloudClient(
				new ClasspathPropertiesFileCredentialsProvider("/IvonaCredentials.properties"));
		speechCloud.setEndpoint("https://tts.eu-west-1.ivonacloud.com");
	}

	public void synthesize(String text, String outputFileName) {

		System.out.println("Init done...");

		CreateSpeechRequest createSpeechRequest = new CreateSpeechRequest();
		Input input = new Input();
		Voice voice = new Voice();

		voice.setName("Brian");
		input.setData(text);

		createSpeechRequest.setInput(input);
		createSpeechRequest.setVoice(voice);
		InputStream in = null;
		FileOutputStream outputStream = null;

		try {

			CreateSpeechResult createSpeechResult = speechCloud.createSpeech(createSpeechRequest);

			System.out.println("\nSuccess sending request:");
			System.out.println(" content type:\t" + createSpeechResult.getContentType());
			System.out.println(" request id:\t" + createSpeechResult.getTtsRequestId());
			System.out.println(" request chars:\t" + createSpeechResult.getTtsRequestCharacters());
			System.out.println(" request units:\t" + createSpeechResult.getTtsRequestUnits());

			System.out.println("\nStarting to retrieve audio stream:");

			in = createSpeechResult.getBody();
			outputStream = new FileOutputStream(new File(outputFileName));

			byte[] buffer = new byte[2 * 1024];
			int readBytes;

			while ((readBytes = in.read(buffer)) > 0) {
				// In the example we are only printing the bytes counter,
				// In real-life scenario we would operate on the buffer
				System.out.println(" received bytes: " + readBytes);
				outputStream.write(buffer, 0, readBytes);
			}

			System.out.println("\nFile saved: " + outputFileName);


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
