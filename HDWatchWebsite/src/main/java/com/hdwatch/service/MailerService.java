package com.hdwatch.service;

import javax.mail.MessagingException;

import com.hdwatch.entity.MailInfo;

public interface MailerService {
    
    // Gửi email
    void send(MailInfo mail) throws MessagingException;
    
    // Gửi email với thông tin cụ thể
    void send(String to, String subject, String body) throws MessagingException;

    // Đưa email vào hàng đợi để gửi
    void queue(MailInfo mail) throws MessagingException;
    
    // Đưa email vào hàng đợi để gửi với thông tin cụ thể
    void queue(String to, String subject, String body) throws MessagingException;

}
