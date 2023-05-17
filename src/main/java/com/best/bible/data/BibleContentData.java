package com.best.bible.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BibleContentData {
	private int bookNum;
	private String book;
	private int chapter;
	private int verse;
	private String text;
}
