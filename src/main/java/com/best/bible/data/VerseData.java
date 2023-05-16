package com.best.bible.data;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class VerseData {
    private int verse;
    private String text;
}
