package simple_search_engine.engine;

import simple_search_engine.file.ObtainDataFromFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public interface SearchEngine {
    default Set<String> search(Map<String, ArrayList<Integer>> dataFromFile, String dataToSearch) {
        Set<String> searchResult = new HashSet<>();
        String[] splitDataToSearch = dataToSearch.split("\\s+");
        Arrays.stream(splitDataToSearch).forEach(i -> {
            if (dataFromFile.containsKey(i)) {
                dataFromFile.get(i).forEach(j -> searchResult.add(ObtainDataFromFile.searchValues.get(j)));
            }
        });
        return searchResult;
    }
}
