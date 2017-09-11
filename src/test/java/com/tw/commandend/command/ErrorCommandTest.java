package com.tw.commandend.command;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static com.tw.commandend.controller.RouteController.COMMON_ERROR_PAGE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by jxzhong on 2017/9/11.
 */
public class ErrorCommandTest extends CommandTest {

    @Test
    public void should_print_error_msg_and_execute_passed_command() throws Exception {
        //Given
        ErrorCommand command = new ErrorCommand(COMMON_ERROR_PAGE);

        String errorMsg = "请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：";
        String numbers = "122, 112";
        GenGradereportCommand genReportCommand = mock(GenGradereportCommand.class);
        doNothing().when(genReportCommand).handleInput(numbers);


        System.setIn(new ByteArrayInputStream(numbers.getBytes()));
        Scanner scanner = new Scanner(System.in);
        //When

        command.cliReader = scanner;
        command.initDisplay(errorMsg, genReportCommand);
        //Then
        assertEquals(errorMsg + "\n\n", outContent.toString());
        verify(genReportCommand, times(1)).handleInput(numbers);
    }
}