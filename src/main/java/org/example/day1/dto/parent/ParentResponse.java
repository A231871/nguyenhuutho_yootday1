package org.example.day1.dto.parent;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentResponse {
    private long id;
    private String fullName;
    private String phone;
    private String email;
    private String address;
    private String relationship;
    private String gender;
}
