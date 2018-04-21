package xenon;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    private static Gson GSON_OBJ = new Gson();
    @RequestMapping("/")
    public String index() {
        TestObject test = new TestObject();

        String objJSON = GSON_OBJ.toJson(test);
        return objJSON;
    }

    class TestObject {
        String message = "Hello World?";

        public TestObject() {}
    }
}