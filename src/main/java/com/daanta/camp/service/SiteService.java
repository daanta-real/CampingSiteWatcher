package com.daanta.camp.service;

import com.daanta.camp.dao.SiteDAO;
import com.daanta.camp.domain.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SiteService {

    @Autowired
    private SiteDAO siteDAO;

    int add(Site site) {
        return siteDAO.insertOne(site);
    }

}
