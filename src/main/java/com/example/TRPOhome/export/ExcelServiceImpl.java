package com.example.TRPOhome.export;

import com.example.TRPOhome.model.Employee;
import com.example.TRPOhome.service.EmployeeService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    private EmployeeService employeeService;

    @Override
    public ResourceDTO exportUsers() {
        List<Employee> userList = employeeService.findAll();
        Resource resource = prepareExcel(userList);
        return ResourceDTO.builder().resource(resource).
                mediaType(MediaType.parseMediaType
                        ("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")).build();
    }

    private Resource prepareExcel(List<Employee> userList) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("USERS");

        prepareHeaders(workbook, sheet, "Id", "name", "lastname", "bd", "Role");
        populateUserData(workbook, sheet, userList);

        try (ByteArrayOutputStream byteArrayOutputStream
                     = new ByteArrayOutputStream()) {

            workbook.write(byteArrayOutputStream);
            return new
                    ByteArrayResource
                    (byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException
                    ("Error while generating excel.");
        }
    }

    private void populateUserData(Workbook workbook, Sheet sheet,
                                  List<Employee> userList) {

        int rowNo = 1;
        Font font = workbook.createFont();
        font.setFontName("Arial");

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);

        for (Employee user : userList) {
            int columnNo = 0;
            Row row = sheet.createRow(rowNo);
            populateCell(sheet, row, columnNo++,
                    String.valueOf(user.getId()), cellStyle);
            populateCell(sheet, row, columnNo++,
                    user.getName(), cellStyle);
            populateCell(sheet, row, columnNo++,
                    user.getLastname(), cellStyle);
            populateCell(sheet, row, columnNo++,
                    String.valueOf(user.getBirthdate()), cellStyle);
            populateCell(sheet, row, columnNo++,
                    user.getRole(), cellStyle);
            rowNo++;
        }
    }

    private void populateCell(Sheet sheet, Row row, int columnNo,
                              String value, CellStyle cellStyle) {

        Cell cell = row.createCell(columnNo);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(value);
        sheet.autoSizeColumn(columnNo);
    }

    private void prepareHeaders(Workbook workbook,
                                Sheet sheet, String... headers) {

        Row headerRow = sheet.createRow(0);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontName("Arial");

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);

        int columnNo = 0;
        for (String header : headers) {
            Cell headerCell = headerRow.createCell(columnNo++);
            headerCell.setCellValue(header);
            headerCell.setCellStyle(cellStyle);
        }
    }
}
