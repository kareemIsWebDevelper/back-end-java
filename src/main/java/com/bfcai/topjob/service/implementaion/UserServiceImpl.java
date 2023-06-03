package com.bfcai.topjob.service.implementaion;

import com.bfcai.topjob.dto.*;
import com.bfcai.topjob.exception.NotFoundException;
import com.bfcai.topjob.model.CompanyPost;
import com.bfcai.topjob.model.Job;
import com.bfcai.topjob.model.UserPost;
import com.bfcai.topjob.model.User;
import com.bfcai.topjob.repository.CompanyRepository;
import com.bfcai.topjob.repository.UserRepository;
import com.bfcai.topjob.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final CompanyRepository companyRepository;

    private final ModelMapper modelMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CompanyRepository companyRepository, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Set<UserResponseDTO> getAllUsers() {
        return this.userRepository.fetchAllUsers();
    }

    @Override
    public UserResponseDTO getUserById(Long userId) {
        return this.modelMapper
                .map(this.userRepository.findById(userId)
                        .orElseThrow(() -> new NotFoundException("No user exist with id : " + userId)), UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO saveUser(UserSaveDTO userSaveDTO) {
        userSaveDTO.setPassword(this.passwordEncoder.encode(userSaveDTO.getPassword()));
        return this.modelMapper.map(this.userRepository.save(this.modelMapper.map(userSaveDTO, User.class)), UserResponseDTO.class);
    }

    @Override
    public Long userValidateLogin(LoginDTO loginDTO) {
        User user = this.userRepository.findUserByEmail(loginDTO.getEmail()).orElse(null);
        if (user == null) {
            return -1L;
        }
        return this.passwordEncoder.matches(loginDTO.getPassword(), user.getPassword()) ? user.getId() : -1L;
    }

    @Override
    public Boolean deleteUserById(Long userId) {
        User user = this.userRepository.findById(userId).orElse(null);
        if (user == null) {
            return false;
        }
        this.userRepository.delete(user);
        return true;
    }

    @Override
    @Transactional
    public Boolean updateUser(Long userId, UserSaveDTO userSaveDTO) {
        try {
            User user = this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("No user exist with id : " + userId));

            user.setFirstName(userSaveDTO.getFirstName());
            user.setLastName(userSaveDTO.getLastName());
            user.setBirthDate(userSaveDTO.getBirthDate());
            user.setPhone(userSaveDTO.getPhone());
            user.setJobTitle(userSaveDTO.getJobTitle());
            user.setCountry(userSaveDTO.getCountry());
            user.setCity(userSaveDTO.getCity());
            user.setEmail(userSaveDTO.getEmail());
            user.setPassword(this.passwordEncoder.encode(userSaveDTO.getPassword()));
            user.setAbout(userSaveDTO.getAbout());
            user.setProfilePicturePath(userSaveDTO.getProfilePicturePath());

            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean updateUserAbout(Long userId, String about) {
        try {
            User user = this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("No user exist with id : " + userId));
            user.setAbout(about);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public Set<UserResponseDTO> getAllUsersByName(String searchKey) {
        String[] searchWords = searchKey.split(" ");
        if (searchWords.length == 1) {
            return this.userRepository.fetchAllUsersByName(searchKey)
                    .stream()
                    .map(user -> this.modelMapper.map(user, UserResponseDTO.class))
                    .collect(Collectors.toSet());
        } else {
            return this.userRepository.fetchAllUsersByNameOptions(searchWords[0], searchWords[searchWords.length - 1])
                    .stream()
                    .map(user -> this.modelMapper.map(user, UserResponseDTO.class))
                    .collect(Collectors.toSet());
        }
    }

    @Override
    @Transactional
    public Boolean userFollow(Long userId, Long followedUserId) {
        try {
            User user = this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("No user exist with id : " + userId));
            User followedUser = this.userRepository.findById(followedUserId).orElseThrow(() -> new NotFoundException("No user exist with id : " + followedUserId));
            user.getFollowingUsers().add(followedUser);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public Boolean isUserFollow(Long userId, Long followedUserId) {
        User user = this.userRepository.checkUserFollow(userId, followedUserId).orElse(null);
        return user != null;
    }

    @Override
    @Transactional
    public Boolean userUnfollow(Long userId, Long unfollowedUserId) {
        try {
            User user = this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("No user exist with id : " + userId));
            User unfollowedUser = this.userRepository.findById(unfollowedUserId).orElseThrow(() -> new NotFoundException("No user exist with id : " + unfollowedUserId));
            user.getFollowingUsers().remove(unfollowedUser);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public Set<UserResponseDTO> getUserFollowers(Long userId) {
        return this.userRepository.fetchUserWithFollowedUsers(userId)
                .getFollowedUsers()
                .stream()
                .map(user -> this.modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<UserResponseDTO> getUserFollowingUsers(Long userId) {
        return this.userRepository
                .fetchUserWithFollowingUsers(userId)
                .getFollowingUsers()
                .stream()
                .map(user -> this.modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<CompanyResponseDTO> getUserFollowingCompanies(Long userId) {
        return this.userRepository
                .fetchUserWithFollowingCompanies(userId)
                .getFollowingCompanies()
                .stream()
                .map(company -> this.modelMapper.map(company, CompanyResponseDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public List<Long> getUserAppliedJobsIds(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("No user exist with id : " + userId))
                .getJobs()
                .stream()
                .map(Job::getId)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserPost> fetchUserPosts(Long userId) {
        return this.userRepository.fetchUserPosts(userId).stream()
                .sorted(Comparator.comparing(UserPost::getDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TimelinePackage fetchUserTimeline(Long userId) {
        User user = this.userRepository.getById(userId);
        TimelinePackage timelinePackage = new TimelinePackage();

        user.getFollowingCompanies()
                .forEach(company -> timelinePackage.getCompanyPosts().addAll(company.getPosts()));
        user.getFollowingUsers()
                .forEach(userValue -> timelinePackage.getUserPosts().addAll(userValue.getPosts()));

        timelinePackage.setCompanyPosts(timelinePackage.getCompanyPosts().stream()
                .sorted(Comparator.comparing(CompanyPost::getDate).reversed())
                .collect(Collectors.toList()));

        timelinePackage.setUserPosts(timelinePackage.getUserPosts().stream()
                .sorted(Comparator.comparing(UserPost::getDate).reversed())
                .collect(Collectors.toList()));
        return timelinePackage;
    }
}
