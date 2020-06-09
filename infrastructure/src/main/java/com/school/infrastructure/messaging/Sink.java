package com.school.infrastructure.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Sink {
    String STUDENT_DELETE_INPUT = "student-deleted-input";

    @Input(STUDENT_DELETE_INPUT)
    SubscribableChannel studentDeletedInputChannel();

}
