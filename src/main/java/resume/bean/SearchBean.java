package resume.bean;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;

public class SearchBean {
	Comparator<ResumeSort> matchCompare= new Comparator<ResumeSort>(){
		public int compare(ResumeSort resume1, ResumeSort resume2) {
			return resume2.matches-resume1.matches;
		}
	};
	private String[] skillCriteria= {"", "", "", "", ""};
	private int criteriaNumber;
	private int matches=0;
	private Vector<String> validResumes = new Vector<String>();
	private Vector<Integer> validResumeID = new Vector<Integer>();
	private PriorityQueue<ResumeSort> validResumeList = new PriorityQueue<ResumeSort>(matchCompare);
	private int resumeNumber=0;
	
	
	public String[] getSkillCriteria() {
		return skillCriteria;
	}
	public void setSkillCriteria(String skillCriteria) {
		String[] skills = skillCriteria.split(",");
		int num=0;
		for(String skill : skills) {
				this.skillCriteria[num]=skill;
				num++;
				if(num>=5) {
					break;
				}
		}
		this.criteriaNumber=num;
	}
	
	public Vector<String> getValidResumes() {
		return validResumes;
	}
	
	public Vector<Integer> getValidResumeID() {
		int values=0;
		while(values<51 && validResumeList.isEmpty()==false) {
			System.out.println(validResumeList.peek().id + " || " + validResumeList.peek().matches);
			validResumeID.add(validResumeList.peek().id);
			validResumes.add(validResumeList.peek().skillList);
			validResumeList.remove();
		}
		return validResumeID;
	}
	
	public void addValidResumes(String resume) {
		String[] results = resume.split("&,");
		ResumeSort resumeHeap= new ResumeSort(Integer.valueOf(results[0]), matches, results[1]);
		validResumeList.add(resumeHeap);
		resumeNumber++;
		matches=0;
	}
	
	public int getResumeReturned() {
		return resumeNumber;
	}
	
	public boolean findCriteria(String skillList) {
		boolean doesSkillExist=false;
		String[] skills = skillList.split(",");
		for(String skill : skills) {
			for(int i=0; i<criteriaNumber; i++) {
				if(skill.equals(skillCriteria[i])) {
					doesSkillExist=true;
					System.out.println("Compare: " + skill + " to " + this.skillCriteria[i]);
					matches++;
				}
			}
		}
		return doesSkillExist;
	}
	
}
