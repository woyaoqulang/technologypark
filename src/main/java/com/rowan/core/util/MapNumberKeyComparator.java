package com.rowan.core.util;

import java.util.Comparator;

/**
 * @author zhanghao
 * @date 2019/9/26 18:05
 **/
public class MapNumberKeyComparator<T> implements Comparator<T> {

    protected MapNumberKeyComparator.Order order;

    public MapNumberKeyComparator() {
        this.order = MapNumberKeyComparator.Order.asc;
    }

    public MapNumberKeyComparator(MapNumberKeyComparator.Order order) {
        this.order = MapNumberKeyComparator.Order.asc;
        this.order = order;
    }

    @Override
    public int compare(T a, T b) {
        Long aa = null;
        Long bb = null;
        if (a instanceof String && b instanceof String) {
            String aStr = (String) a;
            String bStr = (String) b;
            return aStr.compareToIgnoreCase(bStr) * this.order.sign;
        } else if (a instanceof Number && b instanceof Number) {
            aa = (Long) a;
            bb = (Long) b;
            return aa >= bb ? 1 * this.order.sign : -1 * this.order.sign;
        } else {
            throw new RuntimeException("指定按key排序时，key暂时只支持意义上的数字类型");
        }
    }

    public enum Order {
        desc("倒序", -1),
        asc("正序", 1);

        protected String name;
        protected int sign;

        Order(String name, int sign) {
            this.name = name;
            this.sign = sign;
        }

        public static String getName(int sign) {
            MapNumberKeyComparator.Order[] var1 = values();
            int var2 = var1.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                MapNumberKeyComparator.Order c = var1[var3];
                if (c.getSign() == sign) {
                    return c.name;
                }
            }

            return null;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSign() {
            return this.sign;
        }

        public void setSign(int sign) {
            this.sign = sign;
        }
    }
}
