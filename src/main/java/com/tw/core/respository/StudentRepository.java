package com.tw.core.respository;

import com.tw.core.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * Created by jxzhong on 2017/7/26.
 */

public class StudentRepository {
    private List<Student> students;

    public StudentRepository() {
        students = new ArrayList<>();
    }

    public void addStudents(Student... stu) {
        this.students.addAll(asList(stu));
    }

    public Student findStudentByNumber(String stuNumber) {
        return this.students.stream()
                .filter(stu -> stu.getNumber().equals(stuNumber))
                .map(Student::new)
                .findFirst().orElse(null);
    }

    public boolean isExist(String stuNumber) {
        return findStudentByNumber(stuNumber) != null;
    }
}
