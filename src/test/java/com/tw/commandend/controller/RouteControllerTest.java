package com.tw.commandend.controller;

import com.tw.commandend.command.Command;
import org.junit.Test;

import static com.tw.commandend.controller.RouteController.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by jxzhong on 2017/7/27.
 */
public class RouteControllerTest {
    @Test
    public void should_get_main_menu_command_when_first_start_with_order_0() throws Exception {
        //Given
        RouteController routeController = new RouteController();
        //When
        Command command = routeController.generateCommand(DISPLAY_MAIN_MENU_PAGE);
        //Then
        assertEquals(command.getState(), DISPLAY_MAIN_MENU_PAGE);
        assertEquals(command.getContent(), "***********\n" +
                "1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3):\n" +
                "***********\n");
    }

    @Test
    public void should_get_add_stu_content_command_when_first_start_with_order_1() throws Exception {
        //Given
        RouteController routeController = new RouteController();
        //When
        Command command = routeController.generateCommand(DISPLAY_ADD_STUDENT_PAGE);
        //Then
        assertEquals(command.getState(), DISPLAY_ADD_STUDENT_PAGE);
        assertEquals(command.getContent(), "请输入学生信息（格式：姓名, 学号，数学：分数，语文：分数，英语：分数，编程：分数），按回车提交：");
    }


    @Test
    public void should_get_grade_report_content_command_when_input_order_2() throws Exception {
        //Given
        RouteController routeController = new RouteController();
        //When
        Command command = routeController.generateCommand(DISPLAY_GRADE_REPORT_PAGE);
        //Then
        assertEquals(command.getState(), DISPLAY_GRADE_REPORT_PAGE);
        assertEquals(command.getContent(), "请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
    }

    @Test
    public void should_exit_app_content_command_when_input_order_3() throws Exception {
        //Given
        RouteController routeController = new RouteController();
        //When
        Command command = routeController.generateCommand(EXIT_SYSTEM);
        //Then
        assertEquals(command.getState(), EXIT_SYSTEM);
        assertEquals(command.getContent(), "欢迎使用学生成绩管理系统，已经退出!");
    }

    @Test
    public void should_get_empty_content_command_when_input_order_4() throws Exception {
        //Given
        RouteController routeController = new RouteController();
        //When
        Command command = routeController.generateCommand(COMMON_ERROR_PAGE);
        //Then
        assertEquals(command.getState(), COMMON_ERROR_PAGE);
        assertEquals(command.getContent(), "");
    }


    @Test
    public void should_get_main_menu_command_when_input_order_others() throws Exception {
        //Given
        RouteController routeController = new RouteController();
        //When
        Command command = routeController.generateCommand("other unknown order");
        //Then
        assertEquals(command.getState(), DISPLAY_MAIN_MENU_PAGE);
    }
}


