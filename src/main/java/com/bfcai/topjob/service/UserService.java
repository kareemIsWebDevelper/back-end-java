package com.bfcai.topjob.service;

import com.bfcai.topjob.dto.*;
import com.bfcai.topjob.model.UserPost;

import java.util.List;
import java.util.Set;

public interface UserService {
    Set<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(Long userId);

    UserResponseDTO saveUser(UserSaveDTO userSaveDTO);

    Long userValidateLogin(LoginDTO loginDTO);

    Boolean deleteUserById(Long userId);

    Boolean updateUser(Long userId, UserSaveDTO userSaveDTO);

    Boolean updateUserAbout(Long userId,String about);

    Set<UserResponseDTO> getAllUsersByName(String searchKey);

    Boolean userFollow(Long userId, Long followedUserId);

    Boolean isUserFollow(Long userId, Long followedUserId);

    Boolean userUnfollow(Long userId, Long unfollowedUserId);

    Set<UserResponseDTO> getUserFollowers(Long userId);

    Set<UserResponseDTO> getUserFollowingUsers(Long userId);

    Set<CompanyResponseDTO> getUserFollowingCompanies(Long userId);

    List<Long> getUserAppliedJobsIds(Long userId);

    List<UserPost> fetchUserPosts(Long userId);

    TimelinePackage fetchUserTimeline(Long userId);
}
