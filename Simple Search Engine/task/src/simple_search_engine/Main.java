package simple_search_engine;

import simple_search_engine.engine.SearchEngine;
import simple_search_engine.file.ObtainDataFromFile;
import simple_search_engine.users.UserOptions;

public class Main implements SearchEngine {
    private static final UserOptions userOptions = new UserOptions();
    private static final ObtainDataFromFile dataFromFile = new ObtainDataFromFile();

    public static void main(String[] args) {
        dataFromFile.getDataFromFile(args);
        userOptions.searchMenu();
    }
}
