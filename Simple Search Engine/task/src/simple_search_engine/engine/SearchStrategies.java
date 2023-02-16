package simple_search_engine.engine;

import simple_search_engine.file.ObtainDataFromFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class SearchStrategies implements SearchEngine {
    public Set<String> findAll(Map<String, ArrayList<Integer>> dataFromFile, String dataToSearch) {
        List<String> searchArg = new ArrayList<>();
        Set<String> searchRes = search(dataFromFile, dataToSearch);
        searchArg.add(dataToSearch);
        searchRes.retainAll(searchArg);
        return searchRes;
    }

    public List<String> findNone(Map<String, ArrayList<Integer>> dataFromFile, String dataToSearch) {
        List<String> noneValues = ObtainDataFromFile.searchValues;
        noneValues.removeAll(search(dataFromFile, dataToSearch));
        return noneValues;
    }

    public Set<String> findAny(Map<String, ArrayList<Integer>> dataFromFile, String dataToSearch) {
        return search(dataFromFile, dataToSearch);
    }
}
