package com.rtm.pabean.controller;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rtm.pabean.dto.Response;
import com.rtm.pabean.service.PabeanService;
import com.rtm.pabean.utils.FileUtil;

@RestController
@RequestMapping("/pabean")
public class PabeanController {

    @Value("${rtm.pabean.folder.temp}")
    private String tempFolder;
    
    private PabeanService pabeanService;

    @Autowired
    public PabeanController(PabeanService pabeanService) {
        this.pabeanService = pabeanService;
    }

    @PostMapping("/{documentType}/incoming-document")
    public ResponseEntity<?> incomingDocument(@PathVariable String documentType,
        @RequestPart("orderId") String orderId, @RequestPart("companyId") String companyId,
        @RequestPart("file") MultipartFile file) {

        Response<String> response = new Response<>();
        response.setTimestamp(new Date());
        try {
            File filePath = FileUtil.createTempFile(file, tempFolder);
            if (filePath.length() == 0)
                throw new Exception("File failed to store");

            response.getData().add(pabeanService.incomingDocument(documentType, orderId, companyId, filePath));
            response.setSuccess("Completed incoming document");
            response.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setError(e.getLocalizedMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{documentType}/incoming-document")
    public ResponseEntity<?> updatedDocument(@PathVariable String documentType,
        @RequestPart("orderId") String orderId, @RequestPart("companyId") String companyId, 
        @RequestPart("file") MultipartFile file) {

        Response<String> response = new Response<>();
        response.setTimestamp(new Date());
        try {
            File filePath = FileUtil.createTempFile(file, tempFolder);
            if (filePath.length() == 0)
                throw new Exception("File failed to store");
                
            response.getData().add(pabeanService.updatedDocument(documentType, orderId, companyId, filePath));
            response.setSuccess("Completed updated document");
            response.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setError(e.getLocalizedMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
