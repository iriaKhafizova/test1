import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.User;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ResponseUtils {

    public static String getHeader(CloseableHttpResponse response, String headerName) {
        //Get All the headers
        Header[] headers = response.getAllHeaders();
        List<Header> httpHeaders = Arrays.asList(headers);
        String returnHeader = "";
        //Loop over the header list
        for(Header header : httpHeaders){
            if(headerName.equalsIgnoreCase(header.getName())){
                returnHeader = header.getValue();
            }
        }
        //If no header found throw an error
        if(returnHeader.isEmpty()){
            throw new RuntimeException("Didn't find the header " + headerName);
        }
        //Return the header
        return returnHeader;
    }

    public static String getHeaderJaba8Method(CloseableHttpResponse response, final String headerName){
        List<Header> httpHeader = Arrays.asList(response.getAllHeaders());
        Header matchedHeader = httpHeader.stream()
                .filter(header -> headerName.equalsIgnoreCase(header.getName()))
                .findFirst().orElseThrow(() -> new RuntimeException("Didn't find the header"));
        return matchedHeader.getValue();
    }

    public static boolean headerIsPresent(CloseableHttpResponse response, String headerName){
List<Header> httpHeader = Arrays.asList(response.getAllHeaders());

return httpHeader.stream()
        .anyMatch(header -> header.getName().equalsIgnoreCase(headerName));
    }


    public static User unmarshal(CloseableHttpResponse response, Class<User> uClass) throws IOException {
        String jsonBody = EntityUtils.toString(response.getEntity());
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(jsonBody, uClass);
    }

    public static <T> T unmarshalGeneric(CloseableHttpResponse response, Class<T> uClass) throws IOException {
        String jsonBody = EntityUtils.toString(response.getEntity());
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(jsonBody, uClass);
    }
}
