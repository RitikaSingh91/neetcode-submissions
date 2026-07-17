public class Solution {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {

        StringBuilder sb = new StringBuilder();

        for (String str : strs) {

            sb.append(str.length()); // length of string

            sb.append('#');          // delimiter

            sb.append(str);          // actual string
        }

        return sb.toString();
    }

    // Decodes a single string back to list of strings.
    public List<String> decode(String s) {

        List<String> result = new ArrayList<>();

        int i = 0;

        while (i < s.length()) {

            int j = i;

            // Find '#'
            while (s.charAt(j) != '#') {
                j++;
            }

            // Length of string
            int length = Integer.parseInt(s.substring(i, j));

            // Actual string starts after '#'
            String word = s.substring(j + 1, j + 1 + length);

            result.add(word);

            // Move pointer
            i = j + 1 + length;
        }

        return result;
    }
}