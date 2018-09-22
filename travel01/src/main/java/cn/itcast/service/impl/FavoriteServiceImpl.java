package cn.itcast.service.impl;

import cn.itcast.domain.Favorite;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;
import cn.itcast.mapper.FavoriteMapper;
import cn.itcast.service.FavoriteService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Override
    public boolean isFavortieByRidAndUserId(Integer rid, Integer uid) {
       Favorite favorite =  favoriteMapper.isFavoriteByRidAndUserId(rid,uid);
        return favorite!=null;
    }

    @Override
    public void addFavorite(Integer rid, User user) {
        favoriteMapper.addFavorite(rid, user.getUid());
        favoriteMapper.updateRouteFavoriteNum(rid);
    }

    @Override
    public PageBean<Favorite> getPageBean(Integer curPage, Integer uid) {
        PageBean<Favorite> pageBean = new PageBean<>();
        pageBean.setCurPage(curPage);
        int pageSize = 3;
        pageBean.setPageSize(pageSize);
        PageHelper.startPage(curPage,pageSize);
        List<Favorite> favoriteList = favoriteMapper.findFavoriteListByPage(uid);
        PageInfo<Favorite> pageInfo = new PageInfo<>(favoriteList);
        pageBean.setData(pageInfo.getList());
        pageBean.setCount((int)pageInfo.getTotal());
        return pageBean;
    }
}
