package com.bfcai.topjob.repository;

import com.bfcai.topjob.model.CompanyPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyPostRepository extends JpaRepository<CompanyPost,Long> {
}
