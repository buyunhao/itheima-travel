package cn.itcast.service.impl;

import cn.itcast.domain.PageBean;
import cn.itcast.domain.Route;
import cn.itcast.mapper.RouteMapper;
import cn.itcast.service.RouteService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    private RouteMapper routeMapper;

    @Override
    public Map<String, List<Route>> routeCaraChoose() {
        Map<String, List<Route>> map = new HashMap<String, List<Route>>();
        List<Route> routeList = routeMapper.getPopularityRouteList();
        List<Route> newsList = routeMapper.getNewsRouteList();
        List<Route> themesList = routeMapper.getThemeRouteList();
        map.put("popularity", routeList);
        map.put("news", newsList);
        map.put("themes", themesList);
        return map;
    }

    @Override
    public PageBean getPageBean(Integer cid, Integer curPage, String rname) {
        PageBean<Route> pageBean = new PageBean<>();
        pageBean.setCurPage(curPage);
        int pageSize = 5;
        pageBean.setPageSize(pageSize);
        PageHelper.startPage(curPage, pageSize);
        List<Route> routeList = routeMapper.findRouteListByPage(cid, rname);
        PageInfo<Route> pageInfo = new PageInfo<>(routeList);
        pageBean.setData(pageInfo.getList());
        pageBean.setCount((int) pageInfo.getTotal());
        return pageBean;
    }

    @Override
    public Route findRouteByRid(Integer rid) {
        Route route = routeMapper.findRouteByRid(rid);
        return route;
    }

}
