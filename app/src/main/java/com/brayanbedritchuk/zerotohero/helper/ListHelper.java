package com.brayanbedritchuk.zerotohero.helper;

import java.util.List;

public class ListHelper {

    public static void clearAndAdd(List from, List to) {
        to.clear();
        to.addAll(from);
    }

}
