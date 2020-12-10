package com.example.todolist.model.payload;

import com.example.todolist.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestCreatePayload {


    private String title;

    private String description;

    private Date createdTime = new Date();

//    ,
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "YYYY-MM-dd")
    private Date deadline;

    private Long userId;
}
