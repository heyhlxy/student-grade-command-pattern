package com.tw.commandend.command;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static com.tw.commandend.controller.RouteController.EXIT_SYSTEM;
import static org.junit.Assert.assertEquals;

/**
 * Created by jxzhong on 2017/9/8.
 */
public class ExitCommandTest extends CommandTest {

    @Test
    public void should_exit_system_when_call_input() throws Exception {
        //Given
        ExitCommand command = new ExitCommand(EXIT_SYSTEM);

        System.setIn(new ByteArrayInputStream(EXIT_SYSTEM.getBytes()));
        Scanner scanner = new Scanner(System.in);
        //When
        command.cliReader = scanner;
        command.initDisplay();
        //Then
        assertEquals("欢迎使用学生成绩管理系统，已经退出!\n\n", outContent.toString());
    }
}