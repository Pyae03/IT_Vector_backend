package models;

public class Category {

	
	private String categoryName; // this also id

	public Category() {
		
	}
	public Category(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
