package com.best.bible.service;

import com.best.bible.data.BibleData;
import com.best.bible.repository.BibleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BibleInsertService {

    private final BibleRepository bibleRepository;

    public void updateBible(List<BibleData> bibleDataList){
        for(BibleData data : bibleDataList){
            bibleRepository.insertBibleData(data);
        }
    }

}