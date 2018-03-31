package bg.uni.sofia.fmi.commands;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

public class GetTvSeriesCommand implements Command {

    @Override
    public void run(String argument) {

        String seriesName = argument.split(" --")[0];
        int seasonNumber = Integer.parseInt(argument.split(" --season=")[1]);
        File seriesFile = FileHandler.makeAConnectionAndDownloadJSONFile(seriesName, seasonNumber);
        List<String> episodeNames = null;

        try {
            episodeNames = FileHandler.parseJSON(seriesFile);
        } catch (ParseException | IOException e) {
            System.out.println("Problem with parsing file");
        }

        for (String str : episodeNames) {
            System.out.println(str);
        }

        seriesFile.delete();

    }

}
