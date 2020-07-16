package com.mi.controller;

import com.mi.common.ResponseResult;
import com.mi.exception.BusinessException;
import com.mi.exception.ErrorCodeEnum;
import com.mi.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * @author : Rong
 * @date : 2020/7/15
 * @Desc: 文件服务
 */
@RestController
@RequestMapping("/api/files")
@Slf4j
public class FileController {

    @Resource(name = "localFileServiceImpl")
    private FileService fileService;

    /**
     * 文件上传
     * @param multipartFile
     * @return
     */
    @PostMapping("/upload")
    public ResponseResult<String> upload(@NotNull  MultipartFile file){

        //文件上传...
        try {
            fileService.upload(file.getInputStream(),file.getOriginalFilename());
        }catch (Exception e){
            log.error("文件上传失败!",e);
            throw new BusinessException(
                    ErrorCodeEnum.FILE_UPLOAD_FAILURE,e
            );

        }
        return ResponseResult.success(file.getOriginalFilename());
    }
}