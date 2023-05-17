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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.best.bible.global.GlobalUse.getBookData;

@Slf4j
@Controller
@RequiredArgsConstructor
public class JsonReaderController {

    @GetMapping("/")
    public String getBibleDataListByBookAndChapter(Integer bookNum, Integer chapterNum, Model model) throws IOException {

        if(bookNum == null){
            bookNum = 1;
        }
        BookData bookData = getBookData(bookNum);

        if(chapterNum == null){
            chapterNum = 0;
        }
        ChapterData chapterData = bookData.getChapters().get(chapterNum);

        List<BibleContentData> bibleContentDataList = new ArrayList<>();
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



    @GetMapping("/select/book")
    public String selectBookName(Model model) throws IOException {

        List<Box> bookDataList = new ArrayList<>();

        for(TitleData data : TitleData.values()){
            Box box = new Box();
            box.put("bookName",data.getName());
            box.put("bookNum",data.getNumber());
            int bookNum = data.getNumber();

            BookData bookData = getBookData(bookNum);
            List<Integer> chapterNumberList = new ArrayList<>();
            for(ChapterData chapterData: bookData.getChapters()){
                chapterNumberList.add(chapterData.getChapter());
            }
            box.put("chapterNumberList",chapterNumberList);
            bookDataList.add(box);
        }

        model.addAttribute("bookNameList",bookDataList);

        return "selectBookName";
    }

    @GetMapping("/select/book/{bookNum}")
    public String selectChapterNumber(@PathVariable String bookNum, Model model) throws IOException {
        BookData bookData = getBookData(Integer.parseInt(bookNum));

        List<Integer> chapterNumberList = new ArrayList<>();
        for(ChapterData chapterData: bookData.getChapters()){
            chapterNumberList.add(chapterData.getChapter());
        }

        model.addAttribute("bookName",bookData.getBook());
        model.addAttribute("chapterNumberList",chapterNumberList);

        return "selectChapterNumber";
    }


}
