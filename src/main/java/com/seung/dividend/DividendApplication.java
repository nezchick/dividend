package com.seung.dividend;

import com.seung.dividend.model.Company;
import com.seung.dividend.scraper.YahooFinanceScraper;
import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.swing.table.TableRowSorter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class DividendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DividendApplication.class, args);

//		YahooFinanceScraper scraper = new YahooFinanceScraper();
//		var result = scraper.scrapCompanyByTicker("MMM");
//		System.out.println(result);

//		Trie trie = new PatriciaTrie();
//
//		AutoComplete autoComplete = new AutoComplete(trie);
//		AutoComplete autoComplete1 = new AutoComplete(trie);
//
//		autoComplete.add("hello");
//		autoComplete1.add("hello");
//
//		System.out.println(autoComplete.get("hello"));
//		System.out.println(autoComplete1.get("hello"));
}
}
