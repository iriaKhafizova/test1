import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static entities.User.ID;
import static entities.User.LOGIN;

public class BodyTestWithSimpleMap extends BaseClass {

    @Test
    public void returnsCorrectLogin() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/iriaKhafizova");
        response = client.execute(get);
        String jsonBody = EntityUtils.toString(response.getEntity());
        //System.out.println(jsonBody);
        JSONObject jsonObject = new JSONObject(jsonBody);

        String loginValue = (String) getVelueFor(jsonObject, LOGIN);

        Assert.assertEquals(loginValue, "iriaKhafizova");
    }

    @Test
    public void returnsCorrectId() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/iriaKhafizova");
        response = client.execute(get);
        String jsonBody = EntityUtils.toString(response.getEntity());
        //System.out.println(jsonBody);
        JSONObject jsonObject = new JSONObject(jsonBody);

        Integer loginValue = (Integer) getVelueFor(jsonObject, ID);

        Assert.assertEquals(loginValue, Integer.valueOf(118698547));
    }

    private Object getVelueFor(JSONObject jsonObject, String key) {
        return jsonObject.get(key);
    }
}
