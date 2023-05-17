package com.best.bible.controller;

import com.best.bible.data.*;
import com.best.bible.mybatis.MySqlMapClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class JsonReaderController {

    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<BibleContentData> bibleDataList = new ArrayList<>();

        for(TitleData data : TitleData.values()){
            String bookName = data.getName();
            log.debug("@@@@@@@@@@@@@ bookName :{}",bookName);
            BookData bookData = getBookData(bookName, objectMapper);
            for (ChapterData chapter : bookData.getChapters()) {
                for (VerseData verse : chapter.getVerses()) {
                    BibleContentData bibleData = BibleContentData.builder()
                            .bookNum(TitleData.valueOf(bookData.getBook()).getNumber())
                            .book(bookData.getBook())
                            .chapter(chapter.getChapter())
                            .verse(verse.getVerse())
                            .text(verse.getText())
                            .build();
                    bibleDataList.add(bibleData);
                    log.debug("@@@@@@@@ bibleData :{}",bibleData.toString());
                }
            }
        }
    }

    private static BookData getBookData(String bookName, ObjectMapper objectMapper) throws IOException {
        String jsonFileName = TitleData.valueOf(bookName).getName() + ".json";
        ClassPathResource resource = new ClassPathResource("bible/"+jsonFileName);
        Path path = Paths.get(resource.getURI());
        File jsonFile = new File(path.toString());
        BookData bookData = objectMapper.readValue(jsonFile, BookData.class);
        return bookData;
    }

}
