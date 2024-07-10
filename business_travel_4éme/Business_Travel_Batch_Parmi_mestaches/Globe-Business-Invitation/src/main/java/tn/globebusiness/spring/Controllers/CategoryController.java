package tn.globebusiness.spring.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.globebusiness.spring.Entities.Category;
import tn.globebusiness.spring.Services.ICategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	ICategoryService categoryService;
	
	@PostMapping("AddCategory")
	public void addCategory(@RequestBody Category category){
		categoryService.addCategory(category);
	}
	
	@DeleteMapping("deleteCategory/{categoryId}")
	public boolean deleteCategory(@PathVariable("categoryId") Integer categoryId) {
		categoryService.deleteCategory(categoryId);
		return true;
	}
	
	@GetMapping("ListAllCategories")
	public List<Category> listAllCategories(){
		return categoryService.listAllCategories();
	}
	
	@GetMapping("ListCategory/{categoryName}")
	public Category listCategory(@PathVariable("categoryName") String categoryName) {
		return categoryService.listCategory(categoryName);
	}
}
