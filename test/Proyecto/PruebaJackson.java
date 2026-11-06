


import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class PruebaJackson {

    public static void main(String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(
            Map.of("status", "OK", "mensaje", "Jackson funciona")
        );

        System.out.println(json);
    }
}
