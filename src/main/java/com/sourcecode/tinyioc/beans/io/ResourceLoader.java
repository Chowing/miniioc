package com.sourcecode.tinyioc.beans.io;

import java.net.URL;

public class ResourceLoader {
    public Resource getResource(String location){
        URL url = getClass().getClassLoader().getResource(location);
        return new UrlResource(url);
    }
}
