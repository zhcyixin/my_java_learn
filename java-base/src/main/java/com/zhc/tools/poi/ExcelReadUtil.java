package com.zhc.tools.poi;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExcelReadUtil {
    private static final String EXCEL_XLS = ".xls";
    private static final String EXCEL_XLSX = ".xlsx";


    public static Map<String, List<List<String>>> excelReader(File file) throws Exception {
        verifyExcelFile(file);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = getWorkbook(inputStream, file);
        int sheetNumber = workbook.getNumberOfSheets();
        Map<String, List<List<String>>> dataMap = new HashMap<>();
        //遍历sheet
        for (int sheetIndex = 0; sheetIndex < sheetNumber; sheetIndex++) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            if (sheet == null) {
                continue;
            }
            //每次循环到一个不为空的sheet就创建一个集合存储数据
            List<List<String>> dataList = new ArrayList<>();
            String sheetName = sheet.getSheetName();
            for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                //遍历行中的每一个单元格
                List<String> cellList = new ArrayList<>();
                int cellNum = row.getLastCellNum();
                for (int cellIndex = 0; cellIndex < cellNum; cellIndex++) {
                    Cell cell = row.getCell(cellIndex);
                    String cellValue = getCellValue(cell);
                    cellList.add(cellValue);
                }
                dataList.add(cellList);
            }
            dataMap.put(sheetName, dataList);
        }
        inputStream.close();
        return dataMap;
    }


    private static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null || "".equalsIgnoreCase(cell.toString().trim())) {
            return cellValue;
        }
        CellType cellType = cell.getCellTypeEnum();
        //String 类型
        if (cellType == CellType.STRING) {
            cellValue = cell.getStringCellValue().trim();
        }
        //带有数字类型
        if (cellType == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                cellValue = DateFormatUtils.format(cell.getDateCellValue().getTime(), "yyyy-MM-dd");
            } else {
                cellValue = new DecimalFormat("#.######").format(cell.getNumericCellValue());
            }
        }
        //Boolean类型
        if (cellType == CellType.BOOLEAN) {
            cellValue = String.valueOf(cell.getBooleanCellValue());
        }
        //错误单元格
        if (cellType == CellType.ERROR) {
            cellValue = "ErrorType";
        }
        //空值
        if (cellType == CellType.BLANK) {
            return cellValue;
        }
        //公式型
        if (cellType == CellType.FORMULA) {
            cellValue = cell.getCellFormula();
        }
        return cellValue;
    }


    private static Workbook getWorkbook(FileInputStream inputStream, File file) throws IOException {
        Workbook workbook = null;
        if (file.getName().endsWith(EXCEL_XLS)) {
            workbook = new HSSFWorkbook(inputStream);
        }
        if (file.getName().endsWith(EXCEL_XLSX)) {
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }


    private static void verifyExcelFile(File file) {
        String fileName = file.getName();
        //判断file是否存在
        if (!file.exists()) {
            throw new RuntimeException("文件不存在!");
        }
        //判断是不是Excel文件
        if (!file.isFile()) {
            throw new RuntimeException("不是文件!");
        }
        if (!(fileName.endsWith(EXCEL_XLS) || fileName.endsWith(EXCEL_XLSX))) {
            throw new RuntimeException("不是Excel文件");
        }
    }


    private ExcelReadUtil() {
    }


}

