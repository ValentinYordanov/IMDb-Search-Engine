package bg.uni.sofia.fmi.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileHandler {

	public static File makeAConnectionAndDownloadJSONFile(String movieName) {

		movieName = movieName.replace(" ", "+");

		String URLString = "http://www.omdbapi.com/?apikey=f21dfb64&t=" + movieName;

		URL url = null;
		try {
			url = new URL(URLString);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		String inputLine;

		try (FileWriter fw = new FileWriter(movieName + ".txt");
				BufferedWriter output = new BufferedWriter(fw);
				BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
			while ((inputLine = br.readLine()) != null) {
				output.write(inputLine);
			}
		} catch (IOException e) {
			System.out.println("There was an IOException while trying to write the API info to the file");
		}

		File file = new File(movieName + ".txt");
		return file;

	}

	// not done yet!
	public static File makeAConnectionAndDownloadJSONFile(String seriesName, int seasonNumber) {

		seriesName = seriesName.replace(" ", "+");

		String URLString = "http://www.omdbapi.com/?apikey=f21dfb64&t=" + seriesName;

		URL url = null;
		try {
			url = new URL(URLString);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		String inputLine;

		try (FileWriter fw = new FileWriter(seriesName + ".txt");
				BufferedWriter output = new BufferedWriter(fw);
				BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
			while ((inputLine = br.readLine()) != null) {
				output.write(inputLine);
			}
		} catch (IOException e) {
			System.out.println("There was an IOException while trying to write the API info to the file");
		}

		File file = new File(seriesName + ".txt");
		return file;

	}

	public static String parseJSONForMovies(String whatToParse, File fileName) throws ParseException, IOException {

		String parseResult = "";
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(getEveryThingFromFile(fileName));
		JSONObject jsonObj = (JSONObject) obj;

		parseResult = (String) jsonObj.get(whatToParse);

		return parseResult;
	}

	public static String getEveryThingFromFile(File fileName) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line;
		StringBuilder result = new StringBuilder();
		while ((line = br.readLine()) != null) {
			result.append(line);
		}
		br.close();
		return result.toString().replace("\n", "").replace("\r", "");
	}
}
