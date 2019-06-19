package com.tw.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.tw.model.Student;
import com.tw.util.FileUtil;
import com.tw.util.TextFormat;

public class StudentService {
    public void addStudentRecord(Student student){
    	FileUtil.writeRecord(student.toString());
    }
    
    public List<Student> getByID(List<String> IDs) throws IOException{
    	List<Student> students = new ArrayList<Student>();
    	List<String> records = FileUtil.readRecord();
    	TextFormat formatter = new TextFormat();
    	for (int i = 0; i < IDs.size(); i++) {
			for (int j = 0; j < records.size(); j++) {
				if(IDs.get(i).equals(records.get(j).split("，")[1])){
					students.add(formatter.formatStudent(records.get(j)));
				}
			}
		}
    	return students;
    }
}