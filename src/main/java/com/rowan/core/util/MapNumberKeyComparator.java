package com.rowan.core.util;

import java.util.Comparator;

/**
 * @author zhanghao
 * @date 2019/9/26 18:05
 **/
public class MapNumberKeyComparator<T> implements Comparator<T> {

    private Order order;

    public MapNumberKeyComparator(Order order) {
        this.order = order;
    }

    @Override
    public int compare(T a, T b) {
        Long aa = null;
        Long bb = null;
        if (a instanceof String && b instanceof String) {
            String aStr = (String) a;
            String bStr = (String) b;
            return aStr.compareToIgnoreCase(bStr) * order.sign;
        } else if (a instanceof Number && b instanceof Number) {
            aa = (Long) a;
            bb = (Long) b;
            return aa >= bb ? 1 * order.sign : -1 * order.sign;
        } else {
            throw new RuntimeException("指定按key排序时，key暂时只支持意义上的数字类型");
        }
    }

    public enum Order {
        //倒序排列
        desc("倒序", -1),
        asc("正序", 1);

        private String name;
        private int sign;

        Order(String name, int sign) {
            this.name = name;
            this.sign = sign;
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
