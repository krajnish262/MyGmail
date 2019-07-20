package com.example.rajnish.mygmail;

import java.util.Comparator;

public class DateSorting implements Comparator<MyGmail> {
    @Override
    public int compare(MyGmail o1, MyGmail o2) {
        int res = o1.getDate().compareToIgnoreCase(o2.getDate());
        if (res != 0)
            return res;
        return o1.getDate().compareToIgnoreCase(o2.getDate());
    }
}

