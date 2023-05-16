package com.best.bible.repository;

import com.best.bible.data.BibleData;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class BibleRepository {
    private final SqlSession sqlSession;

    @Autowired
    public BibleRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public int insertBibleData(BibleData bibleData){
        return sqlSession.insert("com.best.bible.mappers.BibleMapper.insertBibleData",bibleData);
    }

}

