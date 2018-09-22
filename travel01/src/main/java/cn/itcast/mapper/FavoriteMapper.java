package cn.itcast.mapper;

import cn.itcast.domain.Favorite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FavoriteMapper {
    Favorite isFavoriteByRidAndUserId(@Param("rid") Integer rid, @Param("uid") Integer uid);



    void updateRouteFavoriteNum(@Param("rid") Integer rid);

    void addFavorite(@Param("rid") Integer rid, @Param("uid") Integer uid);

    List<Favorite> findFavoriteListByPage(@Param("uid") Integer uid);
}
