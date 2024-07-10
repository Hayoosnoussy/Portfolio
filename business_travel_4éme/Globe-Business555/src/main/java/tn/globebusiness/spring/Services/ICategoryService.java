package tn.globebusiness.spring.Services;

import java.util.List;

import tn.globebusiness.spring.Entities.Category;

public interface ICategoryService {

	public Category addCategory(Category category);

	public void deleteCategory(int categoryId);

	public List<Category> listAllCategories();

	public Category listCategory(String categoryName);

	public int categoryStat(int categoryId);

}
