package com.daanta.camp.dao;

import com.daanta.camp.domain.Site;

import java.util.List;

public interface SiteDao {

    List<Site> selectList(Site target);

    Site selectOne(Site target);

    void insertOne(Site target);

    void updateOne(Site target);

    void delete(Site target);

}
