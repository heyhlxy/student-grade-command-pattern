package com.tw.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tw.model.Student;

public class TextFormat {
    
	private static final String STU_REGREX ="(.*)，(.*)，数学：(\\d+(\\.\\d+)?)，语文：(\\d+(\\.\\d+)?)，英语：(\\d+(\\.\\d+)?)，编程：(\\d+(\\.\\d+)?)";
    private static final String reportText = "成绩单\n"
    		+ "姓名|数学|语文|英语|编程|平均分|总分\n"
    		+ "========================\n"
    		+ "%s"
    		+ "========================\n"
    		+ "全班总分平均数：%s\n"
    		+ "全班总分中位数：%s\n\n";
    
    public Student formatStudent(String input){
    	Student student = null;
    	Matcher matcher = Pattern.compile(STU_REGREX).matcher(input);
    	if(matcher.matches()){
    		student = new Student(matcher.group(1), matcher.group(2), 
    				Double.parseDouble(matcher.group(3)), 
    				Double.parseDouble(matcher.group(5)), 
    				Double.parseDouble(matcher.group(7)), 
    				Double.parseDouble(matcher.group(9)));
    		
    	}
    	return student;
    }
    
    public String formatReport(List<Student> studentList){
    	int studentNums = studentList.size();
    	String studentGrade = "";
    	double classTotalScore = 0.0;
    	double classMedianTotalScore;
    	List<Double> totalScoreList = new ArrayList<Double>();
    	
    	for (int i = 0; i < studentNums; i++) {
    		Student student = studentList.get(i);
			studentGrade += String.format("%s|%.1f|%.1f|%.1f|%.1f|%.1f|%.1f\n", 
					student.getName(),
					student.getMathScore(),
					student.getChineseScore(),
					student.getEnglishScore(),
					student.getProgrammingScore(),
					student.getAverageScore(),
					student.getTotalScore());
			totalScoreList.add(student.getTotalScore());
			classTotalScore += student.getTotalScore();
		}
    	
    	//全班总分中位数
    	Collections.sort(totalScoreList);
    	if(studentNums % 2 ==0){
    		classMedianTotalScore = (totalScoreList.get(studentNums/2) + totalScoreList.get(studentNums/2 - 1)) / 2.0;
    	} else {
    		classMedianTotalScore = totalScoreList.get(studentNums/2);
    	}
    	
    	return String.format(reportText, studentGrade, classTotalScore / studentNums*1.0, classMedianTotalScore);
    }
  
}
