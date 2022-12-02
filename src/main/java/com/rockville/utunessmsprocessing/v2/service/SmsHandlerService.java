package com.rockville.utunessmsprocessing.v2.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rockville.utunessmsprocessing.v2.dto.SmsHandler;
import com.rockville.utunessmsprocessing.v2.util.Msisdn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class SmsHandlerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;
    @Value("${spring.rabbitmq.sms.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.sms.queueRoutingKey}")
    private String routingKey;
    @Value("${sms.la}")
    private String la;

    public void pushMessageInQueue(String route, String destination, String source, String message, String encoding, String silent, String passThrough, String ack_Required) throws JsonProcessingException {
        SmsHandler smsHandler = SmsHandler.builder()
                .destination(destination)
                .source(92 + Msisdn.normalizeMsisdn(source))
                .messageBody(message.getBytes(StandardCharsets.UTF_8))
                .messageLength(message.length())
                .optionalParamCount(0)
                .messageId(0)
                .sequenceNum(12391681)
                .esmClass("0")
                .messageEncoding("0")
                .sourceTon(1)
                .sourceNpi(1)
                .destinationTon(2)
                .destinationNpi(1)
                .priority(0).
                registeredDelivery(0)
                .LA(route)
                .build();

        /*  Use in both ways
            SmsHandler smsHandler = new SmsHandler();
        smsHandler.setLA(route);
        smsHandler.setDestination(destination);
        smsHandler.setSource(Msisdn.normalizeMsisdn(source));
        smsHandler.setMessageBody(message.getBytes(StandardCharsets.UTF_8));
        smsHandler.setMessageLength(message.length());
        smsHandler.setOptionalParamCount(0);
        smsHandler.setMessageId(0);
        smsHandler.setSequenceNum(12391681);
        smsHandler.setEsmClass("0");
        smsHandler.setMessageEncoding(encoding);
        smsHandler.setSourceTon(1);
        smsHandler.setSourceNpi(1);
        smsHandler.setDestinationTon(2);
        smsHandler.setDestinationNpi(1);
        smsHandler.setPriority(0);
        smsHandler.setRegisteredDelivery(0);
        smsHandler.setLA(la);*/
        log.info("Sms Object------" + smsHandler);
        log.info("Push Message in Queue");
        String SmsHandler1 = objectMapper.writeValueAsString(smsHandler);
        rabbitTemplate.convertAndSend(exchange, routingKey, SmsHandler1);
        // objectMapper.writeValueAsString(smsHandler);


    }
}
