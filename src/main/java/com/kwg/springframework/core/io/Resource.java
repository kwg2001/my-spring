package com.kwg.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: kwg2001
 * @Date: 2022/5/3 19:45
 * @Description: 资源加载接口
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

}
