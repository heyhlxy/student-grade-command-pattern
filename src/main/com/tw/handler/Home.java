package com.tw.handler;

import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

import com.tw.model.Student;
import com.tw.service.StudentService;
import com.tw.util.TextFormat;

public class Home {
	
	private static final String INTRO_TEXT = "1. 添加学生\n"
			+ "2. 生成成绩单\n"
			+ "3. 退出\n"
			+ "输入你的选择（1～3）：";
	
	private static final String ADD_STUDENT_TEXT = "请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：";
	private static final String FAIL_ADD_STUDENT_TEXT = "请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：" ;
	private static final String REPROT_STUDENT_GRADES_TEXT = "请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：";
	private static final String FAIL_REPROT_STUDENT_GRADES_TEXT = "请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：";
	
	private static Scanner input = new Scanner(System.in);
	private static TextFormat formatter = new TextFormat();
	private static StudentService studentService = new StudentService();
	
	public void init() throws Exception{
		System.out.println(INTRO_TEXT);
    	String readInput = input.nextLine();
    	chooseFunction(readInput);
    }
	
	public void chooseFunction(String inputChoice) throws Exception{
		switch (inputChoice) {
		    case "1":
			    addStudent();
			    init();
			    break;
		    case "2":
		        buildGradeReport();
		        init();
		        break;
		    case "3":
		    	init();
		    	break;
		    default:
			    init();
		}
	}
	
	public void addStudent() throws Exception{
		System.out.println(ADD_STUDENT_TEXT);
		
		Student student = formatter.formatStudent(input.nextLine());
		if(student != null){
			studentService.addStudentRecord(student);
			System.out.println("学生"+student.getName()+"的成绩被添加");
		} else {
			System.out.println(FAIL_ADD_STUDENT_TEXT);
			addStudent();
		}
	}
	
	public void buildGradeReport() throws Exception{
		System.out.println(REPROT_STUDENT_GRADES_TEXT);

		List<Student> studentList = studentService.getByID(Arrays.asList(input.nextLine().split("，")));
		if(studentList.size() > 0){
			System.out.println(formatter.formatReport(studentList));
		} else {
			System.out.println(FAIL_REPROT_STUDENT_GRADES_TEXT);
			buildGradeReport();
		}
	}
}
