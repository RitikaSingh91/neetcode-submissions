class LFUCache {
    private int capacity;
    private int minFreq;

    private Map<Integer, Integer> values;
    private Map<Integer, Integer> freq;
    private Map<Integer, LinkedHashSet<Integer>> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;

        values = new HashMap<>();
        freq = new HashMap<>();
        freqMap = new HashMap<>();
    }

    public int get(int key) {
        if (!values.containsKey(key)) {
            return -1;
        }

        updateFreq(key);
        return values.get(key);
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        if (values.containsKey(key)) {
            values.put(key, value);
            updateFreq(key);
            return;
        }

        if (values.size() == capacity) {
            LinkedHashSet<Integer> keys = freqMap.get(minFreq);
            int lruKey = keys.iterator().next();

            keys.remove(lruKey);
            values.remove(lruKey);
            freq.remove(lruKey);

            if (keys.isEmpty()) {
                freqMap.remove(minFreq);
            }
        }

        values.put(key, value);
        freq.put(key, 1);
        freqMap.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);

        minFreq = 1;
    }

    private void updateFreq(int key) {
        int oldFreq = freq.get(key);

        LinkedHashSet<Integer> oldKeys = freqMap.get(oldFreq);
        oldKeys.remove(key);

        if (oldKeys.isEmpty()) {
            freqMap.remove(oldFreq);

            if (minFreq == oldFreq) {
                minFreq++;
            }
        }

        int newFreq = oldFreq + 1;
        freq.put(key, newFreq);

        freqMap.computeIfAbsent(newFreq, k -> new LinkedHashSet<>()).add(key);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */