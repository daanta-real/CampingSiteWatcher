package com.daanta.camp.dao;

import com.daanta.camp.domain.Site;

import java.util.List;

public interface SiteDAO {

    List<Site> selectList(Site site);

    Site selectOne(Site site);

    void insertOne(Site site);

    void updateOne(Site site);

    void delete(Site site);

}
