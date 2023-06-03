package com.bfcai.topjob.service;

import com.bfcai.topjob.dto.UserPostDTO;

public interface UserPostService {
    Boolean savePost(UserPostDTO userPostDTO);

    Boolean deletePost(Long postId);
}
