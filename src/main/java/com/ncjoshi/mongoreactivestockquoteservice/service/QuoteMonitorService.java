package com.ncjoshi.mongoreactivestockquoteservice.service;

import com.ncjoshi.mongoreactivestockquoteservice.client.StockQuoteClient;
import com.ncjoshi.mongoreactivestockquoteservice.domain.Quote;
import com.ncjoshi.mongoreactivestockquoteservice.repositories.QuoteRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Created by ncj on 04 Oct, 2020.
 */
@Service
public class QuoteMonitorService implements ApplicationListener<ContextRefreshedEvent> {

    private final StockQuoteClient stockQuoteClient;
    private final QuoteRepository quoteRepository;

    public QuoteMonitorService(StockQuoteClient stockQuoteClient, QuoteRepository quoteRepository) {
        this.stockQuoteClient = stockQuoteClient;
        this.quoteRepository = quoteRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        stockQuoteClient.getQuoteStream()
                .log("quote-monitor-service")
                .subscribe(quote -> {
                    Mono<Quote> savedQuote = quoteRepository.save(quote);

                    savedQuote.subscribe(res ->
                            System.out.println("I saved Quote with id: " + res.getId())
                    );

                });
    }
}
