package bg.uni.sofia.fmi.commands;

import java.io.File;
import java.io.IOException;

import org.json.simple.parser.ParseException;

public class GetMovieCommand implements Command {

	@Override
	public void run(String argument) {

		String movieName = null;
		File movieFile = null;
		boolean hasFields = false;
		String[] commandFields = null;

		if (!argument.contains("--fields=")) {

			movieName = argument;

		} else {

			movieName = argument.split(" --")[0];
			String movieFields = argument.split(" --fields=")[1];
			if (movieFields.contains(",")) {
				commandFields = movieFields.split(",");
			} else {
				commandFields = new String[1];
				commandFields[0] = movieFields;
			}

			hasFields = true;
		}

		movieFile = FileHandler.makeAConnectionAndDownloadJSONFile(movieName);

		if (hasFields) {
			for (int i = 0; i < commandFields.length; i++) {
				try {
					System.out.println(FileHandler.parseJSONForMovies(commandFields[i], movieFile));
				} catch (ParseException | IOException e) {
					System.out.println("Problem with parsing file");
				}
			}
		} else {
			try {
				System.out.println(FileHandler.getEveryThingFromFile(movieFile));
			} catch (IOException e) {
				System.out.println("problem with getting everything from file in command class");
			}
		}

		movieFile.delete();

	}

}
