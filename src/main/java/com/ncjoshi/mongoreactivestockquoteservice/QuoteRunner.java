package com.ncjoshi.mongoreactivestockquoteservice;

import com.ncjoshi.mongoreactivestockquoteservice.client.StockQuoteClient;
import com.ncjoshi.mongoreactivestockquoteservice.domain.Quote;
import com.ncjoshi.mongoreactivestockquoteservice.repositories.QuoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

/**
 * Created by ncj on 04 Oct, 2020.
 */
@Component
public class QuoteRunner implements CommandLineRunner {

    private final StockQuoteClient stockQuoteClient;
    private final QuoteRepository quoteRepository;

    public QuoteRunner(StockQuoteClient stockQuoteClient, QuoteRepository quoteRepository) {
        this.stockQuoteClient = stockQuoteClient;
        this.quoteRepository = quoteRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Flux<Quote> quoteFlux = quoteRepository.findWithTailableCursorBy();

        Disposable disposable = quoteFlux.subscribe(quote -> {
            System.out.println("*#*#*#*#*#*#*# Id: " + quote.getId());
        });

        disposable.dispose();
    }
}
