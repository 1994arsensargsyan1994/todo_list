package com.example.todolist.model.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestUpdatePayload {





    private Date archiveDateTime;


    private LocalDate trackingTime;


    private Date doneTime;
}
