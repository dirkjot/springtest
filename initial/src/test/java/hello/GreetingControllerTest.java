package hello;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.*;

import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by pivotal on 9/15/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GreetingController.class, LookupService.class, Greeting.class, Quote.class, Value.class})
@WebAppConfiguration
public class GreetingControllerTest {

    private MockMvc mvc;


    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new GreetingController()).build();
    }

    @Test
    public void getGreeting() throws Exception {
        mvc.perform(get("/simplegreeting").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(0)));
    }
}
