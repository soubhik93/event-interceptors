package com.school.infrastructure.messaging.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentDeleteEvent<T> {

    private Object meta;
    private T payload;
}
