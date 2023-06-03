package com.bfcai.topjob.service;

import com.bfcai.topjob.dto.CompanyPostDTO;

public interface CompanyPostService {
    Boolean savePost(CompanyPostDTO companyPostDTO);

    Boolean deletePost(Long postId);
}
