package bg.uni.sofia.fmi.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

public class GetTvSeriesCommand implements Command {

	@Override
	public void run(String argument) {

		String seriesName = null;
		File seriesFile = null;
		int seasonNumber = 0;
		List<String> stringList = null;

		seriesName = argument.split(" --")[0];
		seasonNumber = Integer.parseInt(argument.split(" --season=")[1]);

		seriesFile = FileHandler.makeAConnectionAndDownloadJSONFile(seriesName, seasonNumber);

		try {
			stringList = FileHandler.parseJSON(seriesFile);
		} catch (ParseException | IOException e) {
			System.out.println("Problem with parsing file");
		}

		for(String str : stringList) {
			System.out.println(str);
		}
		
		seriesFile.delete();

	}

}
