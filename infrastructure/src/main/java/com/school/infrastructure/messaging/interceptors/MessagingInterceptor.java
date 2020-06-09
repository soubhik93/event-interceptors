package com.school.infrastructure.messaging.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.config.GlobalChannelInterceptor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.stereotype.Component;

import static com.school.infrastructure.messaging.Sink.STUDENT_DELETE_INPUT;

@Slf4j
@Component
@GlobalChannelInterceptor(patterns = {STUDENT_DELETE_INPUT})
public class MessagingInterceptor extends ChannelInterceptorAdapter {
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("Things we can do before the event reaches the Sink");
        return super.preSend(message, channel);
    }
}
