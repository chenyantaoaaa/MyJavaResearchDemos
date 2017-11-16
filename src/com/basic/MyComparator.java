package com.basic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author chenyantao
 * @date 2017/11/16.
 */
public class MyComparator implements Comparator<people> {
    @Override
    public int compare(people o1, people o2) {
        return o1.getAge()-o2.getAge();
    }

    @Test
    public void t() {
        people p1= new people(1);
        people p2= new people(8);
        people p3= new people(1111);
        people p4= new people(2);
        List<people> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        Collections.sort(list,this);
        for (people people : list) {
            System.out.println(people.getAge());
        }
    }
}
