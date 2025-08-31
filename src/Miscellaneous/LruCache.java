package Miscellaneous;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache {
    private LinkedHashMap<Integer, Integer> cacheMap = null;
    private int capacity = 0;

    public LruCache(int capactiy) {
        this.capacity = capactiy;
        cacheMap = new LinkedHashMap<>(capactiy);
    }

    public int get(int key) {
        if (cacheMap.containsKey(key)) {
            int value = cacheMap.get(key);
            cacheMap.remove(key);
            cacheMap.put(key, value);
        }
        return -1;
    }

    public void put(int key, int value) {
        System.out.println("putting entry " + key + " value " + value);
        if (cacheMap.size() >= capacity) {
            System.out.println("going for eviction.. " + cacheMap.size() + " capacity " + capacity);
            this.printAll();
            evictLru();
        }
        cacheMap.put(key, value);
    }

    public void evictLru() {
        int key = this.cacheMap.firstEntry().getKey();
        this.cacheMap.remove(key);
    }

    public static void main(String[] args) {
        LruCache lruCache = new LruCache(3);
        lruCache.put(1, 1); // cache is {1=1}
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        lruCache.get(2);    // return 1
        lruCache.get(4);    // return 1
        lruCache.printAll();
        lruCache.put(4, 4);
        lruCache.put(3, 33);// LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lruCache.get(4);    // return 4
        lruCache.put(1, 11);// LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lruCache.printAll();
    }

    private void printAll() {
        System.out.println("cache :"+ this.cacheMap);
//        System.out.println("key stats :"+ this.keyStats);
    }


}
