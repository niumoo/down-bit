package com.wdbyte.downbit.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author niujinpeng
 * @date 2020/09/24
 */
public class LogUtils {

    public static boolean DEBUG = false;

    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    public static void info(String msg, Object... arg) {
        print(msg, " -INFO- ", arg);
    }

    public static void error(String msg, Object... arg) {
        print(msg, " -ERROR-", arg);
    }

    public static void debug(String msg, Object... arg) {
        if (DEBUG) { print(msg, " -DEBUG-", arg); }
    }

    private static void print(String msg, String level, Object... arg) {
        if (arg != null && arg.length > 0) {
            msg = String.format(msg.replace("{}", "%s"), arg);
        }
        String thread = Thread.currentThread().getName();
        System.out.println(LocalDateTime.now().format(dateTimeFormatter) + " " + thread + level + msg);
    }
}
