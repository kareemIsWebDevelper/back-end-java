package com.bfcai.topjob.repository;

import com.bfcai.topjob.model.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPostRepository extends JpaRepository<UserPost,Long> {
}
