package com.example.todolist.model;


import com.example.todolist.model.payload.UserPayload;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "user_owner")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "userId")
    @JsonIgnore
    private List<Task> tasks;

    public static User fromPayload(UserPayload userPayload){
        return User.builder()
                .firstName(userPayload.getFirstName())
                .lastName(userPayload.getLastName())
                .build();
    }

}
