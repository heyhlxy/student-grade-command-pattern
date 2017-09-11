package com.tw.commandend.command;

/**
 * Created by jxzhong on 2017/7/27.
 */
public class ExitCommand extends Command {
    private static final String EXIT_CONTENT = "欢迎使用学生成绩管理系统，已经退出!";

    public ExitCommand(String order) {
        super(order, EXIT_CONTENT);
    }

    @Override
    public void initDisplay() {
        setOutPut(this.getContent());
        cliReader.close();

    }
}
