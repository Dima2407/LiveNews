package com.dimazatolokin.livenews.util;

import android.text.TextUtils;

/**
 * Created by dimazatolokin on 10.05.18.
 */

public class Utils {

    public static String getPrettyTime(String badTime) {
        if (TextUtils.isEmpty(badTime)) {
            return "";
        }
        String[] strings = badTime.split("T");
        String[] dates = strings[0].split("-");
        int indexTime = strings[1].lastIndexOf(":");
        String time = strings[1].substring(0, indexTime);
        String date = dates[2].concat(".").concat(dates[1]).concat(".").concat(dates[0]);
        return time.concat("  ").concat(date);
    }
}
