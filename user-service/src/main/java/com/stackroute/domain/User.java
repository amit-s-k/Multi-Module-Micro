package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(value = "playlist")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private int userId;
    private  String userName;
    private String userComments;
}
