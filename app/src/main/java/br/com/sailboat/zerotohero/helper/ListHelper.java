package br.com.sailboat.zerotohero.helper;

import java.util.List;

public class ListHelper {

    public static void clearAndAdd(List fromList, List toList) {
        toList.clear();
        toList.addAll(fromList);
    }

}
