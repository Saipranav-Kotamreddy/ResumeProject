package resume.bean;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
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
	private InputStream urlResume;
	private InputStream urlPicture;
	
	public ResumeBean(String name, String phoneNumber, String emailAddress, String resumeLink, String skillList, String pictureLink){
		this.name=name;
		this.phoneNumber=phoneNumber;
		this.emailAddress=emailAddress;
		this.skillList=skillList;
		try {
			this.setUrlResume(new URL(resumeLink).openStream());
			this.setUrlPicture(new URL(pictureLink).openStream());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public ResumeBean() {
		// TODO Auto-generated constructor stub
    	this.name=null;
		this.phoneNumber=null;
		this.emailAddress=null;
		this.skillList=null;
		this.resumeFile=null;
		this.picture=null;
	}

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

	public InputStream getUrlResume() {
		return urlResume;
	}

	public void setUrlResume(InputStream urlResume) {
		this.urlResume = urlResume;
	}

	public InputStream getUrlPicture() {
		return urlPicture;
	}

	public void setUrlPicture(InputStream urlPicture) {
		this.urlPicture = urlPicture;
	}
}
