package com.shanlan.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


public class FileUploadUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);


    /**
     * 获取以年、月作为路径名的存储路径，比如:2014/10/
     *
     * @return
     */
    public static String getYearMonthPath() {
        return DateUtil.getYear() +"/"+ DateUtil.getMonthInt() + "/";
    }

    /**
     * 获取以年、月作为路径名的存储路径，比如:2014/10/25
     *
     * @return
     */
    public static String getYearMonthDayPath() {
        return getYearMonthPath() + DateUtil.getDayInt() + "/";
    }





    /**
     * 创建目录
     *
     * @param filePath
     */
    public static void createDir(String filePath) {
        File myFile = new File(filePath);
        if (!myFile.exists()) {
            if (!myFile.mkdirs())
                System.out.println("创建目录 fail");
            else
                System.out.println("创建目录 success");
        }
        myFile = null;
    }



}