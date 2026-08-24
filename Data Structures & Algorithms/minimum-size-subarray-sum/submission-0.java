class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int minLen = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            long required = prefix[i] + target;
            int j = lowerBound(prefix, required);

            if (j <= n) {
                minLen = Math.min(minLen, j - i);
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    private int lowerBound(long[] arr, long target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}