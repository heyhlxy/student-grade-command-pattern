package com.tw.core;

import com.tw.core.model.Student;
import com.tw.core.respository.StudentRepository;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

/**
 * Created by jxzhong on 2017/7/26.
 */
public class StudentRepositoryTest {
    @Test
    public void should_get_student_after_insert_two_students() throws Exception {
        //Given
        Student stu1 = new Student("Tom", "1", 90, 88, 98, 100);
        Student stu2 = new Student("Jim", "2", 95, 98, 92, 80);
        StudentRepository studentRepository = new StudentRepository();

        //When
        studentRepository.addStudents(stu1, stu2);
        Student tom = studentRepository.findStudentByNumber("1");

        //Then
        assertEquals(tom.getNumber(), "1");
    }

    @Test
    public void should_match_students_after_insert_two_students_when_input_name() throws Exception {
        //Given
        Student stu1 = new Student("Tom", "1", 90, 88, 98, 100);
        Student stu2 = new Student("Jim", "2", 95, 98, 92, 80);
        StudentRepository studentRepository = new StudentRepository();

        //When
        studentRepository.addStudents(stu1, stu2);
        Student stu = studentRepository.findStudentByNumber("om");

        //Then
        assertNull(stu);

    }

    @Test
    public void should_match_students_and_judge_exist_after_insert_two_students_when_input_number() throws Exception {
        //Given
        Student stu1 = new Student("Tom", "001", 90, 88, 98, 100);
        Student stu2 = new Student("Jim", "2", 95, 98, 92, 80);
        StudentRepository studentRepository = new StudentRepository();

        //When
        studentRepository.addStudents(stu1, stu2);
        Student stu = studentRepository.findStudentByNumber("001");
        boolean isExist = studentRepository.isExist("001");

        //Then
        assertTrue(isExist);
        assertNotNull(stu);
        assertEquals(stu.getName(), stu1.getName());
    }
}
