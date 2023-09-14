package com.daanta.camp.service;

import com.daanta.camp.domain.Site;
import com.daanta.camp.mapper.SiteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    private SiteMapper siteMapper;

    @Override
    public int selectKey() {
        return siteMapper.selectKey();
    }

    @Override
    public List<Site> selectList(Site site) {
        return siteMapper.selectList(site);
    }

    @Override
    public Site selectOne(int idx) {
        return siteMapper.selectOne(idx);
    }

    @Override
    public int insertOne(Site site) {
        return siteMapper.insertOne(site);
    }

    @Override
    public void updateOne(Site site) {
        siteMapper.updateOne(site);
    }

    @Override
    public void delete(Site site) {
        siteMapper.delete(site);
    }

}
