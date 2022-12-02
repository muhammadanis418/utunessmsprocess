package com.rockville.utunessmsprocessing.v2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rockville.utunessmsprocessing.v2.dto.SmsHandler;
import com.rockville.utunessmsprocessing.v2.service.SmsHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsHandlerController {
    @Autowired
    private SmsHandlerService smsHandlerService;


    @GetMapping("/laTola")
    public String pushMessage(@RequestParam String route, @RequestParam String destination, @RequestParam String source, @RequestParam String message, @RequestParam String encoding, @RequestParam(required = false) String silent, @RequestParam(required = false) String passThrough, @RequestParam(required = false) String ack_Required) throws JsonProcessingException {


        smsHandlerService.pushMessageInQueue(route, destination, source, message, encoding, silent, passThrough, ack_Required);
        return "Message Sent Successfully------";
    }

}
