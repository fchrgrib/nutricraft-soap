package org.nutricraft.Services;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


@WebService
public class EmailServicesImpl extends Services implements EmailServices {
    @WebMethod
    public String sendEmail(String to, String from, String password) {
        if(!validateApiKey()){
            System.out.println("API KEY INVALID");
            return null;
        }
        
        String host = "smtp.gmail.com";
        String port = "587";
        System.out.println(from);
        System.out.println(password);
 
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        
        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Berhasil Mendaftar ke Aplikasi Nutricraft");
            message.setContent("<html><body>"+
            "<h2>Konfirmasi Pendaftaran Aplikasi</h2>"+
            "<p>Selamat, Anda telah berhasil mendaftar pada aplikasi kami!</p>"+
            "<p>Terima kasih atas partisipasi Anda. Untuk mulai menggunakan aplikasi, silakan masuk ke akun Anda menggunakan kredensial yang telah Anda daftarkan.</p>"+
            "<p>Jika Anda memiliki pertanyaan atau memerlukan bantuan lebih lanjut, jangan ragu untuk menghubungi tim dukungan kami.</p>"+
            
            "<div >"+
                "<p>-- Nutricraft Team --</p>"+
            "</div>"+
            "</body></html>","text/html");
            Transport.send(message);
            System.out.println("Sent message successfully....");
            log("Sent Email");
            return "Sent message successfully....";
        } catch (MessagingException mex) {
            mex.printStackTrace();
            log("Unable to sent email");
            return "Error: unable to send message....";
        }
    }
}
