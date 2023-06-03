package com.bfcai.topjob.repository;

import com.bfcai.topjob.dto.CompanyResponseDTO;
import com.bfcai.topjob.dto.JobDTO;
import com.bfcai.topjob.model.Company;
import com.bfcai.topjob.model.CompanyPost;
import com.bfcai.topjob.model.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findCompanyByEmail(String email);

    @Query(value = "select distinct new com.bfcai.topjob.dto.CompanyResponseDTO(c.id,c.name,c.phone,c.industry,c.bio,c.about,c.website,c.email,c.foundedDate,c.country,c.city) from Company c")
    Set<CompanyResponseDTO> fetchAllCompanies();

    @Query(value = "select distinct c from Company c where c.name like %:searchKey%")
    Set<Company> fetchAllCompaniesByName(@Param(value = "searchKey") String searchKey);

    @Query(value = "select distinct c from Company c left join fetch c.jobs j left join fetch j.applicants where c.id=:companyId")
    Company fetchCompanyWithJobs(@Param(value = "companyId") Long companyId);


    @Query(value = "select distinct c.posts from Company c where c.id=:companyId")
    Set<CompanyPost> fetchCompanyPosts(@Param(value = "companyId") Long companyId);

    @Query(value = "select distinct p from CompanyPost p where p.company.id <> :companyId")
    List<CompanyPost> fetchOtherCompaniesPosts(@Param(value = "companyId") Long companyId);
}
