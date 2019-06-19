package com.tw.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    
	public static List<String> readRecord() throws IOException{
		ArrayList<String> records = new ArrayList<>();
		
		FileReader reader = new FileReader("src/main/resources/students.txt");
        BufferedReader br = new BufferedReader(reader);
        String str;
        while ((str = br.readLine()) != null) {
            records.add(str);
        }
        
        br.close();
        reader.close();
		return records;
	}
	
	public static boolean writeRecord(String record) {
        try {
            FileWriter writer = new FileWriter("src/main/resources/students.txt", true);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(record);
            bw.newLine();
            bw.flush();
            bw.close();
            writer.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}