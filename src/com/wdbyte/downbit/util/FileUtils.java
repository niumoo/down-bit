package com.wdbyte.downbit.util;

import java.io.File;

/**
 * <p>
 * 文件操作工具类
 *
 * @author niujinpeng
 * @Date 2020/7/19 21:24
 */
public class FileUtils {

    /**
     * 获取文件内容长度
     *
     * @param name
     * @return
     */
    public static long getFileContentLength(String name) {
        File file = new File(name);
        return file.exists() && file.isFile() ? file.length() : 0;
    }

}
