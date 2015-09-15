package hello;
import lombok.Data;
import org.springframework.web.client.RestTemplate;

@Data
public class Greeting {

    private final long id;
    private final String content;
    private final String version = "5";

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getQuote() {
        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        return quote.getValue().getQuote();
    }


}




/*

So the spring framework automatically calls anything that starts with get.. and then returns json for that.  For
getContent it will use the json key 'content' (no caps).


 */