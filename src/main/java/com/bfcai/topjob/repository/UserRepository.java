package com.bfcai.topjob.repository;

import com.bfcai.topjob.dto.UserResponseDTO;
import com.bfcai.topjob.model.UserPost;
import com.bfcai.topjob.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select distinct new com.bfcai.topjob.dto.UserResponseDTO(u.id,u.firstName,u.lastName,u.birthDate,u.phone,u.jobTitle,u.country,u.city,u.email,u.about,u.profilePicturePath) from User u")
    Set<UserResponseDTO> fetchAllUsers();

    @Query(value = "select u from User u where u.email=:email")
    Optional<User> findUserByEmail(String email);

    @Query(value = "select u from User u left join fetch u.courses left join fetch u.educations left join fetch u.experiences " +
            "left join fetch u.projects left join fetch u.skills where u.id=:userId")
    Optional<User> fetchUserById(@Param(value = "userId") Long userId);

    @Query(value = "select u.id from User u where u.id=:userId")
    Optional<Long> findUserId(@Param(value = "userId") Long userId);


    @Query(value = "select distinct u from User u where u.firstName like %:searchKey% or u.lastName like %:searchKey%")
    Set<User> fetchAllUsersByName(@Param(value = "searchKey") String searchKey);

    @Query(value = "select distinct u from User u left join fetch u.followedUsers where u.id=:userId")
    User fetchUserWithFollowedUsers(@Param(value = "userId") Long userId);

    @Query(value = "select distinct u from User u left join fetch u.followingUsers where u.id=:userId")
    User fetchUserWithFollowingUsers(@Param(value = "userId") Long userId);

    @Query(value = "select distinct u from User u left join fetch u.followingCompanies where u.id=:userId")
    User fetchUserWithFollowingCompanies(@Param(value = "userId") Long userId);


    @Query(value = "select distinct u from User u inner join u.followingUsers fu where u.id=:userId and fu.id=:followingId")
    Optional<User> checkUserFollow(@Param(value = "userId") Long userId, @Param(value = "followingId") Long followingId);


    @Query(value = "select distinct u from User u " +
            "where u.firstName like %:first% or u.lastName like %:last% " +
            "or u.firstName like %:last% or u.lastName like %:first%")
    Set<User> fetchAllUsersByNameOptions(@Param(value = "first") String first,@Param(value = "last") String last);

    @Query(value = "select distinct u.posts from User u where u.id=:userId")
    Set<UserPost> fetchUserPosts(@Param(value = "userId") Long userId);

    @Query(value = "select distinct p from UserPost p")
    List<UserPost> fetchUsersPosts();
}
