package entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class RateLimit {

    private int coreLimit;
    private String searchLimit;

    public int getCoreLimit(){
        return coreLimit;
    }

    public String getSearchLimit(){
        return searchLimit;
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("resourses")
    private void unmarshallNested(Map<String, Object> resourses){

      Map<String, Integer> core = (Map<String, Integer>) resourses.get("core");
      coreLimit = core.get("limit");

        Map<String, String> search = (Map<String, String>) resourses.get("search");
        searchLimit = String.valueOf(search.get("limit"));

    }
}
