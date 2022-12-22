package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.UUID;
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Student {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "faculty")
    private String faculty;

    public Student(UUID id, String name, String faculty) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
    }
}
