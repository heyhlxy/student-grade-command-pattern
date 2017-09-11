package com.tw.service;

import com.tw.core.GradeReportBuilder;
import com.tw.core.model.Gradereport;
import com.tw.core.model.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.*;

/**
 * Created by jxzhong on 2017/8/9.
 */
public class ReportServiceTest {

    @Test
    public void shoule_gen_grade_report_when_as_last_1_student_exist() throws Exception {
        //Given
        GradeReportBuilder gradeReportBuilder = mock(GradeReportBuilder.class);
        StudentService studentService = mock(StudentService.class);
        ReportService reportService = new ReportService(studentService, gradeReportBuilder);

        List<Student> mockStudents = Arrays.asList(new Student("001"));
        when(studentService.findByStudentsByNumbers(anyList())).thenReturn(mockStudents);
        Gradereport mockReport = new Gradereport();
        when(gradeReportBuilder.buildStudentGradeReport(mockStudents)).thenReturn(mockReport);
        //When

        Gradereport gradereport = reportService.generateReport(mockStudents);

        //Then
        verify(gradeReportBuilder, times(1)).buildStudentGradeReport(mockStudents);
        assertThat(gradereport, is(mockReport));
    }

    @Test
    public void shoule_return_null_when_all_students_are_not_exist() throws Exception {
        //Given
        GradeReportBuilder gradeReportBuilder = mock(GradeReportBuilder.class);
        StudentService studentService = mock(StudentService.class);
        ReportService reportService = new ReportService(studentService, gradeReportBuilder);

        List<Student> mockStudents = Arrays.asList(new Student("001"));
        when(studentService.findByStudentsByNumbers(anyList())).thenReturn(new ArrayList());
        //When

        Gradereport gradereport = reportService.generateReport(mockStudents);

        //Then
        verify(gradeReportBuilder, times(0)).buildStudentGradeReport(mockStudents);
        assertNull(gradereport);
    }
}