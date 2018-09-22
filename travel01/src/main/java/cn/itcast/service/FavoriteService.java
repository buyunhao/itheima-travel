package cn.itcast.service;

import cn.itcast.domain.Favorite;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;

public interface FavoriteService {
    /**
     * 根据rid和uid查询用户是否收藏了该路线
     * @param rid
     * @param uid
     * @return
     */
    boolean isFavortieByRidAndUserId(Integer rid, Integer uid);

    /**
     * 添加收藏
     * @param rid
     * @param user
     */
    void addFavorite(Integer rid, User user);

    PageBean<Favorite> getPageBean(Integer curPage, Integer uid);
}
