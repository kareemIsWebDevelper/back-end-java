package com.bfcai.topjob.service;

import com.bfcai.topjob.dto.*;
import com.bfcai.topjob.model.CompanyPost;
import com.bfcai.topjob.model.Job;

import java.util.List;
import java.util.Set;

public interface CompanyService {
    Long companyValidateLogin(LoginDTO loginDTO);

    Set<CompanyResponseDTO> getAllCompanies();

    CompanyResponseDTO getCompanyById(Long companyId);

    CompanyResponseDTO saveCompany(CompanySaveDTO companySaveDTO);

    Boolean deleteCompanyById(Long companyId);

    Boolean updateCompany(Long companyId, CompanySaveDTO companySaveDTO);

    Set<CompanyResponseDTO> getAllCompaniesByName(String searchKey);

    Boolean companyFollow(Long userId, Long followedCompanyId);

    Boolean isCompanyFollow(Long userId, Long followedCompanyId);

    Boolean companyUnfollow(Long userId, Long unfollowedCompanyId);

    Set<UserResponseDTO> getCompanyFollowers(Long companyId);

    Set<Job> fetchCompanyJobs(Long companyId);

    List<CompanyPost> fetchCompanyPosts(Long companyId);

    TimelinePackage fetchCompanyTimeline(Long companyId);
}
