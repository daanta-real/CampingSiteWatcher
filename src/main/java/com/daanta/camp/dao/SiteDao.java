package com.daanta.camp.dao;

import com.daanta.camp.domain.Target;

import java.util.List;

public interface SiteDao {

    List<Target> selectList(Target target);

    Target selectOne(Target target);

    void insertOne(Target target);

    void updateOne(Target target);

    void delete(Target target);

}
