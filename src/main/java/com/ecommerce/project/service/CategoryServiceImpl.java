package com.ecommerce.project.service;

import com.ecommerce.project.exception.APIException;
import com.ecommerce.project.exception.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

//    private List<Category> categories = new ArrayList<>();
//    private Long nextId = 1L;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty())
            throw new APIException("No categories added.");

        return categories;
    }

    @Override
    public void createCategory(Category category) {
//        category.setCategoryId(nextId++);
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory != null)
            throw new APIException("Category with category name " + category.getCategoryName() + " already exists !");
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {

        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
//        Category category =optionalCategory.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));
        Category category =optionalCategory.orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));

         categoryRepository.delete(category);
         return "Category by id :" + categoryId + " deleted successfully";
//        List<Category> categories = categoryRepository.findAll();
//
//        Category category = categories.stream()
//                .filter(c -> c.getCategoryId().equals(categoryId)).findFirst()
////                        .orElse(null);
//                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));
//
////        if (category == null) {
////            return "category not found";
////        }
//        categoryRepository.delete(category);
//        return "Category with categoryId " + categoryId + " deleted successfully.";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {

        Category savedCategory = categoryRepository.findById(categoryId)
//                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));
                .orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));

        savedCategory.setCategoryName(category.getCategoryName());


        return categoryRepository.save(savedCategory);
//        List<Category> categories = categoryRepository.findAll();
//
//        Optional<Category> optionalCategory = categories.stream()
//                .filter(c -> c.getCategoryId().equals(categoryId))
//                .findFirst();
//
//        if (optionalCategory.isPresent()) {
//            Category existingCategory = optionalCategory.get();
//            existingCategory.setCategoryName(category.getCategoryName());
//            Category savedCategory = categoryRepository.save(existingCategory);
//            return existingCategory;
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
//        }

//
    }
}
