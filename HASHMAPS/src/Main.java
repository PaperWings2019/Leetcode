import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        hashmaps obj = new hashmaps();
        /*
            String s = "anagram";
            String t = "nagaram";
            System.out.println(obj.isAnagram(s, t));
        */
        int[] arr1 = {1, 2, 3, 2, 1};
        int[] arr2 = {2, 1, 1};
        System.out.println(Arrays.toString(obj.intersection(arr1, arr2)));
        Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
    }
}