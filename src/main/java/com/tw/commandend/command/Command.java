package com.tw.commandend.command;

import com.tw.commandend.controller.RouteController;
import com.tw.commandend.transform.CmdIOTransformer;

import java.util.Scanner;

/**
 * Created by jxzhong on 2017/7/27.
 */
public class Command {

    public static Scanner cliReader = new Scanner(System.in);

    private RouteController routeController;
    private String content;
    private String state;
    private GradeCommandAdapterService gradeCommandAdapterService;
    private CmdIOTransformer transformer;

    public RouteController getRouteController() {
        return routeController;
    }

    public void setRouter(RouteController routeController) {
        this.routeController = routeController;
    }

    public CmdIOTransformer getTransformer() {
        return transformer;
    }

    public void setTransformer(CmdIOTransformer transformer) {
        this.transformer = transformer;
    }


    public Command(String order) {
        this(order, "");
    }

    public Command(String state, String content) {
        this.content = content;
        this.state = state;
    }

    protected GradeCommandAdapterService getGradeCommandAdapterService() {
        return gradeCommandAdapterService;
    }

    public void setGradeCommandAdapterService(GradeCommandAdapterService gradeCommandAdapterService) {
        this.gradeCommandAdapterService = gradeCommandAdapterService;
    }

    public String getState() {
        return state;
    }

    public String getContent() {
        return content;
    }


    public void initDisplay() {
    }

    public void initDisplay(String displayMsg, Command callBackCmd) {
    }

    public void handleInput(String input) {

    }

    protected void setOutPut(String msg) {
        System.out.println(msg);
        System.out.println();
    }
}
