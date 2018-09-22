package cn.itcast.service.impl;

import cn.itcast.domain.Category;
import cn.itcast.mapper.CategoryMapper;
import cn.itcast.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<Category> findAllcategory() throws Exception {
        //定义返回的json
        String jsonData = null;
//        Jedis jedis = null ;
        List<Category> list = new ArrayList<>();
        try {
            //从redis缓存库中取
            //获取jedis连接对象
//            jedis = JedisUtil.getJedis();
//            jsonData = jedis.get("categoryList");
            jsonData = (String) redisTemplate.opsForValue().get("categoryList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //判断有效性
        if (jsonData == null || "".equals(jsonData)) {
            List<Category> categoryList = categoryMapper.findAllCategory();
            jsonData = new ObjectMapper().writeValueAsString(categoryList);
        } else {
            list = new ObjectMapper().readValue(jsonData, ArrayList.class);
        }
        try {
//                jedis.set("categoryList",jsonData);
            redisTemplate.opsForValue().set("categoryList", jsonData);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//            //将json返回
//            jedis.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        return list;
    }
}
