package com.example.demo.messaging;

import com.example.demo.domain.types.AuditMessage;

public interface AuditMessageProducer {
    void send(AuditMessage message);
}
