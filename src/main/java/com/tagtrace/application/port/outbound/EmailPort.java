package com.tagtrace.application.port.outbound;

public interface EmailPort {
    void sendEmail(String email, String message);
}
