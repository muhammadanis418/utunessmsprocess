package com.rockville.utunessmsprocessing.v2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class SmsHandlerConsumer {
    @RabbitListener(queues = "utunes.queue")//concurrency = "3-6")
    public void receiveMessage(String smsHandler) throws InterruptedException {
        // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        // gets the name of current thread
        System.out.println(
                "Current Thread Name: "
                        + Thread.currentThread().getName());
        System.out.println(("Consume Message from Queue------{}" + smsHandler + " " + now));
       // System.out.println("Going to sleep now for 12s");
       // Thread.sleep(12000L);
    }
}
