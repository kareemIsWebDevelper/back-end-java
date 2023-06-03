package com.bfcai.topjob.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class UserJobsPackage {
    private List<UserJobDTO> matching;
    private List<UserJobDTO> others;

    public UserJobsPackage() {
        this.others = new ArrayList<>();
        this.matching = new ArrayList<>();
    }
}
