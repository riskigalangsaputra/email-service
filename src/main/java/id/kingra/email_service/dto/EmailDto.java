package id.kingra.email_service.dto;

import lombok.Data;

@Data
public class EmailDto {

    private String to;
    private String subject;
    private String body;
}
