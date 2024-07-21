package com.kabera.sobersteps.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageDto {

    private String content;
    private String sender;
    private String type;
    private String targetType;
    private Long targetId;
}
