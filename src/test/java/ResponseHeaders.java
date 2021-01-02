import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ResponseHeaders extends BaseClass {



    @Test
    public void contentTypeIsJson() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);

        response = client.execute(get);

        Header contentType = response.getEntity().getContentType();

        assertEquals(contentType.getValue(), "application/json; charset=utf-8");

        ContentType ct = ContentType.getOrDefault(response.getEntity());
        assertEquals(ct.getMimeType(), "application/json");
    }

    @Test
    public void serverIsGitHub() throws IOException{
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);
        String headerValue = ResponseUtils.getHeader(response, "Server");
        assertEquals(headerValue, "GitHub.com");
    }

   @Test
    public void xRateLimitIsSixty() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response =client.execute(get);
       String limitVal = ResponseUtils.getHeaderJaba8Method(response, "X-RateLimit-Limit");
       assertEquals(limitVal, "60");

   }

   @Test
    public void eTagIsPresent() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);
        boolean tagIsPresent = ResponseUtils.headerIsPresent(response, "ETag");
        assertTrue(tagIsPresent);
   }
}
