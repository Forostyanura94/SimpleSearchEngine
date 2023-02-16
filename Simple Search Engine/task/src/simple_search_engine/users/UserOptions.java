package simple_search_engine.users;

import simple_search_engine.file.ObtainDataFromFile;
import simple_search_engine.engine.SearchStrategies;

import java.util.Arrays;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class UserOptions {
    public static Scanner input = new Scanner(System.in);
    private final SearchStrategies searchStrategies = new SearchStrategies();

    public void searchMenu() {
        int option;
        do {
            printMenu();
            try {
                option = Integer.parseInt(input.nextLine());
                switch (option) {
                    case 1 -> chooseStrategy();
                    case 2 -> printListOfPeople(ObtainDataFromFile.searchValues);
                    case 0 -> terminateSearchMessage();
                }
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Incorrect option! Try again.");
            }
        }
        while (option != 0);
    }

    private void terminateSearchMessage() {
        System.out.println("Bye!");
        System.exit(0);
    }

    private void printMenu() {
        String[] options = {"=== Menu ===",
                "1. Find a person",
                "2. Print all people",
                "0. Exit",
        };
        Arrays.stream(options).forEach(System.out::println);
    }

    private void chooseStrategy() {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String strategyType = input.nextLine();
        try {
            switch (strategyType.toLowerCase()) {
                case "any" ->
                        printFinalResults(searchStrategies.findAny(ObtainDataFromFile.splitValues, defineSearchValue()));
                case "none" ->
                        printFinalResults(searchStrategies.findNone(ObtainDataFromFile.splitValues, defineSearchValue()));
                case "all" ->
                        printFinalResults(searchStrategies.findAll(ObtainDataFromFile.splitValues, defineSearchValue()));
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Incorrect option! Please, select one from the list above.");
        }
    }

    public <T> void printFinalResults(Collection<T> finalResult) {
        if (Objects.nonNull(finalResult) && finalResult.size() > 0) {
            System.out.println("Found people:");
            finalResult.forEach(System.out::println);
        } else {
            System.out.println("No matching people found");
        }
    }

    public void printListOfPeople(List<String> splitValues) {
        if (Objects.nonNull(splitValues) && splitValues.size() > 0) {
            System.out.println("=== List of people ===");
            splitValues.forEach(System.out::println);
        } else {
            System.out.println("Current list of people hasn't been initialized or contains 0 items");
        }
    }

    private String defineSearchValue() {
        System.out.println("Enter a name or email to search all suitable people.");
        return input.nextLine();
    }
}
