package com.lordscave.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private int id;
    private String senderId;
    private String receiverId;
    private String content;
    private String timestamp;
    private boolean delivered;
}
