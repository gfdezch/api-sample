package com.gfc.api.v1.infrastructure.adapter.out.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.gfc.api.v1.infrastructure.adapter.out.db.model.CategoryDo;

@Mapper
public interface CategoryMapper {

    @Select("SELECT TOP (1000) [id], [name], [description], [created], [created_by] FROM [category];")
        @Results({        
        @Result(property = "createdBy", column = "created_by")
    })
    List<CategoryDo> findAll();

    @Select("SELECT [id], [name], [description], [created], [created_by] FROM [category] WHERE [id] = #{id};")    
        @Results({        
        @Result(property = "createdBy", column = "created_by")
    })
    CategoryDo findById(int id);

    @Select("SELECT CASE COUNT([id]) WHEN 1 THEN 'true' ELSE 'false' END AS ex FROM [category] WHERE id = #{id}")
    boolean existsById(int id);
}
