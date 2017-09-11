package com.tw.commandend.command;

import com.tw.commandend.controller.RouteController;
import com.tw.core.model.Student;

import static com.tw.commandend.controller.RouteController.COMMON_ERROR_PAGE;
import static com.tw.commandend.controller.RouteController.DISPLAY_MAIN_MENU_PAGE;

/**
 * Created by jxzhong on 2017/7/27.
 */
public class AddStuCommand extends Command {
    private static final String ADD_STU_CONTENT = "请输入学生信息（格式：姓名, 学号，数学：分数，语文：分数，英语：分数，编程：分数），按回车提交：";
    private static final String ERROR_CONTENT = "请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：";

    public AddStuCommand(String order) {
        super(order, ADD_STU_CONTENT);
    }

    @Override
    public void initDisplay() {
        setOutPut(this.getContent());
        String input = cliReader.nextLine();
        handleInput(input);
    }

    @Override
    public void handleInput(String input) {
        Student stu = getTransformer().formatStudent(input);
        if (stu == null) {
            getRouteController().generateCommand(COMMON_ERROR_PAGE).initDisplay(ERROR_CONTENT, this);
        } else {
            getGradeCommandAdapterService().addStudent(stu);
            getRouteController().generateCommand(DISPLAY_MAIN_MENU_PAGE).initDisplay();
        }
    }
}
