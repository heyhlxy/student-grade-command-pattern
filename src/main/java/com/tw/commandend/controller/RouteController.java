package com.tw.commandend.controller;

import com.tw.commandend.command.*;
import com.tw.commandend.transform.CmdIOTransformer;
import com.tw.core.GradeReportBuilder;
import com.tw.core.respository.StudentRepository;
import com.tw.service.ReportService;
import com.tw.service.StudentService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by jxzhong on 2017/7/27.
 */
public class RouteController {

    private final static GradeCommandAdapterService GRADE_COMMAND_ADAPTER_SERVICE = getService();
    public final static String DISPLAY_MAIN_MENU_PAGE = "0";
    public final static String DISPLAY_ADD_STUDENT_PAGE = "1";
    public final static String DISPLAY_GRADE_REPORT_PAGE = "2";
    public final static String EXIT_SYSTEM = "3";
    public final static String COMMON_ERROR_PAGE = "ERROR_COMMON";
    private List<Command> commands;

    public RouteController() {
        commands = Arrays.asList(
                new MainMenuCommand(DISPLAY_MAIN_MENU_PAGE),
                new AddStuCommand(DISPLAY_ADD_STUDENT_PAGE),
                new GenGradereportCommand(DISPLAY_GRADE_REPORT_PAGE),
                new ExitCommand(EXIT_SYSTEM),
                new ErrorCommand(COMMON_ERROR_PAGE));
    }

    public Command generateCommand(String order) {
        Command command;
        Optional<Command> commandWrapper = searchCmd(order);
        if (commandWrapper.isPresent()) {
            command = commandWrapper.get();
        } else {
            command = searchCmd(DISPLAY_MAIN_MENU_PAGE).get();
        }
        command.setGradeCommandAdapterService(this.GRADE_COMMAND_ADAPTER_SERVICE);
        command.setRouter(this);
        command.setTransformer(new CmdIOTransformer());

        return command;
    }

    private Optional<Command> searchCmd(String order) {
        return this.commands.stream().filter(cmd -> cmd.getState().equals(order)).findFirst();
    }

    private static GradeCommandAdapterService getService() {

        StudentRepository studentRepository = new StudentRepository();
        GradeReportBuilder gradeReportBuilder = new GradeReportBuilder();
        StudentService studentService = new StudentService(studentRepository, gradeReportBuilder);
        ReportService reportService = new ReportService(studentService, gradeReportBuilder);
        return new GradeCommandAdapterService(studentService, reportService);
    }
}
