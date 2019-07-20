package com.example.rajnish.mygmail;

import java.util.Comparator;

public class NameSorting implements Comparator<MyGmail> {
    @Override
    public int compare(MyGmail myGmail1, MyGmail myGmail2) {
        int res = myGmail1.getFrom().compareToIgnoreCase(myGmail2.getFrom());
        if (res != 0)
            return res;
        return myGmail1.getFrom().compareToIgnoreCase(myGmail2.getFrom());
    }
}

