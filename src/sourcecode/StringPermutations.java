package sourcecode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class StringPermutations {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java StringPermutations <string>");
            return;
        }
        
        String input = args[0];
        List<String> permutations = generatePermutations(input, false);
        System.out.println("Permutations of " + input + ": " + permutations);
    }

    public static List<String> generatePermutations(String str, boolean excludeDuplicates) {
        if (excludeDuplicates) {
            return new ArrayList<>(new HashSet<>(generatePermutations("", str)));
        } else {
            return generatePermutations("", str);
        }
    }

    private static List<String> generatePermutations(String prefix, String str) {
        List<String> result = new ArrayList<>();
        int n = str.length();
        if (n == 0) {
            result.add(prefix);
        } else {
            HashSet<Character> usedChars = new HashSet<>();
            for (int i = 0; i < n; i++) {
                char currentChar = str.charAt(i);
                if (usedChars.add(currentChar)) { // Check for duplicates
                    result.addAll(generatePermutations(prefix + currentChar, str.substring(0, i) + str.substring(i + 1, n)));
                }
            }
        }
        return result;
    }
}
