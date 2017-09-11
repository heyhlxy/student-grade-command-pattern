package com.tw.commandend.command;

import com.tw.commandend.controller.RouteController;
import com.tw.commandend.transform.CmdIOTransformer;
import com.tw.core.model.Student;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static com.tw.commandend.controller.RouteController.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by jxzhong on 2017/9/11.
 */
public class AddStuCommandTest extends CommandTest {

    @Test
    public void should_print_add_student_msg_then_add_student_when_input_student_str_info() throws Exception {
        //Given
        AddStuCommand command = new AddStuCommand(DISPLAY_ADD_STUDENT_PAGE);
        CmdIOTransformer cmdIOTransformer = mock(CmdIOTransformer.class);
        GradeCommandAdapterService gradeCommandAdapterService = mock(GradeCommandAdapterService.class);

        RouteController controller = mock(RouteController.class);
        MainMenuCommand mainCommand = mock(MainMenuCommand.class);
        when(controller.generateCommand(DISPLAY_MAIN_MENU_PAGE)).thenReturn(mainCommand);
        doNothing().when(mainCommand).initDisplay();

        command.setRouter(controller);
        command.setTransformer(cmdIOTransformer);
        command.setGradeCommandAdapterService(gradeCommandAdapterService);

        String studentInfoStr = "student correct info";
        Student student = new Student("001");
        when(cmdIOTransformer.formatStudent(studentInfoStr)).thenReturn(student);
        doNothing().when(gradeCommandAdapterService).addStudent(student);

        System.setIn(new ByteArrayInputStream(studentInfoStr.getBytes()));
        Scanner scanner = new Scanner(System.in);
        //When

        command.cliReader = scanner;
        command.initDisplay();
        //Then
        verify(mainCommand, times(1)).initDisplay();
        assertEquals("请输入学生信息（格式：姓名, 学号，数学：分数，语文：分数，英语：分数，编程：分数），按回车提交：\n\n", outContent.toString());
    }

    @Test
    public void should_print_error_msg_when_input_incorrect_msg() throws Exception {
        //Given
        AddStuCommand command = new AddStuCommand(DISPLAY_ADD_STUDENT_PAGE);
        CmdIOTransformer cmdIOTransformer = mock(CmdIOTransformer.class);
        GradeCommandAdapterService gradeCommandAdapterService = mock(GradeCommandAdapterService.class);

        RouteController controller = mock(RouteController.class);
        ErrorCommand errorCommand = mock(ErrorCommand.class);
        when(controller.generateCommand(COMMON_ERROR_PAGE)).thenReturn(errorCommand);
        doNothing().when(errorCommand).initDisplay(anyString(), eq(command));

        command.setRouter(controller);
        command.setTransformer(cmdIOTransformer);
        command.setGradeCommandAdapterService(gradeCommandAdapterService);

        String studentInfoStr = "error student info";
        when(cmdIOTransformer.formatStudent(studentInfoStr)).thenReturn(null);
        doNothing().when(gradeCommandAdapterService).addStudent(any());

        System.setIn(new ByteArrayInputStream(studentInfoStr.getBytes()));
        Scanner scanner = new Scanner(System.in);
        //When

        command.cliReader = scanner;
        command.initDisplay();
        //Then
        String errorContent = "请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：";
        verify(errorCommand, times(1)).initDisplay(eq(errorContent), eq(command));

    }

}
