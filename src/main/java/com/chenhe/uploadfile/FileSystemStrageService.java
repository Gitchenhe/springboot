package com.chenhe.uploadfile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

/**
 * Created by chenhe on 2018/1/3.
 */
@Service
public class FileSystemStrageService implements StorageService {

    private Logger logger = LoggerFactory.getLogger(FileSystemStrageService.class);

    private Path rootLocation = null;

    @Autowired
    public FileSystemStrageService(StorageProperties storageProperties){
        this.rootLocation = Paths.get(storageProperties.getLocation());

    }

    @Override
    public void init() {
        logger.info("初始化文件目录");
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            logger.error("文件目录初始化失败",e);
        }
    }

    @Override
    public void store(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        logger.info("文件存储.{}",fileName);
        try{
            if (file.isEmpty()){
                throw new StorageException("不能存储空文件,"+fileName);
            }
            if (fileName.contains("..")){
                throw new StorageException("文件路径错误"+fileName);
            }

            Files.copy(file.getInputStream(),this.rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            logger.error("存储文件失败{}",fileName,e);
            throw new StorageException("存储文件失败"+fileName,e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        logger.info("加载所有文件.");
        try{
            return Files.walk(this.rootLocation,1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        }catch (IOException e) {
            logger.error("加载文件列表失败",e);
            throw new StorageException("加载文件列表失败",e);
        }
    }

    @Override
    public Path load(String fileName) {
        logger.info("文件系统初始化...");
        return rootLocation.resolve(fileName);
    }

    @Override
    public Resource loadAsResource(String fileName) {
        logger.info("加载单个文件.");
        try{
            Path file = load(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()){
                return resource;
            }else{
                logger.warn("加载文件,文件不存在,{}",fileName);
                throw new StorageException("文件不存在");
            }
        }catch (MalformedURLException e){
            logger.error("文件读取失败",e);
            throw new StorageException("文件读取失败",e);
        }
    }

    @Override
    public void deleteAll() {
        logger.info("删除所有文件....");
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}
