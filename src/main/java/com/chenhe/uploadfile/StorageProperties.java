package com.chenhe.uploadfile;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by chenhe on 2018/1/3.
 */
@ConfigurationProperties("storage")
public class StorageProperties {
    private String location = "upload-dir";

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location=location;
    }
}
