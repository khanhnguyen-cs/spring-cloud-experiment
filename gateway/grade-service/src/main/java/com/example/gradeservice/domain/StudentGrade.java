package com.example.gradeservice.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentGrade {
    private String studentId;
    private String courseId;
    private String grade;
}
