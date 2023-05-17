package com.best.bible.controller;

import com.best.bible.data.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class JsonReaderController {

    @GetMapping("/")
    public String getBibleDataListByBookAndChapter(Integer bookNum, Integer chapterNum, Model model) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<BibleContentData> bibleContentDataList = new ArrayList<>();
        if(bookNum==null){
            bookNum = 1;
        }
        if(chapterNum == null){
            chapterNum = 0;
        }
        BookData bookData = getBookData(bookNum, objectMapper);
        ChapterData chapterData = bookData.getChapters().get(chapterNum);
        for (VerseData verse : chapterData.getVerses()) {
            BibleContentData bibleData = BibleContentData.builder()
                    .bookNum(TitleData.valueOf(bookData.getBook()).getNumber())
                    .book(bookData.getBook())
                    .chapter(chapterData.getChapter())
                    .verse(verse.getVerse())
                    .text(verse.getText())
                    .build();
            bibleContentDataList.add(bibleData);
        }
        String bookName = bookData.getBook();
        model.addAttribute("bookName",bookName);
        model.addAttribute("chapterNum",chapterNum+1);
        model.addAttribute("bibleContentDataList",bibleContentDataList);
        return "index";
    }

    private static BookData getBookData(int bookNum, ObjectMapper objectMapper) throws IOException {

        String bookName = getBookNameByBookNum(bookNum);

        String jsonFileName = "bible/"+ bookName + ".json";
        ClassPathResource resource = new ClassPathResource(jsonFileName);
        Path path = Paths.get(resource.getURI());
        File jsonFile = new File(path.toString());
        BookData bookData = objectMapper.readValue(jsonFile, BookData.class);
        return bookData;
    }

    private static String getBookNameByBookNum(int bookNum) {
        String bookName = Arrays.stream(TitleData.values())
                .filter(title -> title.getNumber() == bookNum)
                .findFirst().orElseThrow(()->new IllegalArgumentException("해당 성경이 없습니다.")).getName();
        return bookName;
    }

}
