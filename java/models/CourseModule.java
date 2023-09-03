package models;

import java.util.List;

public class CourseModule {
    private int moduleID;
    private int courseID;
    private String moduleTitle;

    
    private List<CourseModuleMaterial> courseModuleMaterialList;
    public CourseModule() {
        // Default constructor
    }
  
    public CourseModule(int moduleID, int courseID, String moduleTitle) {
        this.moduleID = moduleID;
        this.courseID = courseID;
        this.moduleTitle = moduleTitle;
    }

    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    @Override
    public String toString() {
    	
//    	List<CourseModuleMaterial> allCM = this.getCourseModuleMaterialList();
//    	
//    	for(CourseModuleMaterial cm : allCM) {
//    		System.out.println("cm: " + cm.toString());
//    	}
    	
        return "CourseModule{" +
                "moduleID=" + moduleID +
                ", courseID=" + courseID +
                ", moduleTitle='" + moduleTitle + '\'' +
                '}';
    }

	public List<CourseModuleMaterial> getCourseModuleMaterialList() {
		return courseModuleMaterialList;
	}

	

	public void setCourseModuleMaterialList(List<CourseModuleMaterial> allMaterials) {
		// TODO Auto-generated method stub
		this.courseModuleMaterialList = allMaterials;
	}

	
}
