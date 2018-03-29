package bg.uni.sofia.fmi.commands;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.json.simple.parser.ParseException;

public class GetMoviePosterCommand implements Command {

	@Override
	public void run(String argument) {
		
		String movieName = argument;
		File posterFile = null;
		File imageFile = null;
		posterFile = FileHandler.makeAConnectionAndDownloadJSONFile(movieName);
		
		try {
			imageFile = FileHandler.downloadImage(posterFile);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().open(imageFile);
				//.delete();
			} catch (Exception e) {
				
			}
		}
		posterFile.delete();
		//imageFile.delete();
	}

}