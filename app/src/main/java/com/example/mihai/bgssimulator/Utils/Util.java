package com.example.mihai.bgssimulator.Utils;

import java.util.Iterator;

/**
 * Created by mihai on 08.03.2017.
 */

public class Util {


    public static <T> T getLastElement(final Iterable<T> elements) {
        final Iterator<T> itr = elements.iterator();
        T lastElement = itr.next();

        while(itr.hasNext()) {
            lastElement=itr.next();
        }

        return lastElement;
    }
}
