import java.util.*;

public class DictionaryManager {
    private Map<String, String> synonyms;
    private Map<String, String> antonyms;

    public DictionaryManager() {
        synonyms = new HashMap<>();
        antonyms = new HashMap<>();
    }
    public void addSynonym(String word, String synonym) {
        synonyms.put(word, synonym);
    }
    public void addAntonym(String word, String antonym) {
        antonyms.put(word, antonym);
    }
    public void deleteSynonym(String word) {
        synonyms.remove(word);
    }
    public void deleteAntonym(String word) {
        antonyms.remove(word);
    }
    public Map<String, String> mergeDictionaries() {
        HashMap<String, String> merged = new HashMap<>();

        for (String word : synonyms.keySet()) {
            String synonym = synonyms.get(word);
            String antonym = antonyms.get(word);
            if (antonym != null) {
                merged.put(word, "Synonym: " + synonym + ", Antonym: " + antonym);
            } else {
                merged.put(word, "Synonym: " + synonym);
            }
        }

        for (String word : antonyms.keySet()) {
            if (!merged.containsKey(word)) {
                String antonym = antonyms.get(word);
                merged.put(word, "Antonym: " + antonym);
            }
        }

        System.out.println("Merged Dictionary:");
        for (String key : merged.keySet()) {
            System.out.println(key + " - " + merged.get(key));
        }
        System.out.println();
        return merged;
    }
    public void displaySynonymAndAntonym(String word) {
        String synonym = synonyms.get(word);
        String antonym = antonyms.get(word);

        System.out.println("Word: " + word);
        if (synonym != null) {
            System.out.println("Synonym: " + synonym);
        } else {
            System.out.println("No synonym found.");
        }
        if (antonym != null) {
            System.out.println("Antonym: " + antonym);
        } else {
            System.out.println("No antonym found.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DictionaryManager dictionaryManager = new DictionaryManager();

        while (true) {
            System.out.println("\nDictionary Manager");
            System.out.println("1. Add Synonym");
            System.out.println("2. Add Antonym");
            System.out.println("3. Delete Synonym");
            System.out.println("4. Delete Antonym");
            System.out.println("5. Merge Dictionaries");
            System.out.println("6. Display Synonym and Antonym");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter word: ");
                    String wordForSynonym = scanner.nextLine();
                    System.out.print("Enter synonym: ");
                    String synonym = scanner.nextLine();
                    dictionaryManager.addSynonym(wordForSynonym, synonym);
                    break;
                case 2:
                    System.out.print("Enter word: ");
                    String wordForAntonym = scanner.nextLine();
                    System.out.print("Enter antonym: ");
                    String antonym = scanner.nextLine();
                    dictionaryManager.addAntonym(wordForAntonym, antonym);
                    break;
                case 3:
                    System.out.print("Enter word to delete synonym: ");
                    String wordToDeleteSynonym = scanner.nextLine();
                    dictionaryManager.deleteSynonym(wordToDeleteSynonym);
                    break;
                case 4:
                    System.out.print("Enter word to delete antonym: ");
                    String wordToDeleteAntonym = scanner.nextLine();
                    dictionaryManager.deleteAntonym(wordToDeleteAntonym);
                    break;
                case 5:
                    Map<String, String> mergedDict = dictionaryManager.mergeDictionaries();
                    System.out.println("Merged Dictionary: " + mergedDict);
                    break;
                case 6:
                    System.out.print("Enter word to display: ");
                    String wordToDisplay = scanner.nextLine();
                    dictionaryManager.displaySynonymAndAntonym(wordToDisplay);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}