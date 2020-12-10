package com.example.todolist.model;


import com.example.todolist.model.payload.TaskRequestCreatePayload;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String title;

    private String description;

    @Enumerated(value = EnumType.ORDINAL)
    private Status status;


    private Date createdTime;


    @Column(updatable = false)

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd")
    private Date deadline;

    private Date doneTime;


    private LocalDate trackingTime;

    private Boolean archive;

    private Date archiveDateTime;

    @ManyToOne(cascade = CascadeType.ALL)
    private User userId;

    public static Task fromCreatePayload(TaskRequestCreatePayload taskPayload) {
        return Task.builder().title(taskPayload.getTitle())
                .archive(false)
                .description(taskPayload.getDescription())
                .deadline(taskPayload.getDeadline())
                .createdTime(new Date()).status(Status.TODO)
                .build();
    }


}
