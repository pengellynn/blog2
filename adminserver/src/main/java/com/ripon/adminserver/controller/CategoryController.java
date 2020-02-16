package com.ripon.adminserver.controller;

import com.ripon.adminserver.dto.Response;
import com.ripon.adminserver.model.Category;
import com.ripon.adminserver.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @PostMapping("/category")
    public Response addCategory(@RequestBody Category category) {
        if (categoryService.addCategory(category) == 1){
            return Response.success();
        }
        return Response.fail("添加栏目失败");
    }

    @DeleteMapping("/category/{id}")
    public Response deleteCategory(@PathVariable("id") Integer id) {
        if (categoryService.deleteCategory(id) == 1) {
            return Response.success();
        }
        return Response.fail("删除栏目失败");

    }

    @PutMapping("/category/{id}")
    public Response updateCategory(@RequestBody Category category) {
        if (categoryService.updateCategory(category) == 1) {
            return Response.success();
        }
        return Response.fail("更新栏目失败");

    }

    @DeleteMapping("/categories")
    public Response batchDeleteCategory(@RequestBody List<Integer> list) {
        if (categoryService.batchDeleteCategory(list) == list.size()) {
            return Response.success();
        }
        return Response.fail("批量删除栏目失败");

    }
}
