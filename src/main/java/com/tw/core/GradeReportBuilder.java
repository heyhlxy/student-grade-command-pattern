package com.tw.core;

import com.tw.core.model.Gradereport;
import com.tw.core.model.Student;
import com.tw.core.model.StudentGradeItem;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jxzhong on 2017/7/26.
 */
public class GradeReportBuilder {

    public GradeReportBuilder() {
    }

    public Gradereport buildStudentGradeReport(List<Student> students) {
        Gradereport gradereport = new Gradereport();
        gradereport.setStudentGradeItems(students.stream()
                .map(stu -> new StudentGradeItem(
                        stu.getName(),
                        stu.getNumber(),
                        stu.getMathsScore(),
                        stu.getChineseScore(),
                        stu.getEnglishScore(),
                        stu.getProgramScore())).collect(Collectors.toList()));
        return gradereport;
    }

}
