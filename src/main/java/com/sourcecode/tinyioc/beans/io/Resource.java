package com.sourcecode.tinyioc.beans.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 定位资源的接口
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
