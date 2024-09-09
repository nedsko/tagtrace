package com.tagtrace.adapter.output;

import com.tagtrace.application.port.output.EmailPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Dummy class used to provide an implementation for EmailPort.
 */
@Component
public class EmailAdapter implements EmailPort {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailAdapter.class);

    @Override
    public void sendEmail(String email, String message) {
        LOGGER.info("Sending email to '{}' with message '{}'", email, message);
        LOGGER.warn("Using dummy EmailAdapter. No email actually sent");
    }
}
