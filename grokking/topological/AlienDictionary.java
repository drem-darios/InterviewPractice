package grokking.topological;

import java.util.*;

// Topological Sort
class AlienDictionary {
    public static String findOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        Map<Character, List<Character>> adjacencyList = new HashMap<>();
        Map<Character, Integer> characterCount = new HashMap<>();
        StringBuilder result = new StringBuilder();

        // Initialize & build
        for (int i = 0; i < words.length - 1; i++) {
            String firstWord = words[i];
            String secondWord = words[i+1];

            for (int j = 0; j < Math.min(firstWord.length(), secondWord.length()); j++) {
                char firstWordChar = firstWord.charAt(j);
                char secondWordChar = secondWord.charAt(j);

                if (firstWordChar != secondWordChar) { // Not the same letter
                    if (adjacencyList.get(firstWordChar) == null) {
                        adjacencyList.put(firstWordChar, new ArrayList<>());
                    }
                    adjacencyList.get(firstWordChar).add(secondWordChar);
                    if (characterCount.get(firstWordChar) == null) {
                        characterCount.put(firstWordChar, 0);
                    }

                    if (characterCount.get(secondWordChar) == null) {
                        characterCount.put(secondWordChar, 0);
                    }

                    characterCount.put(secondWordChar, characterCount.get(secondWordChar) + 1);
                    break; // Only care about the first characters that do no match
                }
            }
        }

        // Find sources
        Queue<Character> sources = new LinkedList<>();
        for (Character character: adjacencyList.keySet()) {
            if (characterCount.get(character) == 0) {
                sources.add(character);
            }
        }

        // Sort
        while(!sources.isEmpty()) {
            Character character = sources.poll();
            result.append(character);
            if (adjacencyList.get(character) != null) {
                for (Character adjacentCharacter: adjacencyList.get(character)) {
                    characterCount.put(adjacentCharacter, characterCount.get(adjacentCharacter) - 1);
                    if (characterCount.get(adjacentCharacter) == 0) {
                        sources.add(adjacentCharacter);
                    }
                }
            }

        }

        if (result.length() != characterCount.size()) {
            return "";
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String result = AlienDictionary.findOrder(new String[] { "ba", "bc", "ac", "cab" });
        System.out.println("Character order: " + result); // bac

        result = AlienDictionary.findOrder(new String[] { "cab", "aaa", "aab" });
        System.out.println("Character order: " + result); // cab

        result = AlienDictionary.findOrder(new String[] { "ywx", "wz", "xww", "xz", "zyy", "zwz" });
        System.out.println("Character order: " + result); // ywxz
    }
}
