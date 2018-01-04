package com.chenhe.uploadfile;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Created by chenhe on 2018/1/3.
 */
public interface StorageService {
    void init();

    void store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String fileName);

    Resource loadAsResource(String fileName);

    void deleteAll();
}
