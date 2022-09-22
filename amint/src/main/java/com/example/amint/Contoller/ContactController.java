package com.example.amint.Contoller;

import com.example.amint.Model.Contact;
import com.example.amint.repository.ContactRepository;
import com.example.amint.service.ExcelFileServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/contact")
@AllArgsConstructor
@NoArgsConstructor
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ExcelFileServiceImpl excelFileService;

    @GetMapping("/downloadExcelFile")
    public void downloadExcelFile(HttpServletResponse response) throws IOException {
        List<Contact> contacts = (List<Contact>)contactRepository.findAll();
        ByteArrayInputStream byteArrayInputStream = excelFileService.export(contacts);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=contacts.xlsx");
        IOUtils.copy(byteArrayInputStream, response.getOutputStream());

    }}
