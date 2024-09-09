package com.tagtrace.application.domain.listener;

import com.tagtrace.application.domain.event.OwnerCreatedEvent;
import com.tagtrace.application.port.output.EmailPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class OwnerEventListener {
    private final EmailPort emailPort;

    @Autowired
    public OwnerEventListener(EmailPort emailPort) {
        this.emailPort = emailPort;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void sendWelcomeEmailOnCreation(OwnerCreatedEvent event) {
        emailPort.sendEmail(event.owner().getEmail().value(), "Welcome %s to TagTrace!".formatted(event.owner().getName().value()));
    }
}
