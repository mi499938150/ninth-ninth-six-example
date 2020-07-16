package com.mi.service;

import java.io.File;
import java.io.InputStream;

/**
 * @author : Rong
 * @date : 2020/7/15
 * @Desc:
 */
public interface FileService {

    /**
     * 文件上传
     * @param inputStream
     * @param filename
     */
    void upload(InputStream inputStream, String filename);

    /**
     * 文件上传
     * @param file
     */
    void upload(File file);
}
