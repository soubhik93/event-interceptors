package com.school;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.domain.entities.Student;
import com.school.infrastructure.messaging.Sink;
import com.school.infrastructure.messaging.events.StudentDeleteEvent;
import com.school.infrastructure.repository.FluentJdbcRepository;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTest
@RunWith(SpringRunner.class)
public class StudentListenerIT {

    @Autowired
    private Sink sink;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FluentJdbcRepository repository;

    @Test
    @SneakyThrows
    public void should_delete_student_record_when_event_received() {
        val studentDeleteEvent = new StudentDeleteEvent<String>(null, "200");
        repository.save(new Student("Hulk", 200, "Greenland"));
        sink.studentDeletedInputChannel()
                .send(MessageBuilder.withPayload(objectMapper.writeValueAsString(studentDeleteEvent))
                        .setHeader(MessageHeaders.CONTENT_TYPE, "application/json")
                        .build()
                );

        assertThat(repository.findOne(200)).isNull();

    }
}
