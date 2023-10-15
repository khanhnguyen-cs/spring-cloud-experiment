package com.example.gradeservice.controller;

import com.example.gradeservice.domain.StudentGrade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grades")
public class GradeController {

    @GetMapping("/{studentId}/{courseId}")
    public StudentGrade getGrade(@PathVariable String studentId, @PathVariable String courseId) {
        return StudentGrade.builder()
                .studentId(studentId)
                .courseId(courseId)
                .grade("A")
                .build();
    }
}
