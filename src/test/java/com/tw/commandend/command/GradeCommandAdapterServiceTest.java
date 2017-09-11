package com.tw.commandend.command;

import com.tw.core.model.Gradereport;
import com.tw.core.model.Student;
import com.tw.service.ReportService;
import com.tw.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by jxzhong on 2017/8/9.
 */
@RunWith(MockitoJUnitRunner.class)
public class GradeCommandAdapterServiceTest {

    @Mock
    private StudentService studentService;
    @Mock
    private ReportService reportService;

    @Test
    public void should_generate_report_and_add_student() throws Exception {
        //Given
        Student stu = new Student("Tom", "1", 90, 88, 98, 100);
        GradeCommandAdapterService gradeCommandAdapterService = new GradeCommandAdapterService(this.studentService,
                this.reportService);
        List<Student> stuList = asList(stu);
        when(studentService.findByStudentsByNumbers(anyList())).thenReturn(stuList);

        //When
        Gradereport report = gradeCommandAdapterService.generateReport(stuList);
        gradeCommandAdapterService.addStudent(stu);

        //Then
        verify(this.reportService, times(1)).generateReport(stuList);
        verify(this.studentService, times(1)).addStudent(stu);

    }

}