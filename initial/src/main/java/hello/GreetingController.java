package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.Future;


@RestController
public class GreetingController {

    private static final String template = "Hi from Dirk and Navya, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    LookupService lookupService;


    @RequestMapping("/simplegreeting")
    public Greeting simplegreeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting();
    }


    @RequestMapping("/simplegreeting2")
    public Greeting simplegreeting2(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name),
                false);
    }

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name),
                true);
    }

    @RequestMapping("/quote")
    public Value quote() {
        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
//        log.info(quote.toString());
        return quote.getValue();
    }


    @RequestMapping("/slowgreeting")
    public Greeting slow() throws Exception {
        Future<Greeting> greeting = lookupService.find(counter.incrementAndGet(), "slow");
        while(!greeting.isDone()){
            Thread.sleep(100);
            System.out.println("waiting");
        }

        return greeting.get();
    }
}
