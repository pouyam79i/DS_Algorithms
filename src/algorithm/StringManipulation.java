package algorithm;

import java.util.*;

public class StringManipulation {

    public static int findNumberOfVowels(String sentence){
        int count = 0;

        for (char ch : sentence.toCharArray())
            // more on this: you can use hash set and set.contains() to make it instant in larger scenarios
            if (isVowel(ch))
                count++;

        return count;
    }

    public static String reverseString(String sentence){
        if (sentence == null)
            return "";

        StringBuilder reversed = new StringBuilder();
        for (int i = sentence.length()-1; i >= 0; i--)
            reversed.append(sentence.charAt(i));

        return reversed.toString();
    }

    public static String reverseWords(String sentence){
        String[] words = sentence.trim().split(" ");

        // you can also use stack - but this way is much simpler
//        StringBuilder reversed = new StringBuilder();
//        for (int i = words.length-1; i >= 0; i--){
//            reversed.append(words[i]);
//            if (i != 0)
//                reversed.append(" ");
//        }
//        return reversed.toString();

        // another way:
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    public static boolean areRotations(String str1, String str2){
        if (str1 == null || str2 == null)
            return false;

        return ((str1.length() == str2.length()) && (
                (str1 + str1).contains(str2)
                ));
    }

    public static String removeDuplicate(String str){
        if (str == null)
            return "";

        Set<Character> set = new HashSet<>();
        StringBuilder duplicateRemoved = new StringBuilder();
        for(var ch : str.toCharArray()){
            if (set.contains(ch))
                continue;
            duplicateRemoved.append(ch);
            set.add(ch);
        }
        return duplicateRemoved.toString();
    }

    public static char getMaxRepeatedChar(String str){
        if(str == null || str.isEmpty())
            throw new IllegalArgumentException();

        final int ASCII_SIZE = 256;
        int[] table = new int[ASCII_SIZE];
        for(var ch : str.toCharArray())
            table[ch]++;

        int max=0;
        char result = ' ';
        for(char i = 0; i < ASCII_SIZE; i++){
            if(max < table[i]){
                max = table[i];
                result = i;
            }
        }

        return result;
    }

    public static String capitalize(String sentence){
        if (sentence == null)
            return "";
        if (sentence.trim().isEmpty())
            return "";

        String[] words = sentence.trim().replaceAll(" +", " ").split(" ");

        for (int i = 0; i < words.length; i++)
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();

        return String.join(" ", words);
    }

    public static boolean areAnagrams(String str1, String str2){
        if (str1 == null || str2 == null
                || str1.length() != str2.length())
            return false;
        if (str1.isEmpty())
            return true;

// use sorting + case-insensitive:
//        var arr1 = str1.toLowerCase().toCharArray();
//        Arrays.sort(arr1);
//        var arr2 = str2.toLowerCase().toCharArray();
//        Arrays.sort(arr2);
//        return Arrays.equals(arr1, arr2);

// use maps:
//        Map<Character, Integer> charsStr1 = new HashMap<>();
//        Map<Character, Integer> charsStr2 = new HashMap<>();
//        for (int i = 0; i < str1.length(); i++){
//            charsStr1.put(str1.charAt(i), charsStr1.getOrDefault(str1.charAt(i), 0));
//            charsStr2.put(str2.charAt(i), charsStr2.getOrDefault(str2.charAt(i), 0));
//        }
//        return charsStr1.equals(charsStr2);

// using arrays + case-insensitive: much simpler
        final int ALPHABET_SIZE = 26;
        int[] histogram = new int[ALPHABET_SIZE];

        for (int i = 0; i < str1.length(); i++)
            histogram[str1.charAt(i) - 'a']++;

        for (int i = 0; i < str2.length(); i++){
            int index = str2.charAt(i) - 'a';
            if (histogram[index] == 0)
                return false;
            histogram[index]--;
        }

        return true;
    }

    public static boolean isPalindrome(String str){
        if (str == null)
            return false;

        int left = 0;
        int right = str.length()-1;

        while (left < right)
            if (str.charAt(left++) != str.charAt(right--))
                return false;

        return true;
    }

    private static boolean isVowel(char ch){
        if (ch < 97)
            ch += 32;
        return (
                ch == 'a' || ch == 'e' || ch == 'u' || ch == 'o' || ch == 'i'
                );
    }

}
