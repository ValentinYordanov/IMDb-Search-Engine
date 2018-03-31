package bg.uni.sofia.fmi.commands;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.json.simple.parser.ParseException;

public class GetMoviePosterCommand implements Command {

    @Override
    public void run(String argument) {

        String movieName = argument;
        File posterFile = FileHandler.makeAConnectionAndDownloadJSONFile(movieName);
        File imageFile = null;

        try {
            imageFile = FileHandler.downloadImage(posterFile);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(imageFile);
            } catch (Exception e) {

            }
        }
        posterFile.delete();
    }

}