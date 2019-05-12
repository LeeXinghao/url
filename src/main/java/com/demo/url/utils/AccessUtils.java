package com.demo.url.utils;

import java.util.HashMap;

/**
 * 访问计数
 * 访问计数的数据会在每次重启后清除。如果需要访问数据永久有效，可以将访问信息数据入库。
 *
 * <p>
 * Created by li.hao on 2019/5/12.
 */
public class AccessUtils {

    public static final class MutableInteger {
        private int val;

        public MutableInteger(int val) {
            this.val = val;
        }

        public int get() {
            return this.val;
        }

        public void set(int val) {
            this.val = val;
        }
    }

    public static final HashMap<String, MutableInteger> counter = new HashMap<String, MutableInteger>();

    public static void accessCount(String key) {
        if (counter.containsKey(key)) {
            MutableInteger oldValue = counter.get(key);
            oldValue.set(oldValue.get() + 1);
        } else {
            counter.put(key, new MutableInteger(1));
        }
    }
}
