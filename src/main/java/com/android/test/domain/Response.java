
package com.android.test.domain;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("com.googlecode.jsonschema2pojo")
public class Response {

    @Expose
    private List<Object> neighborhoods = new ArrayList<Object>();
    @Expose
    private List<Venue> venues = new ArrayList<Venue>();
    @Expose
    private Boolean confident;

    public List<Object> getNeighborhoods() {
        return neighborhoods;
    }

    public void setNeighborhoods(List<Object> neighborhoods) {
        this.neighborhoods = neighborhoods;
    }

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

    public Boolean getConfident() {
        return confident;
    }

    public void setConfident(Boolean confident) {
        this.confident = confident;
    }

}
