package com.ncjoshi.mongoreactivestockquoteservice.client;

import com.ncjoshi.mongoreactivestockquoteservice.domain.Quote;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * Created by ncj on 04 Oct, 2020.
 */
@Slf4j
@Setter
@Component
@ConfigurationProperties("quoteservice")
public class StockQuoteClient {

    private String host;
    private String port;
    private String path;

    public Flux<Quote> getQuoteStream() {
        String url = "http://" + host + ":" + port;

        log.debug("URL set is: " + url);

        return WebClient.builder()
                .baseUrl(url)
                .build()
                .get()
                .uri(path)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(Quote.class);
    }
}
