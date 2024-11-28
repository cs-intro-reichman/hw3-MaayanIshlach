import java.util.ArrayList;
import java.util.Random;

/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	private static int count_char(String s, char c) {
        int count = 0;
        for (char ch : s.toCharArray()) {
            if (ch == c) {
                count++;
            }
        }
        return count;
    }

	public static boolean isAnagram(String str1, String str2) {
		str1 = preProcess(str1);
        str2 = preProcess(str2);

        if (str1.length() != str2.length()) {
            return false;
        }

   
        for (char c : str1.toCharArray()) {
            int count1 = count_char(str1, c);
            int count2 = count_char(str2, c);

            if (count1 != count2) {
                return false; 
            }
        }

        return true;
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		String pre_str=str;
		pre_str=str.toLowerCase().replaceAll("[^a-z]", "");
		return pre_str;
	
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		str = preProcess(str);
        ArrayList<Character> chars = new ArrayList<>();
        for (char c : str.toCharArray()) {
            chars.add(c);
        }

        StringBuilder anagram = new StringBuilder();
        Random random = new Random();

        while (!chars.isEmpty()) {
            int rnd = random.nextInt(chars.size());
            anagram.append(chars.remove(rnd));
        }
        return anagram.toString();
    }
	
}
