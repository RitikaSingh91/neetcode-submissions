class Solution {
    public boolean checkInclusion(String s1, String s2) {

        if (s1.length() > s2.length()) {
            return false;
        }

        int[] count1 = new int[26];
        int[] count2 = new int[26];

        // Frequency of characters in s1
        for (char c : s1.toCharArray()) {
            count1[c - 'a']++;
        }

        int windowSize = s1.length();

        // First window
        for (int i = 0; i < windowSize; i++) {
            count2[s2.charAt(i) - 'a']++;
        }

        // Check first window
        if (matches(count1, count2)) {
            return true;
        }

        // Sliding window
        for (int right = windowSize; right < s2.length(); right++) {

            // Add new character
            count2[s2.charAt(right) - 'a']++;

            // Remove leftmost character
            int left = right - windowSize;
            count2[s2.charAt(left) - 'a']--;

            // Check frequency
            if (matches(count1, count2)) {
                return true;
            }
        }

        return false;
    }

    private boolean matches(int[] count1, int[] count2) {
        for (int i = 0; i < 26; i++) {
            if (count1[i] != count2[i]) {
                return false;
            }
        }
        return true;
    }
}