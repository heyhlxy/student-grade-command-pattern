package com.tw.service;

import com.tw.core.GradeReportBuilder;
import com.tw.core.model.Gradereport;
import com.tw.core.model.Student;

import java.util.List;

/**
 * Created by jxzhong on 2017/8/9.
 */
public class ReportService {

    private final StudentService studentService;
    private final GradeReportBuilder gradeReportBuilder;

    public ReportService(StudentService studentRepository,
                         GradeReportBuilder gradeReportBuilder) {

        this.studentService = studentRepository;
        this.gradeReportBuilder = gradeReportBuilder;
    }


    public Gradereport generateReport(List<Student> stuList) {

        List<Student> students = studentService.findByStudentsByNumbers(stuList);
        Gradereport gradereport = null;

        if (!students.isEmpty()) {

            gradereport = this.gradeReportBuilder.buildStudentGradeReport(students);
        }
        return gradereport;
    }

}
