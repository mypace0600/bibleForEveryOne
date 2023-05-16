package com.best.bible.controller;

import com.best.bible.data.*;
import com.best.bible.service.BibleInsertService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JsonReaderController {

    public static BibleInsertService service;

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            /*File jsonFile = new File(path);*/
            File jsonFile = new File("/Users/hyunsu/Workspace/요한계시록.json");
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
                }
            }
            service.updateBible(bibleDataList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
