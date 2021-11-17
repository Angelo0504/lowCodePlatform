package jnpf.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MailModel {
    private String uid;
    private String from;
    private String fromName;
    private String recipient;
    private String toName;
    private String cc;
    private String ccName;
    private String bcc;
    private String bccName;
    private String subject;
    private String bodyText;
    private List<MailFile> attachment;
    private LocalDateTime date;
}
