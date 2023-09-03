package models;

public class CourseModuleMaterial {
    private int materialID;
    private int moduleID;
    private String materialName;
    private String materialDescription;
    private String filePath;

    public CourseModuleMaterial() {
        // Default constructor
    }
    
    public CourseModuleMaterial(int moduleID, String materialName, String materialDescription, String filePath) {
        this.moduleID = moduleID;
        this.materialName = materialName;
        this.materialDescription = materialDescription;
        this.filePath = filePath;
    }

    public CourseModuleMaterial(int materialID, int moduleID, String materialName, String materialDescription, String filePath) {
    	this.materialID = materialID;
    	this.moduleID = moduleID;
    	this.materialName = materialName;
    	this.materialDescription = materialDescription;
    	this.filePath = filePath;
    }
    // Getter and Setter methods for all fields

    public int getMaterialID() {
        return materialID;
    }

    public void setMaterialID(int materialID) {
        this.materialID = materialID;
    }

    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "CourseModuleMaterial{" +
                "materialID=" + materialID +
                ", moduleID=" + moduleID +
                ", materialName='" + materialName + '\'' +
                ", materialDescription='" + materialDescription + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}

