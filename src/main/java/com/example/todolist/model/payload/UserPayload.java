package com.example.todolist.model.payload;

import com.example.todolist.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPayload {


    private String firstName;
    private String lastName;

}
