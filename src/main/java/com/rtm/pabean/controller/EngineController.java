package com.rtm.pabean.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rtm.pabean.dto.Response;
import com.rtm.pabean.service.ParserEngine;
import com.rtm.pabean.service.SubmitService;

@RestController
@RequestMapping("/pabean/engine")
public class EngineController {
    
    private ParserEngine<com.rtm.pabean.model.bue.Inbox> bueParserEngine;

    private ParserEngine<com.rtm.pabean.model.bup.Inbox> bupParserEngine;

    private SubmitService<com.rtm.pabean.model.bue.Outbox> bueSubmitService;

    private SubmitService<com.rtm.pabean.model.bup.Outbox> bupSubmitService;

    public EngineController(
        @Qualifier("bueParserServiceImp") ParserEngine<com.rtm.pabean.model.bue.Inbox> bueParserEngine, 
        @Qualifier("bupParserServiceImp") ParserEngine<com.rtm.pabean.model.bup.Inbox> bupParserEngine, 
        @Qualifier("bueSubmitServiceImp") SubmitService<com.rtm.pabean.model.bue.Outbox> bueSubmitService,
        @Qualifier("bupSubmitServiceImp") SubmitService<com.rtm.pabean.model.bup.Outbox> bupSubmitService
    ) {
        this.bueParserEngine = bueParserEngine;
        this.bupParserEngine = bupParserEngine;
        this.bueSubmitService = bueSubmitService;
        this.bupSubmitService = bupSubmitService;
    }

    @GetMapping("/parser/start")
    public ResponseEntity<?> parserStart() {
        Response<String> response = new Response<>();
        response.setTimestamp(new Date());
        try {

            bueParserEngine.setStoped(false);
            bueParserEngine.setPaused(false);
            bueParserEngine.runEngine();

            bupParserEngine.setStoped(false);
            bupParserEngine.setPaused(false);
            bupParserEngine.runEngine();

            response.setStatus(HttpStatus.OK.value());
            response.setSuccess("Engine running for parser bue & bup");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setError(e.getLocalizedMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("/ceisa/start")
    public ResponseEntity<?> ceisaStart() {
        Response<String> response = new Response<>();
        response.setTimestamp(new Date());
        try {

            bueSubmitService.setStoped(false);
            bueSubmitService.setPaused(false);
            bueSubmitService.runEngine();

            bupSubmitService.setStoped(false);
            bupSubmitService.setPaused(false);
            bupSubmitService.runEngine();

            response.setStatus(HttpStatus.OK.value());
            response.setSuccess("Engine running for ceisa bue & bup");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setError(e.getLocalizedMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("/parser/pause")
    public ResponseEntity<?> parserPause() {
        bueParserEngine.setPaused(true);
        bupParserEngine.setPaused(true);

        Response<?> response = new Response<>();
        response.setStatus(HttpStatus.OK.value());
        response.setSuccess("Engine paused for parser bue & bup");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/ceisa/pause")
    public ResponseEntity<?> ceisaPause() {
        bueSubmitService.setPaused(true);
        bupSubmitService.setPaused(true);
        
        Response<?> response = new Response<>();
        response.setStatus(HttpStatus.OK.value());
        response.setSuccess("Engine paused for ceisa bue & bup");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/parser/stop")
    public ResponseEntity<?> parserStop() {
        bueParserEngine.setStoped(true);
        bupParserEngine.setStoped(true);

        Response<?> response = new Response<>();
        response.setStatus(HttpStatus.OK.value());
        response.setSuccess("Engine stoped for parser bue & bup");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/ceisa/stop")
    public ResponseEntity<?> ceisaStop() {
        bueSubmitService.setStoped(true);
        bupSubmitService.setStoped(true);

        Response<?> response = new Response<>();
        response.setStatus(HttpStatus.OK.value());
        response.setSuccess("Engine stoped for ceisa bue & bup");
        return ResponseEntity.ok().body(response);
    }
}
