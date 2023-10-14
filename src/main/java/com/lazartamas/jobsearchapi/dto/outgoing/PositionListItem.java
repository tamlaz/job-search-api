package com.lazartamas.jobsearchapi.dto.outgoing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lazartamas.jobsearchapi.domain.Position;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionListItem {

    private String jobTitle;
    private String locationName;
    private String jobUrl;

    public PositionListItem(Position position) {
        this.jobTitle = position.getJobTitle();
        this.locationName = position.getLocation();
        this.jobUrl = position.getUrl();
    }
}
