package simple_search_engine.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class ObtainDataFromFile {
    public static Map<String, ArrayList<Integer>> splitValues = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private static final String DATA = "--data";
    public static ArrayList<String> searchValues = new ArrayList<>();

    public void getDataFromFile(String[] args) {
        if (DATA.equals(args[0])) {
            try (Scanner fileReader = new Scanner(new File(args[1]))) {
                while (fileReader.hasNextLine()) {
                    searchValues.add(fileReader.nextLine());
                }

            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
            splitItemsToSpeedUpSearch(searchValues);
        }
    }

    private void splitItemsToSpeedUpSearch(List<String> dataToSplit) {
        String[] stockArr = new String[dataToSplit.size()];
        dataToSplit.toArray(stockArr);
        int index = 0;

        for (String s : dataToSplit) {
            stockArr = s.split("\\s+");

            for (String word : stockArr) {
                if (splitValues.containsKey(word)) {
                    splitValues.get(word).add(index);
                    splitValues.put(word, splitValues.get(word));
                } else {
                    ArrayList<Integer> tempArray = new ArrayList<>();
                    tempArray.add(index);
                    splitValues.put(word, tempArray);
                }
            }
            index++;
        }
    }
}
