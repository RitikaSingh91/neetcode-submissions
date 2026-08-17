class Solution {
    public int maxArea(int[] heights) {
        int left = 0, right = heights.length - 1;
        int max = 0;

        while (left < right) {
            int area = Math.min(heights[left], heights[right]) * (right - left);
            max = Math.max(max, area);

            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }
}