package com.daanta.camp.dao;

import com.daanta.camp.domain.Site;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

// SITE DAO - MyBatis Version
@Repository
public class SiteDAOBatis implements SiteDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public int selectKey() {
        return sqlSession.selectOne("com.daanta.camp.dao.SiteDAO.selectKey");
    }

    @Override
    public List<Site> selectList(Site site) {
        return null;
    }

    @Override
    public Site selectOne(Site site) {
        return null;
    }

    @Override
    public int insertOne(Site site) {
        return 0;
    }

    @Override
    public void updateOne(Site site) {

    }

    @Override
    public void delete(Site site) {

    }

}
