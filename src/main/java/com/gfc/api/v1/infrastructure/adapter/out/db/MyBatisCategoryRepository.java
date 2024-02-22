package com.gfc.api.v1.infrastructure.adapter.out.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gfc.api.v1.domain.model.Category;
import com.gfc.api.v1.domain.port.CategoryRepository;
import com.gfc.api.v1.infrastructure.adapter.out.db.mapper.CategoryMapper;
import com.gfc.api.v1.infrastructure.adapter.out.db.model.CategoryDo;

@Repository
public class MyBatisCategoryRepository implements CategoryRepository {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findAll() {
        List<Category> res = null;

        List<CategoryDo> categories = categoryMapper.findAll();
        
        if (null != categories && !categories.isEmpty()) {
            res = CategoryDo.toBo(categories);
        }

        return res;
    }

    @Override
    public Category findById(int id) {
        Category res = null;

        CategoryDo category = categoryMapper.findById(id);
        
        if (null != category) {
            res = CategoryDo.toBo(category);
        }

        return res;
    }

    @Override
    public boolean existsById(int id) {
        return categoryMapper.existsById(id);
    }
}
