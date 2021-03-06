package com.iege.cryptocurrency.service.impl;

import com.iege.cryptocurrency.entity.Monitoring;
import com.iege.cryptocurrency.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;
    @Value("${client.url}")
    private String clientUrl;

    public void sendMonitoringResults(String to, String subject, List<Monitoring> monitorings) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<html><body> Hello! You have successful monitoring results <p>");

            for (Monitoring monitoring : monitorings) {
                stringBuilder.append("Cryptocurrency ")
                        .append(monitoring.getCryptoCurrency().getName())
                        .append(" price is ")
                        .append(monitoring.getMonitoringCondition().getDescription().toLowerCase())
                        .append("(<b>")
                        .append(monitoring.getConditionValue()).append("</b>)")
                        .append(". Current price is <b>").append(monitoring.getCryptoCurrency().getPriceUSD())
                        .append("</b> - ")
                        .append("<a href=" + clientUrl + "monitoring/deactivate/" + monitoring.getId() + ">turn off monitoring</a>")
                        .append("<p>");
            }
            stringBuilder.append("</body></html>");
            helper.setText(stringBuilder.toString(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        emailSender.send(message);
    }
}
