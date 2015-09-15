package hello;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class HelloControllerTest {

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/helloworld"))
                .andExpect(status().isOk())
                .andExpect(view().name("hellotemplate"));
                //.andExpect(content().string(containsString("pre")));
                //.andExpect(content().string(containsString("<pre>\n" +
                //        "hardcoded hello\n" +
                 //       "</pre>")))
                //.)

    }

    @Test
    public void getHello2() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/helloworld2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(xpath("//h1").exists())
                .andExpect(content().string(equalTo("<html><h1>Hello world</h1><p>Hardcoded without a template</p></html>")));
    }

}