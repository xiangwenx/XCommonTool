package cn.xtool.util;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.xtool.model.CellData;
import com.google.common.collect.Lists;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

/**
 * @author xwxu
 * @description
 * @date 2022-07-28
 */
public class EasyExcelUtil {
    public static void main(String[] args) throws IOException {
        String path = "D:\\Desktop\\企业碳排放+ (2).xlsx";
        List<List<CellData>> rows = getRows(Files.newInputStream(new File(path).toPath()), 0, 20, 0, 41, false);
        System.out.println(rows);
    }

    /***
     * @description: 指定行列范围强制读取Excel单元格
     *
     * @param: inputStream 输入文件流
     * @param: startRow 开始行 (0行开始
     * @param: endRow   结束行(0行结束
     * @param: startCell    开始列(0列开始
     * @param: endCell  结束列(0列结束
     * @param: enforce  强制读取单元格( 空返回null
     * @param: readHeader  是否读取表头
     **/
    public static List<List<CellData>> getRows(InputStream inputStream, int startRow, int endRow, int startCell, int endCell, boolean readHeader) {
        List<List<CellData>> metadata = Lists.newArrayList();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        int rowCount = reader.getRowCount();
        if (startRow < 0) {
            startRow = 0;
        }
        if (endCell < 0) {
            endCell = 0;
        }
        if (endRow > rowCount) {
            endRow = rowCount;
        }
        List<Object> headers = reader.read(0).get(0);
        if (endCell > headers.size()) {
            endCell = headers.size();
        }
        for (int y = startRow; y <= endRow; y++) {
            List<CellData> rowData = Lists.newArrayList();
            metadata.add(rowData);
            for (int x = startCell; x <= endCell; x++) {
                rowData.add(new CellData(reader.readCellValue(x, y), y, x));
            }
        }
        if (readHeader) {
            List<CellData> headerData = Lists.newArrayList();
            for (int i = 0; i < headers.size(); i++) {
                headers.add(new CellData(headers.get(i), 1, i));
            }
            metadata.add(0, headerData);
        }
        return metadata;
    }
}
