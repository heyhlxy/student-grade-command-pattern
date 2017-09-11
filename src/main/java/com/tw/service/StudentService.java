package com.tw.service;

import com.tw.core.GradeReportBuilder;
import com.tw.core.model.Student;
import com.tw.core.respository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jxzhong on 2017/7/27.
 */
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository, GradeReportBuilder gradeReportBuilder) {

        this.studentRepository = studentRepository;
    }

    public Boolean addStudent(Student stu) {
        try {
            if (this.studentRepository.isExist(stu.getNumber())) {
                throw new Exception("This student number is exist!");
            }
            this.studentRepository.addStudents(stu);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Student> findByStudentsByNumbers(List<Student> studentInfos) {
        return studentInfos.stream()
                .map(stuInfo -> studentRepository.findStudentByNumber(stuInfo.getNumber()))
                .filter(stuInfo -> stuInfo != null).collect(Collectors.toList());
    }
}
