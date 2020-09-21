package com.ximi.train.customer.integration;

import com.ximi.train.customer.model.Course;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
@EnableBinding(CourseProduce.class)
public class CourseMessageSend {

    @Resource
    @Qualifier(CourseProduce.name)
    private MessageChannel messageChannel;

    public void save(Course course) {
        try {
            messageChannel.send(MessageBuilder.withPayload(course).build());
        } catch (Exception ex) {
            log.error("save course exception", ex);
        }
    }

}
