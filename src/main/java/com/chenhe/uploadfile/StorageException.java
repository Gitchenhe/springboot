package com.chenhe.uploadfile;

/**
 * Created by chenhe on 2018/1/3.
 */
public class StorageException extends RuntimeException{
    public StorageException(String messge){
        super(messge);
    }

    public StorageException(String message,Throwable cause){
        super(message,cause);
    }
}
