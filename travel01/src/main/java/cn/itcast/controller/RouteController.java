package cn.itcast.controller;


import cn.itcast.domain.PageBean;
import cn.itcast.domain.ResultInfo;
import cn.itcast.domain.Route;
import cn.itcast.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("route")
public class RouteController {
    @Autowired
    private RouteService routeService;

    public ResultInfo routeCaraChoose(){
        ResultInfo resultInfo = null;
        try {
            Map<String, List<Route>> map = routeService.routeCaraChoose();
            resultInfo = new ResultInfo(true,map,null);
        }catch (Exception e){
            e.printStackTrace();
            resultInfo = new ResultInfo(false,null,"服务器正忙");
        }
        return resultInfo;
    }


    @RequestMapping("findRouteListByCid")
    @ResponseBody
    public ResultInfo findRouteListByCid(@RequestParam("cid") Integer cid, @RequestParam(value = "curPage",defaultValue = "1") Integer curPage, @RequestParam(value = "rname", required = false) String rname){
        ResultInfo resultInfo = null;
        try {
            PageBean pageBean = routeService.getPageBean(cid,curPage,rname);
            resultInfo = new ResultInfo(true,pageBean,null);
        }catch (Exception e){
            e.printStackTrace();
            resultInfo = new ResultInfo(false,null,"服务器正忙...");
        }
        return resultInfo;

    }

    @RequestMapping("findRouteByRid")
    @ResponseBody
    public ResultInfo findRouteByRid(@RequestParam("rid") Integer rid){
        ResultInfo resultInfo = null;
        try {
            Route route = routeService.findRouteByRid(rid);
            resultInfo = new ResultInfo(true,route,"拿到数据了...");
        }catch (Exception e){
            e.printStackTrace();
            resultInfo = new ResultInfo(false,null,"服务器正忙...");
        }
        return resultInfo;
    }
}
