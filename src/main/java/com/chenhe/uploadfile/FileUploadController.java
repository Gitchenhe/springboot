package com.chenhe.uploadfile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

/**
 * Created by chenhe on 2018/1/3.
 */
@Controller
public class FileUploadController {

    private final StorageService storageService;

    private Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    public FileUploadController(StorageService storageService){
        this.storageService=storageService;
    }

    @GetMapping(path = "filelist")
    public String listUploadedFiles(HttpServletRequest request){
        request.setAttribute("file",storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile",path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename){
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=\""+file.getFilename()+"\"").body(file);
    }

    @PostMapping("/fileupload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
        logger.info("文件上传请求.");
        storageService.store(file);

        redirectAttributes.addFlashAttribute("message","成功上传文件 "+file.getOriginalFilename() +"!");

        return "redirect:/filelist";
    }
}
