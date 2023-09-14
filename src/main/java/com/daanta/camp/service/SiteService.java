package com.daanta.camp.service;

import com.daanta.camp.domain.Site;

import java.util.List;

public interface SiteService {

    int selectKey();

    List<Site> selectList(Site site);

    Site selectOne(int idx);

    int insertOne(Site site);

    void updateOne(Site site);

    void delete(Site site);

}
