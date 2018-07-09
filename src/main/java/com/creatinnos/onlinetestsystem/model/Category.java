package com.creatinnos.onlinetestsystem.model;

public class Category extends Token{

	private String categoryId;
	private String categoryName;
	private String organizationId;
/*
	private ArrayList<SubCategory> subCategory;
*/
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	
/*
	public ArrayList<SubCategory> getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(ArrayList<SubCategory> subCategory) {
		this.subCategory = subCategory;
	}

	public int existSubcategory(String subCategoryName) {
		int index = -1;
		if (getSubCategory() != null) {
			for (int i = 0; i < getSubCategory().size(); i++) {
				if (getSubCategory().get(i) != null) {
					if (getSubCategory().get(i).getSubCategoryName() != null) {
						if (getSubCategory().get(i).getSubCategoryName().equals(subCategoryName)) {
							index = i;
							break;
						}

					}
				}

			}
		}
		return index;
	}*/
}
