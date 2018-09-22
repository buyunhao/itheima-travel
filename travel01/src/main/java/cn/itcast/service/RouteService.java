package cn.itcast.service;

import cn.itcast.domain.PageBean;
import cn.itcast.domain.Route;

import java.util.List;
import java.util.Map;

public interface RouteService {
    Map<String, List<Route>> routeCaraChoose();

    PageBean getPageBean(Integer cid, Integer curPage, String rname);

    /**
     * 根据路线id查询路线详情
     * @param rid
     * @return
     */
    Route findRouteByRid(Integer rid);
}
