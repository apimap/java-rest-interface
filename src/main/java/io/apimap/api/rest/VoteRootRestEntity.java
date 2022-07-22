package io.apimap.api.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import io.apimap.api.rest.jsonapi.JsonApiViews;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class VoteRootRestEntity {
    @JsonValue
    @JsonView(JsonApiViews.Default.class)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected ArrayList<VoteDataRestEntity> data = new ArrayList<>();

    public VoteRootRestEntity() {
    }

    @JsonCreator
    public VoteRootRestEntity(ArrayList<VoteDataRestEntity> data) {
        this.data = data;
    }

    public ArrayList<VoteDataRestEntity> getData() {
        return data;
    }

    public void setData(ArrayList<VoteDataRestEntity> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "VoteRootRestEntity{" +
                "data=" + data +
                '}';
    }
}
