package com.tw.core.model;

/**
 * Created by jxzhong on 2017/7/26.
 */
public class Student {

    private String name;
    private String number;
    private int mathsScore;
    private int chineseScore;
    private int englishScore;
    private int programScore;

    public Student(Student student) {
        this(student.getName(),
                student.getNumber(),
                student.getMathsScore(),
                student.getChineseScore(),
                student.getEnglishScore(),
                student.getProgramScore());
    }

    public Student(String name, String number, int mathsScore, int chineseScore, int englishScore, int programScore) {

        this.name = name;
        this.number = number;
        this.mathsScore = mathsScore;
        this.chineseScore = chineseScore;
        this.englishScore = englishScore;
        this.programScore = programScore;
    }

    public Student(String num) {
        this.number = num;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public int getMathsScore() {
        return mathsScore;
    }

    public int getChineseScore() {
        return chineseScore;
    }

    public int getEnglishScore() {
        return englishScore;
    }

    public int getProgramScore() {
        return programScore;
    }

}
