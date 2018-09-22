package cn.itcast.controller;


import cn.itcast.domain.Category;
import cn.itcast.domain.ResultInfo;
import cn.itcast.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("findAllCategory")
    @ResponseBody
    public ResultInfo findAllCategory() throws JsonProcessingException {
        //调用业务逻辑层获取分类列表的json数据
        String jsonData = null;
        //定义json转换对象
        ObjectMapper objectMapper=new ObjectMapper();
        //定义返回的数据对象
        ResultInfo resultInfo = null;
        List<Category> list = new ArrayList<>();
        try {
            list = categoryService.findAllcategory();
            resultInfo = new ResultInfo(true,list,"拿到数据...");
        }catch (Exception e){
            e.printStackTrace();
            resultInfo = new ResultInfo(false,null,"服务器正忙...");
//            jsonData = null;
        }
        return resultInfo;
    }
}
