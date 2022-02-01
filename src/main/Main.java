package main;

import checker.Checker;

import common.Constants;

import fileio.ChildrenUpdatesInputData;
import fileio.Input;
import fileio.InputLoader;
import fileio.Writer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import reading.Changes;
import reading.Children;
import reading.Gifts;

import writing.Write;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }

    /**
     * This method is used to call the checker which calculates the score
     *
     * @param args the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {

        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        if (outputDirectory.listFiles() != null) {
            for (File file : outputDirectory.listFiles()) {
                if (!file.delete()) {
                    System.out.println("nu s-a sters");
                }
            }
        }

        //This should fix that error as I no longer iterate through the files themselves
        for (int i = 1; i <= Constants.TESTS_NUMBER; i++) {
            String inPath = Constants.TESTS_PATH
                    + "test" + i
                    + Constants.FILE_EXTENSION;
            String outPath = Constants.OUTPUT_PATH
                    + i
                    + Constants.FILE_EXTENSION;
            File out = new File(outPath);
            if (out.createNewFile()) {
                action(inPath, outPath);
            }
        }

        Checker.calculateScore();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readInput();

        Writer fileWriter = new Writer(filePath2);
        JSONObject objectResult = new JSONObject();

        double numberOfYears = input.getNumberOfYears();
        double santaBudget = input.getSantaBudget();

        Children children = new Children(input.getChildren());
        Gifts santaGiftsList = new Gifts(input.getGifts());
        Changes changesList = new Changes(input.getChanges());

        Write write = new Write(children);
        JSONArray arrayResult = new JSONArray();
        JSONObject object = null;

        //Calculate stuff and print for the year 0

        Singleton santa = Singleton.getInstance();

        santa.setClient(new Client(children, santaBudget, santaGiftsList));

        santa.getClient().executeAction("RemoveYoungAdults");
        santa.getClient().executeAction("CalculateAverageScore");
        santa.getClient().executeAction("CalculateChildrenBudget");
        santa.getClient().executeAction("GiveChildrenGifts");
        object = write.returnChildren();
        arrayResult.add(arrayResult.size(), object);

        //Now Iterate for the rest of the years
        for (int i = 1; i <= numberOfYears; i++) {
            santaBudget = changesList.getChanges().get(i - 1).getNewSantaBudget();

            Children newChildren =
                    new Children(changesList.getChanges().get(i - 1).getNewChildren());

            ArrayList<ChildrenUpdatesInputData> childrenUpdates =
                    changesList.getChanges().get(i - 1).getChildrenUpdates();

            Gifts newGifts = new Gifts(changesList.getChanges().get(i - 1).getNewGifts());

            String strategy = changesList.getChanges().get(i - 1).getStrategy();

            santa.setClient(new Client(children, santaBudget, santaGiftsList,
                    newChildren, childrenUpdates, newGifts, strategy));

            santa.getClient().executeAction("GrowChildren");
            santa.getClient().executeAction("AddChildren");
            santa.getClient().executeAction("AddGifts");
            santa.getClient().executeAction("RemoveYoungAdults");
            santa.getClient().executeAction("UpdateChildren");
            santa.getClient().executeAction("CalculateAverageScore");
            santa.getClient().executeAction("CalculateChildrenBudget");
            santa.getClient().executeAction("GiveChildrenGifts");
            object = write.returnChildren();
            arrayResult.add(arrayResult.size(), object);
        }

        objectResult.put("annualChildren", arrayResult);

        fileWriter.closeJSON(objectResult);
    }
}
