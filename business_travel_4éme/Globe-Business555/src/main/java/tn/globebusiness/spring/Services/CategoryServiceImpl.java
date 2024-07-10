package tn.globebusiness.spring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.globebusiness.spring.Entities.Category;
import tn.globebusiness.spring.Repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Category addCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public void deleteCategory(int categoryId) {
		categoryRepository.delete(categoryRepository.findById(categoryId).get());
	}

	@Override
	public List<Category> listAllCategories() {
		return (List<Category>) categoryRepository.findAllCategories();
	}

	@Override
	public Category listCategory(String categoryName) {
		return categoryRepository.findByName(categoryName);
	}

	@Override
	public int categoryStat(int categoryId) {
		System.out.println(categoryRepository.categoryStats(categoryId));
		return categoryRepository.categoryStats(categoryId)*100/categoryRepository.categoriesNumber();
	}

}
