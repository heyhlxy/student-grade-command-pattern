package com.tw.model;

public class Student {
    
	private String studentID;
	private String name;
//	private Map<String, Double> scoreList = new HashMap();
	private double mathScore;
	private double chineseScore;
	private double englishScore;
	private double programmingScore;

	public Student(String name, String studentID, double mathScore,
			double chineseScore, double englishScore, double programmingScore) {
		this.name = name;
		this.studentID = studentID;
		this.mathScore = mathScore;
		this.chineseScore = chineseScore;
		this.englishScore = englishScore;
		this.programmingScore = programmingScore;
	}
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public double getMathScore() {
		return mathScore;
	}

	public void setMathScore(double mathScore) {
		this.mathScore = mathScore;
	}

	public double getChineseScore() {
		return chineseScore;
	}

	public void setChineseScore(double chineseScore) {
		this.chineseScore = chineseScore;
	}

	public double getEnglishScore() {
		return englishScore;
	}

	public void setEnglishScore(double englishScore) {
		this.englishScore = englishScore;
	}

	public double getProgrammingScore() {
		return programmingScore;
	}

	public void setProgrammingScore(double programmingScore) {
		this.programmingScore = programmingScore;
	}

	public double getTotalScore() {
        double totalScore = chineseScore + mathScore + englishScore + programmingScore;
        return totalScore;
    }

    public double getAverageScore() {
        return getTotalScore() / 4;
    }

    @Override
    public String toString() {
        return String.format("%s，%s，数学：%.1f，语文：%.1f，英语：%.1f，编程：%.1f",
                name, studentID, mathScore, chineseScore, englishScore, programmingScore);
    }
    
//	public Map<String, Double> getScoreList() {
//		return scoreList;
//	}
//
//	public void setScoreList(Map<String, Double> scoreList) {
//		this.scoreList = scoreList;
//	}
    
//	public double getTotalScore(){
//		int totalScore = 0;
//		for (int i = 0; i < scoreList.size(); i++) {
//			totalScore += scoreList.get(scoreList.keySet().toArray()[i]);
//		}
//		return totalScore;
//	}
//    
//	public double getAverageScore(){
//		return getTotalScore() / scoreList.size() * 1.0;
//	}
}
