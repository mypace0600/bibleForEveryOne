package com.best.bible.controller;

import com.best.bible.data.*;
import com.best.bible.mybatis.MySqlMapClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

@Slf4j
@RequiredArgsConstructor
public class JsonReaderController {


    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        SqlSession mySqlSession = null;
        try {
            SqlSessionFactory sqlSessionFactory = MySqlMapClient.getSqlSession();
            mySqlSession = sqlSessionFactory.openSession();
        } catch(Exception e){
            e.printStackTrace();
        }

        try {
            /*File jsonFile = new File(path);*/
            File jsonFile = new File("C:\\workspace\\bibleForEveryOne\\src\\main\\resources\\revelation.json");
            BookData bookData = objectMapper.readValue(jsonFile, BookData.class);
            List<BibleData> bibleDataList = new ArrayList<>();
            for (ChapterData chapter : bookData.getChapters()) {
                for (VerseData verse : chapter.getVerses()) {
                    BibleData bibleData = BibleData.builder()
                            .bookNum(TitleData.valueOf(bookData.getBook()).getNumber())
                            .book(bookData.getBook())
                            .chapter(chapter.getChapter())
                            .verse(verse.getVerse())
                            .text(verse.getText())
                            .build();
                    bibleDataList.add(bibleData);
                    mySqlSession.insert("MySql.insertBible",bibleData);
                    mySqlSession.flushStatements();
                    mySqlSession.commit();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
