package com.seung.dividend.scraper;

import com.seung.dividend.model.Company;
import com.seung.dividend.model.ScrapedResult;

public interface Scraper {

    Company scrapCompanyByTicker(String ticker);

    ScrapedResult scrap(Company company);
}
