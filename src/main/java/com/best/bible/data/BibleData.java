package com.best.bible.data;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BibleData {
    private int bookNum;
    private String book;
    private int chapter;
    private int verse;
    private String text;
}
