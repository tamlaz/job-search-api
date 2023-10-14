package com.lazartamas.jobsearchapi.dto.outgoing;

import com.lazartamas.jobsearchapi.domain.Position;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PositionListItem {

    private String jobTitle;
    private String location;
    private String jobUrl;

    public PositionListItem(Position position) {
        this.jobTitle = position.getJobTitle();
        this.location = position.getLocation();
        this.jobUrl = position.getUrl();
    }
}
