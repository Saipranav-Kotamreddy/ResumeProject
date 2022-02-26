package resume.bean;

import java.util.Vector;

import javax.servlet.http.Part;

public class ResumeBean {
	private String name;
	private String phoneNumber;
	private String emailAddress;
	private int skillNumber=0;
	private String skillList;
	private Vector<String> skills = new Vector<String>(5);
	private Part resumeFile;
	private Part picture;
	
    public String getName() {
    	return name;
    }
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
    	return phoneNumber;
    }
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailAddress() {
    	return emailAddress;
    }
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getSkill(int index) {
    	return skills.elementAt(index);
    }
	public void addSkills(String newSkill) {
		this.skills.add(newSkill);
		this.skillNumber++;
	}
	public void deleteSkill(String skill) {
		for(int i=0; i<skills.size(); i++) {
			if(skills.elementAt(i)==skill) {
				skills.remove(i);
				break;
			}
		}
		this.skillNumber--;
	}
	public void setSkillList(String skillList) {
		this.skillList=skillList;
		String[] skillArray = skillList.split(",");
		for(String skill : skillArray) {
			this.addSkills(skill);
		}
	}
	public String getSkillListString() {
		return skillList;
	}
	public Vector<String> getSkillListVector(){
		return skills;
	}
	public Part getResumePdf() {
    	return resumeFile;
    }
	public void setResumePdf(Part link) {
		this.resumeFile = link;
	}
	public Part getPicture() {
    	return picture;
    }
	public void setPicture(Part img) {
		this.picture = img;
	}
	public int getSkillNumber() {
		return skillNumber;
	}
}
