package com.best.bible.data;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ChapterData {
    private int chapter;
    private List<VerseData> verses;
}
