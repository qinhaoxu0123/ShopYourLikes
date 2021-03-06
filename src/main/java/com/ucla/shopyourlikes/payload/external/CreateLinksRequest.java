package com.ucla.shopyourlikes.payload.external;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * This class contains all information about the CreateLinksRequest object ,including all the getters and setters.
 */
public class CreateLinksRequest {

    @NotNull
    private List<String> urls;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

}
