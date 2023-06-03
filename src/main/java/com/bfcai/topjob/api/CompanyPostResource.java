package com.bfcai.topjob.api;

import com.bfcai.topjob.dto.CompanyPostDTO;
import com.bfcai.topjob.service.CompanyPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/company-posts")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class CompanyPostResource {

    private final CompanyPostService companyPostService;

    @PostMapping
    public ResponseEntity<Boolean> savePost(@RequestBody CompanyPostDTO companyPostDTO) {
        return ResponseEntity.ok(this.companyPostService.savePost(companyPostDTO));
    }

    @DeleteMapping(path = "/{postId}")
    public ResponseEntity<Boolean> deletePost(@PathVariable(value = "postId") Long postId) {
        return ResponseEntity.ok(this.companyPostService.deletePost(postId));
    }
}
