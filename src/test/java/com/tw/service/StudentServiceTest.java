package com.tw.service;

import com.tw.core.GradeReportBuilder;
import com.tw.core.model.Student;
import com.tw.core.respository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by jxzhong on 2017/7/27.
 */
@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private GradeReportBuilder gradeReportBuilder;

    @Test
    public void should_add_student() throws Exception {
        //Given
        Student stu = new Student("Tom", "1", 90, 88, 98, 100);
        StudentService studentGradeService = new StudentService(this.studentRepository, this.gradeReportBuilder);
        //When
        studentGradeService.addStudent(stu);
        //Then
        verify(this.studentRepository, times(1)).addStudents(stu);
    }

    @Test
    public void should_not_add_student_when_student_number_exist() throws Exception {
        //Given
        Student stu = new Student("Tom", "1", 90, 88, 98, 100);
        StudentService studentGradeService = new StudentService(this.studentRepository, this.gradeReportBuilder);

        when(this.studentRepository.isExist(stu.getNumber())).thenReturn(true);
        //When
        Boolean isSuccess = studentGradeService.addStudent(stu);
        //Then
        verify(this.studentRepository, times(0)).addStudents(stu);
        assertFalse(isSuccess);
    }


    @Test
    public void should_search_students_when_all_exist_in_repository() throws Exception {
        //Given
        StudentService studentGradeService = new StudentService(this.studentRepository, this.gradeReportBuilder);
        List<Student> studentInfos = asList(
                new Student("1"),
                new Student("2")
        );
        when(studentRepository.findStudentByNumber("1")).thenReturn(new Student("1"));
        when(studentRepository.findStudentByNumber("2")).thenReturn(new Student("2"));

        //When
        List<Student> students = studentGradeService.findByStudentsByNumbers(studentInfos);
        //Then
        assertThat(students.size(), is(2));
        verify(this.studentRepository, times(2)).findStudentByNumber(anyString());

    }

    @Test
    public void should_return_null_when_there_is_no_stu_number_exist() throws Exception {
        //Given
        StudentService studentGradeService = new StudentService(this.studentRepository, this.gradeReportBuilder);
        List<Student> studentInfos = asList(
                new Student("1")
        );
        when(this.studentRepository.findStudentByNumber("1")).thenReturn(null);

        //When
        List<Student> students = studentGradeService.findByStudentsByNumbers(studentInfos);
        //Then
        assertThat(students.size(), is(0));
        verify(this.studentRepository, times(1)).findStudentByNumber(anyString());

    }

}
