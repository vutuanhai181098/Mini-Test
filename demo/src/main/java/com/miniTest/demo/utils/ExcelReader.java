package com.miniTest.demo.utils;

import com.miniTest.demo.model.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Component
public class ExcelReader implements IFileReader{
    private final int COLUMN_INDEX_ID = 0;
    private final int COLUMN_INDEX_NAME = 1;
    private final int COLUMN_INDEX_EMAIL = 2;
    private final int COLUMN_INDEX_PHONE = 3;
    private final int COLUMN_INDEX_ADDRESS = 4;
    private final int COLUMN_INDEX_AVATAR = 5;
    private final int COLUMN_INDEX_PASSWORD = 6;
    @Override
    public List<User> readFile(String filePath) {
        List<User> userList = new ArrayList<>();
        try {
            ClassPathResource resource = new ClassPathResource(filePath);
            InputStream inputStream = resource.getInputStream();

            Workbook workbook = getWorkbook(inputStream, filePath);
            Sheet sheet = workbook.getSheet("data");
            for (Row row : sheet){
                if(row.getRowNum() == 0){
                    continue;
                }
                Iterator<Cell> cellIterator = row.cellIterator();
                User user = new User();
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    setUser(user, cell);
                }
                userList.add(user);
            }
            workbook.close();
            inputStream.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return userList;
    }

    private void setUser(User user, Cell cell) {
        int columnIndex = cell.getColumnIndex();
        Object cellValue = getCellValue(cell);
        switch (columnIndex){
            case COLUMN_INDEX_ID :
                user.setId((Integer) cellValue);
                break;
            case COLUMN_INDEX_NAME :
                user.setName((String) cellValue);
                break;
            case COLUMN_INDEX_EMAIL :
                user.setEmail((String) cellValue);
                break;
            case COLUMN_INDEX_PHONE :
                user.setPhone((String) cellValue);
                break;
            case COLUMN_INDEX_ADDRESS :
                user.setAddress((String) cellValue);
                break;
            case COLUMN_INDEX_AVATAR :
                user.setAvatar((String) cellValue);
                break;
            case COLUMN_INDEX_PASSWORD :
                user.setPassword((String) cellValue);
        }
    }

    public Workbook getWorkbook(InputStream inputStream, String filePath) throws IOException {
        Workbook workbook = null;
        if (filePath.endsWith("xlsx")){
            workbook = new XSSFWorkbook(inputStream);
        } else if(filePath.endsWith("xls")){
            workbook = new HSSFWorkbook(inputStream);
        } else {
            System.out.println("File isn't in the correct format!");
        }
        return workbook;
    }
    public Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        switch (cellType){
            case NUMERIC :
                cellValue = cell.getNumericCellValue();
                break;
            case STRING :
                cellValue = cell.getStringCellValue();
                break;
        }
        return cellValue;
    }
}
