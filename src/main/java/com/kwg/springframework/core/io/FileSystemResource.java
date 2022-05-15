package com.kwg.springframework.core.io;/**
 * @Auther: kwg2001
 * @Date: 2022/5/3 20:18
 * @Description:
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: my-spring
 *
 * @description: 读取 指定的 文件信息
 *
 * @author: Kwg
 *
 * @create: 2022-05-03 20:18
 **/
public class FileSystemResource implements Resource{

    private  final File file;
    private final String path;

    public FileSystemResource(String path) {
        this.path = path;
        this.file=new File(path);
    }

    public FileSystemResource(File file) {
        this.file = file;
        this.path=file.getPath();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(file);
    }
}
