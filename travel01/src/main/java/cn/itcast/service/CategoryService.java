package cn.itcast.service;

import cn.itcast.domain.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 查询所有分类
     * @return
     */
    List<Category> findAllcategory() throws Exception;

}
