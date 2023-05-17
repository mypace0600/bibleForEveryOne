package com.best.bible;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.best.bible.data.TitleData;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class BibleApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibleApplication.class, args);
	}

}
