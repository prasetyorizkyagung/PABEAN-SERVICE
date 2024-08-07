package com.rtm.pabean.controller;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rtm.pabean.dto.Response;
import com.rtm.pabean.dto.ceisa.Queue;
import com.rtm.pabean.model.bup.Outbox;
import com.rtm.pabean.service.bup.BupService;
import com.rtm.pabean.service.ceisa.ResponseService;
import com.rtm.pabean.utils.FileUtil;

@RestController
@RequestMapping("/pabean/bup")
public class BupController {
    
    @Value("${rtm.pabean.folder.temp}")
    private String tempFolder;
    
    private BupService bupService;

    private ResponseService responseService;

    public BupController(BupService bupService, ResponseService responseService) {
        this.bupService = bupService;
        this.responseService = responseService;
    }

    /* @PostMapping("/incoming-document")
    public ResponseEntity<?> incomingDocument(@RequestPart("orderId") String orderId, @RequestPart("companyId") String companyId,
                @RequestPart("file") MultipartFile file) {
        Response<String> response = new Response<>();
        response.setTimestamp(new Date());
        try {
            File filePath = FileUtil.createTempFile(file, tempFolder);
            if (filePath.length() == 0)
                throw new Exception("File failed to store");

            response.getData().add(bupService.incomingDocument(filePath, orderId, companyId));
            response.setSuccess("Incoming document success");
            response.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setError(e.getLocalizedMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        }
    } */

    @PostMapping("/queue/ceisa")
    public ResponseEntity<?> queueCeisa(@RequestBody Queue queue) {
        Response<Outbox> response = new Response<>();
        response.setTimestamp(new Date());
        try {
            Outbox outbox = bupService.queueCeisa(queue);
            
            response.setSuccess("Queue ceisa success");
            response.getData().add(outbox);
            response.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setError(e.getLocalizedMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/response-car/ceisa")
    public ResponseEntity<?> responseCeisaByCar(@RequestBody Queue queue) {
        responseService.responseByCar(queue.getUserName(), queue.getPassword(), queue.getCar());
        Response<Outbox> response = new Response<>();
        response.setTimestamp(new Date());
        response.setSuccess("Withdraw response in progress");
        response.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/response-npwp/ceisa")
    public ResponseEntity<?> responseCeisaByNpwp(@RequestBody Queue queue) {
        Response<Outbox> response = new Response<>();
        response.setTimestamp(new Date());
        response.setSuccess("Withdraw response in progress");
        response.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok().body(response);
    }
}
