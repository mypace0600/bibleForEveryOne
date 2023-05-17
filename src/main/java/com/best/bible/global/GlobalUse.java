package com.best.bible.global;

import com.best.bible.data.BookData;
import com.best.bible.data.TitleData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class GlobalUse {

    public static BookData getBookData(int bookNum) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
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
