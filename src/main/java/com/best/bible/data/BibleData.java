package com.best.bible.data;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BibleData {
    private List<BookData> books;
}
