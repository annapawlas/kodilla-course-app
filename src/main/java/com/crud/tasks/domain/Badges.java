package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Badges {

    @JsonProperty("votes")
    private String votes;

    @JsonProperty("attachmentsByType")
    private AttachmentsByType attachmentsByType;
}
