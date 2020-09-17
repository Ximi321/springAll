package com.ximi.train.integration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CourseConsumer {

    String name = "course";

    @Input(name)
    public SubscribableChannel courseInput();

}
