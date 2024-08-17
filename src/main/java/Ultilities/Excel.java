package Ultilities;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;

public class Excel {

    private FileInputStream fis;
    private FileOutputStream fileOut;
    private Workbook wb;
    private Sheet sh;
    private Cell cell;
    private Row row;
    private CellStyle cellstyle;
    private Color mycolor;
    private String excelFilePath;
    private Map<String, Integer> columns = new HashMap<>();

    public void setExcelFile(String ExcelPath, String SheetName) throws Exception {
        try {
            File f = new File(ExcelPath);

            if (!f.exists()) {
                f.createNewFile();
                System.out.println("File doesn't exist, so created!");
            }

            fis = new FileInputStream(ExcelPath);
            wb = WorkbookFactory.create(fis);
            sh = wb.getSheet(SheetName);
            //sh = wb.getSheetAt(0); //0 - index of 1st sheet
            if (sh == null) {
                sh = wb.createSheet(SheetName);
            }

            this.excelFilePath = ExcelPath;

            //adding all the column header names to the map 'columns'
            sh.getRow(0).forEach(cell ->{
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getCellData(int rownum, int colnum) throws Exception{
        try{
            cell = sh.getRow(rownum).getCell(colnum);
            String CellData = null;
            switch (cell.getCellType()){
                case STRING:
                    CellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell))
                    {
                        CellData = String.valueOf(cell.getDateCellValue());
                    }
                    else
                    {
                        CellData = String.valueOf((long)cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    CellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    CellData = "";
                    break;
            }
            return CellData;
        }catch (Exception e){
            return"";
        }
    }

    //Gọi ra hàm này nè
    public String getCellData(String columnName, int rownum) throws Exception {
        Integer colnum = columns.get(columnName);
        if (colnum == null) {
            throw new Exception("Column name not found: " + columnName);
        }
        return getCellData(rownum, colnum);
    }

    public void setCellData(String text, int rownum, int colnum) throws Exception {
        try{
            row  = sh.getRow(rownum);
            if(row ==null)
            {
                row = sh.createRow(rownum);
            }
            cell = row.getCell(colnum);

            if (cell == null) {
                cell = row.createCell(colnum);
            }
            cell.setCellValue(text);

            fileOut = new FileOutputStream(excelFilePath);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
        }catch(Exception e){
            throw (e);
        }
    }

    public int getRowCount(String SheetName) {
        int rowCount = 0;
        Sheet sheet = wb.getSheet(SheetName);
        if (sheet != null) {
            rowCount = sheet.getPhysicalNumberOfRows();
        }
        return rowCount;
    }
    public String getData(String filePath, String name) {
        String value = null;
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                System.out.println("File doesn't exist, so created!");
            }
            fis = new FileInputStream(filePath);
            this.excelFilePath = filePath;
            for (int rowNum = 0; rowNum <= sh.getLastRowNum(); rowNum++) {
                Row row = sh.getRow(rowNum);
                if (row != null) {
                    for (int col = 0; col < row.getPhysicalNumberOfCells(); col++) {
                        Cell cel = row.getCell(col);
                        if (cel != null && cel.getCellType() == CellType.STRING) {
                            String celValue = cel.getStringCellValue();
                            if (celValue.equals(name)) {
                                Cell valueCol = row.getCell(col + 1);
                                if (valueCol != null) {
                                    switch (valueCol.getCellType()) { //
                                        case STRING:
                                            value = valueCol.getStringCellValue();
                                            break;
                                        case NUMERIC:
                                            if (DateUtil.isCellDateFormatted(valueCol)) {
                                                value = String.valueOf(valueCol.getDateCellValue());
                                            } else {
                                                value = String.valueOf((long) valueCol.getNumericCellValue());
                                            }
                                            break;
                                        case BOOLEAN:
                                            value = Boolean.toString(valueCol.getBooleanCellValue());
                                            break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (value == null) {
                System.out.println("This value does not exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
}