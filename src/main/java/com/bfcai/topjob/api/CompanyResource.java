package com.bfcai.topjob.api;

import com.bfcai.topjob.dto.*;
import com.bfcai.topjob.model.CompanyPost;
import com.bfcai.topjob.model.Job;
import com.bfcai.topjob.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/companies")
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyResource {

    private final CompanyService companyService;

    @Autowired
    public CompanyResource(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Long> companyLogin(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(this.companyService.companyValidateLogin(loginDTO));
    }

    @GetMapping
    public ResponseEntity<Set<CompanyResponseDTO>> getAllCompanies() {
        return ResponseEntity.ok(this.companyService.getAllCompanies());
    }

    @GetMapping(path = "/{companyId}")
    public ResponseEntity<CompanyResponseDTO> getCompanyById(@PathVariable(value = "companyId") Long companyId) {
        return ResponseEntity.ok(this.companyService.getCompanyById(companyId));
    }

    @GetMapping(path = "/search/{searchKey}")
    public ResponseEntity<Set<CompanyResponseDTO>> getAllCompaniesByName(@PathVariable(value = "searchKey") String searchKey) {
        return ResponseEntity.ok(this.companyService.getAllCompaniesByName(searchKey));
    }

    @GetMapping(path = "/{userId}/following/{followedCompanyId}")
    public ResponseEntity<Boolean> companyFollow(@PathVariable(value = "userId") Long userId, @PathVariable(value = "followedCompanyId") Long followedCompanyId) {
        return ResponseEntity.ok(this.companyService.companyFollow(userId, followedCompanyId));
    }

    @GetMapping(path = "/{userId}/unfollowing/{unfollowedCompanyId}")
    public ResponseEntity<Boolean> companyUnfollow(@PathVariable(value = "userId") Long userId, @PathVariable(value = "unfollowedCompanyId") Long unfollowedCompanyId) {
        return ResponseEntity.ok(this.companyService.companyUnfollow(userId, unfollowedCompanyId));
    }

    @GetMapping(path = "/{userId}/isFollow/{followedCompanyId}")
    public ResponseEntity<Boolean> isCompanyFollow(@PathVariable(value = "userId") Long userId, @PathVariable(value = "followedCompanyId") Long followedCompanyId) {
        return ResponseEntity.ok(this.companyService.isCompanyFollow(userId, followedCompanyId));
    }

    @GetMapping(path = "/{companyId}/followers")
    public ResponseEntity<Set<UserResponseDTO>> fetchCompanyFollowers(@PathVariable(value = "companyId") Long companyId) {
        return ResponseEntity.ok(this.companyService.getCompanyFollowers(companyId));
    }

    @GetMapping(path = "/{companyId}/jobs")
    public ResponseEntity<Set<Job>> fetchCompanyJobs(@PathVariable(value = "companyId") Long companyId) {
        return ResponseEntity.ok(this.companyService.fetchCompanyJobs(companyId));
    }

    @PostMapping
    public ResponseEntity<CompanyResponseDTO> saveCompany(@RequestBody CompanySaveDTO companySaveDTO) {
        CompanyResponseDTO savedCompany = this.companyService.saveCompany(companySaveDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{companyId}").buildAndExpand(savedCompany.getId()).toUri();
        return ResponseEntity.created(location).body(savedCompany);
    }

    @DeleteMapping(path = "/{companyId}")
    public ResponseEntity<Boolean> deleteCompanyById(@PathVariable(value = "companyId") Long companyId) {
        return ResponseEntity.ok(this.companyService.deleteCompanyById(companyId));
    }


    @PutMapping(path = "/{companyId}")
    public ResponseEntity<Boolean> updateCompany(@PathVariable(value = "companyId") Long companyId, @RequestBody CompanySaveDTO companySaveDTO) {
        return ResponseEntity.ok(this.companyService.updateCompany(companyId, companySaveDTO));
    }

    @GetMapping(path = "/{companyId}/posts")
    public ResponseEntity<List<CompanyPost>> fetchCompanyPosts(@PathVariable(value = "companyId") Long companyId){
        return ResponseEntity.ok(this.companyService.fetchCompanyPosts(companyId));
    }

    @GetMapping(path = "/{companyId}/timeline")
    public ResponseEntity<TimelinePackage> fetchCompanyTimeline(@PathVariable(value = "companyId") Long companyId){
        return ResponseEntity.ok(this.companyService.fetchCompanyTimeline(companyId));
    }
}
