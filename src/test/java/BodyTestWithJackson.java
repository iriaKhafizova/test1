import entities.NotFound;
import entities.RateLimit;
import entities.User;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

public class BodyTestWithJackson extends BaseClass {

    @Test
    public void returnsCorrectLogin() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/iriaKhafizova");
        response = client.execute(get);

       User user = ResponseUtils.unmarshalGeneric(response, User.class);
       Assert.assertEquals(user.getLogin(), "iriaKhafizova");
        
    }

    @Test
    public void returnsCorrectId() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/iriaKhafizova");
        response = client.execute(get);

        User user = ResponseUtils.unmarshalGeneric(response, User.class);
        Assert.assertEquals(user.getId(), 60514054);

    }

    @Test
    public void notFoundMessageIsCorrect() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/nonexistingendpoint");
        response = client.execute(get);

        NotFound notFoundMessage = ResponseUtils.unmarshalGeneric(response, NotFound.class);
        Assert.assertEquals(notFoundMessage.getMessage(), "Not Found");

    }

    @Test
    public void correctRateLimitsAreSet() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");

        response = client.execute(get);
        RateLimit rateLimits = ResponseUtils.unmarshalGeneric(response, RateLimit.class);
        Assert.assertEquals(rateLimits.getCoreLimit(), 0);
        Assert.assertEquals(rateLimits.getSearchLimit(), "10");
    }
}
