package org.example.day1.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import org.example.day1.domain.AuditableEntity;

@Entity
@Data
public class Course extends AuditableEntity {
    @Column(columnDefinition = "VARCHAR(20)")
    private String course_code;
    @Column(columnDefinition = "VARCHAR(100)")
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;

    private double tuition_fee;

    private int totalSessions;

    private byte isActive;
}
