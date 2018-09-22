package cn.itcast.controller;

import cn.itcast.domain.*;
import cn.itcast.service.FavoriteService;
import cn.itcast.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private RouteService routeService;

    /**
     * @return
     */
    @RequestMapping("isFavoriteByRid")
    @ResponseBody
    public ResultInfo isFavoriteByRid(@RequestParam("rid") Integer rid, HttpSession session) {
        ResultInfo resultInfo = null;
        try {
            User user = (User) session.getAttribute("loginUser");
            if (user == null) {
                resultInfo = new ResultInfo(true, false, null);
            } else {
                boolean flag = favoriteService.isFavortieByRidAndUserId(rid, user.getUid());
                if (flag) {
                    resultInfo = new ResultInfo(true, false, null);
                } else {
                    resultInfo = new ResultInfo(true, false, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo = new ResultInfo(false, null, "服务器正忙...");
        }

        return resultInfo;
    }

    @RequestMapping("addFavorite")
    @ResponseBody
    public ResultInfo addFavorite(@RequestParam("rid") Integer rid, HttpSession session) {
        ResultInfo resultInfo = null;
        try {
            User user = (User) session.getAttribute("loginUser");
            if (user == null) {
                resultInfo = new ResultInfo(true, false, null);
            } else {
                favoriteService.addFavorite(rid, user);
                Route route = routeService.findRouteByRid(rid);
                int count = route.getCount();
                resultInfo = new ResultInfo(true, count, null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultInfo = new ResultInfo(false, null, "服务器正忙...");
        }
        return resultInfo;
    }

    @RequestMapping("findFavoriteByPage")
    @ResponseBody
    public ResultInfo findFavoriteByPage(@RequestParam(value = "curPage", defaultValue = "1") Integer curPage, HttpSession session) {
        ResultInfo resultInfo = null;
        try {
            User user = (User) session.getAttribute("loginUser");
            PageBean<Favorite> pageBean = favoriteService.getPageBean(curPage,user.getUid());
            resultInfo = new ResultInfo(true,pageBean,null);
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo = new ResultInfo(false, null, "服务器正忙...");
        }
        return resultInfo;
    }
}
