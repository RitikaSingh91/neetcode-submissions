class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        Integer[] cars = new Integer[n];

        for (int i = 0; i < n; i++) {
            cars[i] = i;
        }

        Arrays.sort(cars, (a, b) -> Integer.compare(position[b], position[a]));

        double lastTime = 0;
        int fleets = 0;

        for (int i : cars) {
            double time = (double) (target - position[i]) / speed[i];

            if (time > lastTime) {
                fleets++;
                lastTime = time;
            }
        }

        return fleets;
    }
}