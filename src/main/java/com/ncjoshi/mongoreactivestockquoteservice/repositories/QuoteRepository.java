package com.ncjoshi.mongoreactivestockquoteservice.repositories;

import com.ncjoshi.mongoreactivestockquoteservice.domain.Quote;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

/**
 * Created by ncj on 04 Oct, 2020.
 */
public interface QuoteRepository extends ReactiveMongoRepository<Quote, String> {

    @Tailable
    Flux<Quote> findWithTailableCursorBy();
}
