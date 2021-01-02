import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DeleteAndPost extends BaseClass {

    @Test
    public void deleteIsSucessful() throws IOException {
        HttpDelete request = new HttpDelete(BASE_ENDPOINT);
        request.setHeader(HttpHeaders.AUTHORIZATION, "token");//not a real token

        response = client.execute(request);

        int actualStatusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatusCode, 401);
    }

    @Test(description = "This test will work only with real redantials")
    public void createRepoReturs201() throws IOException{

        //create an HTTP with a valid endpoint
        HttpPost request = new HttpPost(BASE_ENDPOINT);
        //set a basic auth header
        String auth = "Email" + ":" + "password";
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.ISO_8859_1));
        String autheader = "Basic" + new String(encodedAuth);
        request.setHeader(HttpHeaders.AUTHORIZATION, autheader);

        //Define Json and Post and set as Entity
        String json = "{\"name\" : \"deleteme\"}";
        request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        //Send
        response = client.execute(request);

        int actualStatusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatusCode, 404);
    }
}
