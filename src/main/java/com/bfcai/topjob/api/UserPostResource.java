package com.bfcai.topjob.api;


import com.bfcai.topjob.dto.UserPostDTO;
import com.bfcai.topjob.service.UserPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user-posts")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserPostResource {


    private final UserPostService postService;

    @PostMapping
    public ResponseEntity<Boolean> savePost(@RequestBody UserPostDTO userPostDTO) {
        return ResponseEntity.ok(this.postService.savePost(userPostDTO));
    }

    @DeleteMapping(path = "/{postId}")
    public ResponseEntity<Boolean> deletePost(@PathVariable(value = "postId") Long postId) {
        return ResponseEntity.ok(this.postService.deletePost(postId));
    }
}
