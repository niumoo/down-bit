package com.wdbyte.downbit.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

import com.wdbyte.downbit.DownloadMain;

/**
 * <p>
 * 多线程下载日志记录
 *
 * @author niujinpeng
 * @Date 2020/7/23 23:05
 */
public class LogThread implements Callable<Boolean> {

    public static AtomicLong DOWNLOAD_SIZE = new AtomicLong();
    public static AtomicLong DOWNLOAD_FINISH = new AtomicLong();
    private long httpFileContentLength;

    public LogThread(long httpFileContentLength) {
        this.httpFileContentLength = httpFileContentLength;
    }

    @Override
    public Boolean call() throws Exception {
        double size = 0;
        int logLength = 0;
        while (DOWNLOAD_FINISH.get() != DownloadMain.DOWNLOAD_THREAD_NUM) {
            double downloadSize = DOWNLOAD_SIZE.get();
            // 速度 = 大小/ 时间
            Double speed = ((downloadSize - size) / 1024d) / 1d;
            size = downloadSize;
            double surplusTime = (httpFileContentLength - downloadSize) / 1024d / speed;
            Double fileSize = downloadSize / 1024d / 1024d;
            String speedLog = "> 已经下载大小 " + String.format("%.2f", fileSize) + "mb,当前下载速度:" + speed.intValue() + "kb/s"
                + ",估计剩余时间:" + String.format("%.1f", surplusTime) + "s";
            for (int i = 0; i < logLength; i++) {
                System.out.print("\b");
            }
            logLength = speedLog.length();
            System.out.print(speedLog);
            Thread.sleep(1000);
        }
        System.out.println();
        return true;
    }

}
