package cn.itcast.mapper;

import cn.itcast.domain.Category;

import java.util.List;

public interface CategoryMapper {
    List<Category> findAllCategory();
}
