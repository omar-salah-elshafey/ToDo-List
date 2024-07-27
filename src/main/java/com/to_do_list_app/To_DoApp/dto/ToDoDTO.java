package com.to_do_list_app.To_DoApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoDTO {
    private String title;
    private String description;
    private Boolean isCompleted;
    private Date createdAt;
    private Date updatedAt;
}
