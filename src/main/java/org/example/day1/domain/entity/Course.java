package org.example.day1.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.example.day1.domain.AuditableEntity;

@Entity
@Table(name = "courses")
@Data
public class Course extends AuditableEntity {
    @Column(name = "course_code", columnDefinition = "VARCHAR(20)", nullable = false, unique = true)
    private String course_code;
    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    private String name;
    @Column(columnDefinition = "VARCHAR(500)")
    private String description;

    @Column(name = "tuition_fee", columnDefinition = "DECIMAL(12,2)", nullable = false)
    private double tuition_fee;

    @Column(name = "total_sessions", nullable = false)
    private int totalSessions = 24;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
}
