package hello;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LookupService {

    RestTemplate restTemplate = new RestTemplate();

    @Async
    public Future<Greeting> find(Long id, String name) throws InterruptedException {
        System.out.println("Looking up " + name);
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        Greeting greeting = new Greeting(id, name, false);
        greeting.setQuote(quote.getValue().getQuote());
        Thread.sleep(1000L);
        return new AsyncResult<Greeting>(greeting);
    }

}