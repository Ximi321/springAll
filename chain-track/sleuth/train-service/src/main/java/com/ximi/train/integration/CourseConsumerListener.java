package com.ximi.train.integration;

import com.ximi.train.model.Course;
import com.ximi.train.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
@EnableBinding(CourseConsumer.class)
public class CourseConsumerListener {

    @Resource
    private CourseRepository courseRepository;

    @StreamListener(CourseConsumer.name)
    public void save(Course course){
        try {
            log.info("course recevie data: {}",course);
            courseRepository.save(course);
        } catch (Exception ex){
            log.error("save exception",ex);
        }
    }

}
