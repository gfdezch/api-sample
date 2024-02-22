package com.gfc.api.v1.infrastructure.adapter.out.db.model;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.gfc.api.v1.domain.model.Category;

public class CategoryDo {

    private int id;
    private String name;
    private String description;
    private Date created;
    private String createdBy;
    
    public static List<Category> toBo(List<CategoryDo> categories) {
        List<Category> res = null;

        if (null != categories && !categories.isEmpty()) {
            res = categories.stream().map(CategoryDo::toBo).collect(Collectors.toList());
        }

        return res;
    }

    public static Category toBo(CategoryDo category) {
        Category res = null;

        if (null != category) {           
            res = new Category(category.getId(), category.getName(), category.getDescription());
        }

        return res;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
