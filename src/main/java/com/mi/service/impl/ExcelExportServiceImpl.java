package com.mi.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.mi.common.PageQuery;
import com.mi.common.PageResult;
import com.mi.domain.dto.UserDTO;
import com.mi.domain.dto.UserExportDTO;
import com.mi.domain.dto.UserQueryDTO;
import com.mi.service.ExcelExportService;
import com.mi.service.FileService;
import com.mi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : Rong
 * @date : 2020/7/16
 * @Desc: Excel 导出服务实现类
 */
@Service
@Slf4j
public class ExcelExportServiceImpl implements ExcelExportService {

    @Resource(name = "localFileServiceImpl")
    private FileService fileService;

    @Autowired
    private UserService userService;

    /**
     * 执行数据库查询和Excel导出，将数据写入到outputStream中
     * @param outputStream
     * @param queryDTO
     */
    private void export(OutputStream outputStream,UserQueryDTO queryDTO){

        // 1. 需要创建一个EasyExcel导出对象
        ExcelWriter excelWriter = EasyExcelFactory.write(
                outputStream, UserExportDTO.class)
                .build();
        // 2. 分批加载数据
        PageQuery<UserQueryDTO> pageQuery =
                new PageQuery<>();
        pageQuery.setQuery(queryDTO);
        pageQuery.setPageSize(2);
        int pageNo = 0;
        PageResult<List<UserDTO>> pageResult;
        do {
            // 先累加 再赋值， 要跟pagno++区分
            pageQuery.setPageNo(++pageNo);
            log.info("开始导出第 [ {} ]页数据",pageNo);
            pageResult = userService.query(pageQuery);
            // 数据转换：UserDTO 转换成 UserExportDTO
            List<UserExportDTO> exportDTOList = Optional.ofNullable(pageResult.getData())
                    .map(List::stream)
                    .orElseGet(Stream::empty)
                    .map(userDTO -> {
                        UserExportDTO userExportDTO = new UserExportDTO();
                        BeanUtils.copyProperties(userDTO, userExportDTO);
                        return userExportDTO;
                    }).collect(Collectors.toList());

            // 将数据写入到不通sheet页面中
            WriteSheet writeSheet = EasyExcelFactory.writerSheet(pageNo,
                    "第" + pageNo + "页").build();
            excelWriter.write(exportDTOList,writeSheet);
            log.info("结束导出第页 [ {} ]数据",pageNo);
            // 总页数 大于 当前页 说明还有数据，需要再次执行
        }while (pageResult.getPagNum() > pageNo);
        // 4. 收尾 执行finish，才会关闭Excel文件流
        excelWriter.finish();
        log.info("完成导出!");
    }

    @Override
    public void export(UserQueryDTO query, String filename) {

        ByteArrayOutputStream outputStream =
                new ByteArrayOutputStream();

        // 1. 实现数据导出的Excel
        export(outputStream,query);

        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(outputStream.toByteArray());

        // 2. 实现文件上传
        fileService.upload(inputStream,filename);


    }

    @Async("exportServiceExecutor")
    @Override
    public void asyncExport(UserQueryDTO queryDTO, String filename) {
        export(queryDTO,filename);
    }
}