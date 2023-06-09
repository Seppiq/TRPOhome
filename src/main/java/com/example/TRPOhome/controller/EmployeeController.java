package com.example.TRPOhome.controller;

import com.example.TRPOhome.exception.ResourceNotFoundException;
import com.example.TRPOhome.export.ExcelService;
import com.example.TRPOhome.export.ResourceDTO;
import com.example.TRPOhome.model.Employee;
import com.example.TRPOhome.model.Student;
import com.example.TRPOhome.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
@CrossOrigin("*")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;

    private final ExcelService excelService;

    @GetMapping()
    public ResponseEntity<List<Employee>> get() {
        return ok(employeeService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Void> save(@Validated @RequestBody Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        //employee.setRole("ROLE_USER");
        employeeService.save(employee);
        return ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employee) {
        Employee updateEmployee = employeeService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        updateEmployee.setName(employee.getName());
        updateEmployee.setLastname(employee.getLastname());
        updateEmployee.setUsername(employee.getUsername());
        updateEmployee.setBirthdate(employee.getBirthdate());
        updateEmployee.setPosition(employee.getPosition());
        updateEmployee.setPassword(employee.getPassword());
        employeeService.save(updateEmployee);

        return ResponseEntity.ok(updateEmployee);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id) {
        Employee employee = employeeService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        employeeService.delete(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{lastname}")
    public ResponseEntity<Employee> findByLastname(@PathVariable String lastname) {
        return ok(employeeService.findByLastname(lastname));
    }

    //@GetMapping("/export/xls")
    @GetMapping("/export/xls")
    public ResponseEntity<Resource> exportUsers() {
        ResourceDTO resourceDTO = excelService.exportUsers();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition",
                "attachment; filename=" + "User.xlsx");

        return ResponseEntity.ok()
                .contentType(resourceDTO.getMediaType())
                .headers(httpHeaders)
                .body(resourceDTO.getResource());
    }

    @GetMapping("/export/docx")
    public ResponseEntity<byte[]> exportStudentsToWord() throws IOException {
        // create a new document
        XWPFDocument document = new XWPFDocument();

        // create a paragraph and add it to the document
        XWPFParagraph paragraph = document.createParagraph();

        // create a run and add it to the paragraph
        XWPFRun run = paragraph.createRun();
        run.setBold(true);
        run.setText("List of Students");
        run.addBreak();

        // get the list of employees from the database
        List<Employee> employees = employeeService.findAll();

                // create a table and add it to the document
        XWPFTable table = document.createTable();
        XWPFTableRow headerRow = table.getRow(0);
        headerRow.getCell(0).setText("ID");
        headerRow.addNewTableCell().setText("Name");
        headerRow.addNewTableCell().setText("Google Username");
        headerRow.addNewTableCell().setText("Google Name");
        headerRow.addNewTableCell().setText("Lastname");
        headerRow.addNewTableCell().setText("Username");
        headerRow.addNewTableCell().setText("Password");
        headerRow.addNewTableCell().setText("Role");
        headerRow.addNewTableCell().setText("Birthdate");
        headerRow.addNewTableCell().setText("Mobile Phone");
        headerRow.addNewTableCell().setText("Position");


        // add the data rows to the table
        for (Employee employee : employees) {
            XWPFTableRow dataRow = table.createRow();
            dataRow.getCell(0).setText(String.valueOf(employee.getId()));
            dataRow.getCell(1).setText(employee.getName());
            dataRow.getCell(2).setText(employee.getGoogleUsername());
            dataRow.getCell(3).setText(employee.getGoogleName());
            dataRow.getCell(4).setText(employee.getLastname());
            dataRow.getCell(5).setText(employee.getUsername());
            dataRow.getCell(6).setText(employee.getPassword());
            dataRow.getCell(7).setText(employee.getRole());
            dataRow.getCell(8).setText(employee.getBirthdate().toString());
            dataRow.getCell(9).setText(employee.getMp());
            dataRow.getCell(10).setText(employee.getPosition());

        }

        // write the document to a byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        document.write(baos);

        // create the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=employees.docx");

        // return the document as a byte array with the appropriate headers
        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }
}
