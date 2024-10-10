package com.capstone.stagecoach.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "stagecoach_users")
public class User {
    
    @Id
    private String id;
    
    private String username;
    private String password;
    private String email;
    private Set<String> roles;
}
