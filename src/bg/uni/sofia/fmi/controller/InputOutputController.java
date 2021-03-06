package bg.uni.sofia.fmi.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import bg.uni.sofia.fmi.commands.*;

public class InputOutputController {

    private Map<String, Command> mapOfSupportedCommands;
    private static Scanner inScanner;

    public InputOutputController() {

        mapOfSupportedCommands = new HashMap<>();
        mapOfSupportedCommands.put("get-movie", new GetMovieCommand());
        mapOfSupportedCommands.put("get-tv-series", new GetTvSeriesCommand());
        mapOfSupportedCommands.put("get-movie-poster", new GetMoviePosterCommand());

        inScanner = new Scanner(System.in);
    }

    public void addNewSupportedCommand(String commandName, Command commandObject) {

        mapOfSupportedCommands.put(commandName, commandObject);

    }

    public String keyboardInput() {

        String resultString = new String();

        System.out.println("Enter command, please");

        resultString = inScanner.nextLine();
        return resultString;

    }

    private String[] splitWordsAndCommandsFromInputLine(String initialString) {

        String[] result = new String[2];

        if (!initialString.contains(" ")) {
            result[0] = initialString;
            return result;
        }

        for (int counter = 0; counter < initialString.length(); counter++) {

            if (initialString.charAt(counter) == ' ') {
                result[0] = initialString.substring(0, counter);
                result[1] = initialString.substring(counter + 1, initialString.length());
                break;
            }
        }

        return result;

    }

    public void run() {

        while (true) {

            String[] wordsInWholeLine = splitWordsAndCommandsFromInputLine(keyboardInput());

            String commandString = wordsInWholeLine[0];
            String fieldsString = wordsInWholeLine[1];

            if (commandString.equals("bye")) {
                break;
            }

            try {
                mapOfSupportedCommands.get(commandString).run(fieldsString);
            } catch (NullPointerException ex) {
                System.out.println("That is not a valid command, please try again :)");
            }

        }

        System.out.println("bye, bye :)");
        inScanner.close();
    }

    public static void main(String[] args) {

        InputOutputController test = new InputOutputController();
        test.run();

    }

}
