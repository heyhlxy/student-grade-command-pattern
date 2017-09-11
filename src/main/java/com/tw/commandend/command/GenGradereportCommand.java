package com.tw.commandend.command;

import com.tw.core.model.Gradereport;
import com.tw.core.model.Student;

import java.util.List;

import static com.tw.commandend.controller.RouteController.COMMON_ERROR_PAGE;
import static com.tw.commandend.controller.RouteController.DISPLAY_MAIN_MENU_PAGE;

/**
 * Created by jxzhong on 2017/7/27.
 */
public class GenGradereportCommand extends Command {
    private static final String GEN_GRADE_REPORT_CONTENT = "请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：";
    private static final String ERROR_CONTENT = "请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：";


    public GenGradereportCommand(String order) {
        super(order, GEN_GRADE_REPORT_CONTENT);
    }

    @Override
    public void initDisplay() {
        setOutPut(this.getContent());
        String input = cliReader.nextLine();
        handleInput(input);

    }

    @Override
    public void handleInput(String input) {
        List<Student> stus = getTransformer().formatStudentNos(input);
        Gradereport gradereport = getGradeCommandAdapterService().generateReport(stus);
        if (stus.isEmpty() || gradereport == null) {
            getRouteController().generateCommand(COMMON_ERROR_PAGE).initDisplay(ERROR_CONTENT, this);
        } else {
            setOutPut(getTransformer().formatReportText(gradereport));
            getRouteController().generateCommand(DISPLAY_MAIN_MENU_PAGE).initDisplay();
        }

    }
}
