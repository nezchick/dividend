package com.seung.dividend.scheduler;

import com.seung.dividend.model.Company;
import com.seung.dividend.model.ScrapedResult;
import com.seung.dividend.model.constants.CacheKey;
import com.seung.dividend.persisit.CompanyRepository;
import com.seung.dividend.persisit.DividendRepository;
import com.seung.dividend.persisit.entity.CompanyEntity;
import com.seung.dividend.persisit.entity.DividendEntity;
import com.seung.dividend.scraper.Scraper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@EnableCaching
@AllArgsConstructor
public class ScraperScheduler {

    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;
    private final Scraper yahooFinanceScraper;



    @CacheEvict(value = CacheKey.KEY_FINANCE, allEntries = true)
    @Scheduled(cron = "${scheduler.scrap.yahoo}")
    public void yahooFinanceScheduling() {

        log.info("scraping scheduler is started");

        //저장된 회사 목록 조회
        List<CompanyEntity> companies = this.companyRepository.findAll();

        //회사마다 배당금 정보 새로 스크리핑
        for( var company : companies ) {
            log.info("scraping scheduler is started -> " + company.getName());
            ScrapedResult scrapedResult = this.yahooFinanceScraper.scrap(
                    new Company(company.getTicker(), company.getName()));

            //배당금 정보 중 db에 없는 것은 저장

            scrapedResult.getDividends().stream()
                    //디비든모델 -> 디비든 엔티티로 매핑
                    .map(e -> new DividendEntity(company.getId(), e))
                    //엘리먼트를 하나씩 디비든 레파지토리에 삽입
                    .forEach(e -> {
                        boolean exists = this.dividendRepository.existsByCompanyIdAndDate(e.getCompanyId(), e.getDate());
                        if(!exists) {
                            this.dividendRepository.save(e);
                        }
                    });

            //얀속적으로 스크래핑 사이트 서버에 요청을 날리지 않도록 정지시키기
            try {
                Thread.sleep(3000); //3초간 정지
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }
}
