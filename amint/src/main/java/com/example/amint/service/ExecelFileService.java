package com.example.amint.service;

import com.example.amint.Model.Contact;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ExecelFileService {
    ByteArrayInputStream export(List<Contact> Contacts);
}
