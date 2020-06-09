package com.school.infrastructure.messaging.listeners;

import com.school.domain.SchoolService;
import com.school.infrastructure.messaging.Sink;
import com.school.infrastructure.messaging.events.StudentDeleteEvent;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import static com.school.infrastructure.messaging.Sink.STUDENT_DELETE_INPUT;
import static java.lang.Integer.valueOf;

@Component
@AllArgsConstructor
@EnableBinding(Sink.class)
public class DeleteStudentListener {

    private SchoolService service;

    @StreamListener(STUDENT_DELETE_INPUT)
    public void onMessage(StudentDeleteEvent<String> deleteStudentEvent) {
        service.delete(valueOf(deleteStudentEvent.getPayload()));
    }

}
