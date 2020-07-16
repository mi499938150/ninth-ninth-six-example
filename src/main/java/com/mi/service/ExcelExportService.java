package com.mi.service;

import com.mi.domain.dto.UserQueryDTO;

/**
 * @author : Rong
 * @date : 2020/7/16
 * @Desc: Excel 导出服务接口
 */
public interface ExcelExportService {

    /**
     * 导出服务
     * @param queryDTO
     */
    void export(UserQueryDTO queryDTO, String filename);

    /**
     * 异步导出
     * @param queryDTO
     * @param filename
     */
    void asyncExport(UserQueryDTO queryDTO, String filename);
}
