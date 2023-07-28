package com.twodog.cache;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;

public class HutoolCacheUtil {
    public static void main(String[] args) {
        lfu();
    }

    public static void lfu() {
        Cache<String, String> lfuCache = CacheUtil.newLFUCache(3);
        //通过实例化对象创建
        //LFUCache<String, String> lfuCache = new LFUCache<String, String>(3);

        lfuCache.put("key1", "value1", DateUnit.SECOND.getMillis() * 3);
        lfuCache.get("key1");//使用次数+1
        lfuCache.put("key2", "value2", DateUnit.SECOND.getMillis() * 3);
        lfuCache.put("key3", "value3", DateUnit.SECOND.getMillis() * 3);
        lfuCache.put("key4", "value4", DateUnit.SECOND.getMillis() * 3);

        //由于缓存容量只有3，当加入第四个元素的时候，根据LRU规则，最少使用的将被移除（2,3被移除）

        executePrint(lfuCache);
    }

    public static void lru() {
        Cache<String, String> lruCache = CacheUtil.newLRUCache(3);
        //通过实例化对象创建
        //LRUCache<String, String> lruCache = new LRUCache<String, String>(3);
        lruCache.put("key1", "value1", DateUnit.SECOND.getMillis() * 3);
        lruCache.put("key2", "value2", DateUnit.SECOND.getMillis() * 3);
        lruCache.put("key3", "value3", DateUnit.SECOND.getMillis() * 3);
        lruCache.get("key1");//使用时间推近
        lruCache.put("key4", "value4", DateUnit.SECOND.getMillis() * 3);

        //由于缓存容量只有3，当加入第四个元素的时候，根据LRU规则，最少使用的将被移除（2被移除）

        executePrint(lruCache);
    }

    public static void fifo() {
        Cache<String, String> fifoCache = CacheUtil.newFIFOCache(3);

        //加入元素，每个元素可以设置其过期时长，DateUnit.SECOND.getMillis()代表每秒对应的毫秒数，在此为3秒
        fifoCache.put("key1", "value1", DateUnit.SECOND.getMillis() * 3);
        fifoCache.put("key2", "value2", DateUnit.SECOND.getMillis() * 3);
        fifoCache.put("key3", "value3", DateUnit.SECOND.getMillis() * 3);

        //由于缓存容量只有3，当加入第四个元素的时候，根据FIFO规则，最先放入的对象将被移除
        fifoCache.put("key4", "value4", DateUnit.SECOND.getMillis() * 3);

        //value1为null

        executePrint(fifoCache);
    }

    private static void executePrint(Cache<String, String> cacheMap) {
        System.out.println(cacheMap.get("key1"));
        System.out.println(cacheMap.get("key2"));
        System.out.println(cacheMap.get("key3"));
        System.out.println(cacheMap.get("key4"));
        ThreadUtil.sleep(3000);
        System.out.print(StrUtil.CRLF + StrUtil.CRLF + StrUtil.CRLF);
        System.out.println(cacheMap.get("key1"));
        System.out.println(cacheMap.get("key2"));
        System.out.println(cacheMap.get("key3"));
        System.out.println(cacheMap.get("key4"));
    }
}
