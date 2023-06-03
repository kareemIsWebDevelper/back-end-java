package com.bfcai.topjob.api;

import com.bfcai.topjob.dto.*;
import com.bfcai.topjob.model.UserPost;
import com.bfcai.topjob.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;


@RestController
@RequestMapping(path = "/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserResource {

    public final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Long> userLogin(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(this.userService.userValidateLogin(loginDTO));
    }

    @GetMapping
    public ResponseEntity<Set<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable(value = "userId") Long userId) {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

    @GetMapping(path = "/search/{searchKey}")
    public ResponseEntity<Set<UserResponseDTO>> getAllUsersByName(@PathVariable(value = "searchKey") String searchKey) {
        return ResponseEntity.ok(this.userService.getAllUsersByName(searchKey));
    }

    @GetMapping(path = "/{userId}/following/{followedUserId}")
    public ResponseEntity<Boolean> userFollow(@PathVariable(value = "userId") Long userId, @PathVariable(value = "followedUserId") Long followedUserId) {
        return ResponseEntity.ok(this.userService.userFollow(userId, followedUserId));
    }

    @GetMapping(path = "/{userId}/unfollowing/{unfollowedUserId}")
    public ResponseEntity<Boolean> userUnfollow(@PathVariable(value = "userId") Long userId, @PathVariable(value = "unfollowedUserId") Long unfollowedUserId) {
        return ResponseEntity.ok(this.userService.userUnfollow(userId, unfollowedUserId));
    }

    @GetMapping(path = "/{userId}/isFollow/{followedUserId}")
    public ResponseEntity<Boolean> isUserFollow(@PathVariable(value = "userId") Long userId, @PathVariable(value = "followedUserId") Long followedUserId){
        return ResponseEntity.ok(this.userService.isUserFollow(userId,followedUserId));
    }

    @GetMapping(path = "/{userId}/followers")
    public ResponseEntity<Set<UserResponseDTO>> fetchUserFollowers(@PathVariable(value = "userId") Long userId){
        return ResponseEntity.ok(this.userService.getUserFollowers(userId));
    }

    @GetMapping(path = "/{userId}/following-users")
    public ResponseEntity<Set<UserResponseDTO>> fetchUserFollowingUsers(@PathVariable(value = "userId") Long userId){
        return ResponseEntity.ok(this.userService.getUserFollowingUsers(userId));
    }

    @GetMapping(path = "/{userId}/following-companies")
    public ResponseEntity<Set<CompanyResponseDTO>> fetchUserFollowingCompanies(@PathVariable(value = "userId") Long userId){
        return ResponseEntity.ok(this.userService.getUserFollowingCompanies(userId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Boolean> saveUser(@RequestBody UserSaveDTO userSaveDTO) {
        try {
            this.userService.saveUser(userSaveDTO);
            return ResponseEntity.ok(true);
        } catch (Exception exception) {
            return ResponseEntity.ok(false);
        }
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable(value = "userId") Long userId) {
        return ResponseEntity.ok(this.userService.deleteUserById(userId));
    }


    @PutMapping(path = "/{userId}")
    public ResponseEntity<Boolean> updateUser(@PathVariable(value = "userId") Long userId, @RequestBody UserSaveDTO userSaveDTO) {
        return ResponseEntity.ok(this.userService.updateUser(userId, userSaveDTO));
    }

    @PostMapping(path = "/{userId}/about")
    public ResponseEntity<Boolean> updateUserAbout(@PathVariable(value = "userId") Long userId, @RequestBody String about) {
        return ResponseEntity.ok(this.userService.updateUserAbout(userId, about));
    }


    @GetMapping(path = "/{userId}/applied-jobs")
    public ResponseEntity<List<Long>> getUserAppliedJobsIds(@PathVariable(value = "userId") Long userId){
        return ResponseEntity.ok(this.userService.getUserAppliedJobsIds(userId));
    }

    @GetMapping(path = "/{userId}/posts")
    public ResponseEntity<List<UserPost>> fetchUserPosts(@PathVariable(value = "userId") Long userId){
        return ResponseEntity.ok(this.userService.fetchUserPosts(userId));
    }

    @GetMapping(path = "/{userId}/timeline")
    public ResponseEntity<TimelinePackage> fetchUserTimeline(@PathVariable(value = "userId") Long userId){
        return ResponseEntity.ok(this.userService.fetchUserTimeline(userId));
    }
}
