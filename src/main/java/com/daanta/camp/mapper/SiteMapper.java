package com.daanta.camp.mapper;

import com.daanta.camp.domain.Site;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SiteMapper {

    int selectKey();

    List<Site> selectList(Site site);

    Site selectOne(Site site);

    int insertOne(Site site);

    void updateOne(Site site);

    void delete(Site site);

}
