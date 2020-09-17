package com.ximi.train.customer.integration;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CourseProduce {

    public String name = "course";

    @Output(CourseProduce.name)
    public MessageChannel courseInput();

}
