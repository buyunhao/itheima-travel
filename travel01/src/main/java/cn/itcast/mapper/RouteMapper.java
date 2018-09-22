package cn.itcast.mapper;

import cn.itcast.domain.Route;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteMapper {
    List<Route> getPopularityRouteList();

    List<Route> getNewsRouteList();

    List<Route> getThemeRouteList();

    List<Route> findRouteListByPage(@Param("cid") Integer cid, @Param("rname") String rname);

    Route findRouteByRid(@Param("rid") Integer rid);
}
