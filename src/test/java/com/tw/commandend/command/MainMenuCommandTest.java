package com.tw.commandend.command;

import com.tw.commandend.controller.RouteController;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static com.tw.commandend.controller.RouteController.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by jxzhong on 2017/7/31.
 */
public class MainMenuCommandTest extends CommandTest {

    @Test
    public void should_get_menu_and_exit_text_when_execute_menu_command_and_input_3() throws Exception {
        //Given
        MainMenuCommand command = new MainMenuCommand(DISPLAY_MAIN_MENU_PAGE);
        RouteController controller = new RouteController();

        System.setIn(new ByteArrayInputStream(EXIT_SYSTEM.getBytes()));
        Scanner scanner = new Scanner(System.in);
        //When
        command.setRouter(controller);
        command.cliReader = scanner;
        command.initDisplay();
        //Then
        assertEquals("***********\n" +
                "1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3):\n" +
                "***********\n" +
                "\n\n" +
                "欢迎使用学生成绩管理系统，已经退出!" +
                "\n\n", outContent.toString());
    }


    @Test
    public void should_redirect_to_one_of_menu_item_display_when_input_addstudent_code_in_main_menu_dispkay() throws Exception {
        //Given
        RouteController controller = mock(RouteController.class);
        AddStuCommand addCommand = mock(AddStuCommand.class);

        when(controller.generateCommand(DISPLAY_ADD_STUDENT_PAGE)).thenReturn(addCommand);
        doNothing().when(addCommand).initDisplay();

        MainMenuCommand command = new MainMenuCommand(DISPLAY_MAIN_MENU_PAGE);

        System.setIn(new ByteArrayInputStream(DISPLAY_ADD_STUDENT_PAGE.getBytes()));
        Scanner scanner = new Scanner(System.in);
        //When
        command.setRouter(controller);
        command.cliReader = scanner;
        command.initDisplay();
        //Then
        verify(controller, times(1)).generateCommand(DISPLAY_ADD_STUDENT_PAGE);
    }

}