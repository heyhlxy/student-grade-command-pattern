package com.tw.commandend.command;

import com.tw.commandend.controller.RouteController;
import com.tw.commandend.transform.CmdIOTransformer;
import com.tw.core.model.Gradereport;
import com.tw.core.model.Student;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.tw.commandend.controller.RouteController.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by jxzhong on 2017/9/8.
 */
public class GenGradereportCommandTest extends CommandTest {

    @Test
    public void should_print_generate_report_msg_and_print_report_when_init_display_msg_then_input_correct_nunmber() throws Exception {
        //Given
        GenGradereportCommand command = new GenGradereportCommand(DISPLAY_GRADE_REPORT_PAGE);
        CmdIOTransformer cmdIOTransformer = mock(CmdIOTransformer.class);
        GradeCommandAdapterService gradeCommandAdapterService = mock(GradeCommandAdapterService.class);

        RouteController controller = mock(RouteController.class);
        MainMenuCommand mainCommand = mock(MainMenuCommand.class);
        when(controller.generateCommand(DISPLAY_MAIN_MENU_PAGE)).thenReturn(mainCommand);
        doNothing().when(mainCommand).initDisplay();

        command.setRouter(controller);
        command.setTransformer(cmdIOTransformer);
        command.setGradeCommandAdapterService(gradeCommandAdapterService);

        String stuNumber = "122";
        Gradereport genGradeReport = new Gradereport();
        List<Student> stuList = Arrays.asList(new Student(stuNumber));
        when(cmdIOTransformer.formatStudentNos(stuNumber)).thenReturn(stuList);
        when(gradeCommandAdapterService.generateReport(stuList)).thenReturn(genGradeReport);
        when(cmdIOTransformer.formatReportText(genGradeReport)).thenReturn("report content");

        System.setIn(new ByteArrayInputStream(stuNumber.getBytes()));
        Scanner scanner = new Scanner(System.in);
        //When

        command.cliReader = scanner;
        command.initDisplay();
        //Then
        verify(mainCommand, times(1)).initDisplay();
        assertEquals("请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n\n" +
                "report content\n\n", outContent.toString());
    }

    @Test
    public void should_print_error_msg_when_input_incorrect_msg() throws Exception {
        //Given
        GenGradereportCommand command = new GenGradereportCommand(DISPLAY_GRADE_REPORT_PAGE);
        CmdIOTransformer cmdIOTransformer = mock(CmdIOTransformer.class);
        GradeCommandAdapterService gradeCommandAdapterService = mock(GradeCommandAdapterService.class);

        RouteController controller = mock(RouteController.class);
        ErrorCommand errorCommand = mock(ErrorCommand.class);
        when(controller.generateCommand(COMMON_ERROR_PAGE)).thenReturn(errorCommand);
        doNothing().when(errorCommand).initDisplay(anyString(), eq(command));

        command.setRouter(controller);
        command.setTransformer(cmdIOTransformer);
        command.setGradeCommandAdapterService(gradeCommandAdapterService);

        String stuNumber = "error number";
        when(cmdIOTransformer.formatStudentNos(stuNumber)).thenReturn(new ArrayList<>());
        when(gradeCommandAdapterService.generateReport(any())).thenReturn(null);

        System.setIn(new ByteArrayInputStream(stuNumber.getBytes()));
        Scanner scanner = new Scanner(System.in);
        //When

        command.cliReader = scanner;
        command.initDisplay();
        //Then
        String errorContent = "请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：";
        verify(errorCommand, times(1)).initDisplay(eq(errorContent), eq(command));

    }


}