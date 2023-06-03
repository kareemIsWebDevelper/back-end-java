package com.bfcai.topjob.dto;

import com.bfcai.topjob.model.CompanyPost;
import com.bfcai.topjob.model.UserPost;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@Data
@ToString
public class TimelinePackage {
    private List<CompanyPost> companyPosts;
    private List<UserPost> userPosts;

    public TimelinePackage() {
        this.companyPosts = new ArrayList<>();
        this.userPosts = new ArrayList<>();
    }
}
