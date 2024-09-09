package com.tagtrace.application.port.output;

public interface EmailPort {
    void sendEmail(String email, String message);
}
