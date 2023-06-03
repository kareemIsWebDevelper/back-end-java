package com.bfcai.topjob.service.implementaion;

import com.bfcai.topjob.dto.*;
import com.bfcai.topjob.exception.NotFoundException;
import com.bfcai.topjob.model.Company;
import com.bfcai.topjob.model.CompanyPost;
import com.bfcai.topjob.model.Job;
import com.bfcai.topjob.model.User;
import com.bfcai.topjob.repository.CompanyRepository;
import com.bfcai.topjob.repository.UserRepository;
import com.bfcai.topjob.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Long companyValidateLogin(LoginDTO loginDTO) {
        Company company = this.companyRepository.findCompanyByEmail(loginDTO.getEmail()).orElse(null);
        if (company == null) {
            return -1L;
        }
        return this.passwordEncoder.matches(loginDTO.getPassword(), company.getPassword()) ? company.getId() : -1L;
    }

    @Override
    public Set<CompanyResponseDTO> getAllCompanies() {
        return this.companyRepository.fetchAllCompanies();
    }

    @Override
    public CompanyResponseDTO getCompanyById(Long companyId) {
        return this.modelMapper
                .map(this.companyRepository.findById(companyId).orElseThrow(() -> new NotFoundException("No company found with id : " + companyId)),
                        CompanyResponseDTO.class);
    }

    @Override
    public CompanyResponseDTO saveCompany(CompanySaveDTO companySaveDTO) {
        companySaveDTO.setPassword(this.passwordEncoder.encode(companySaveDTO.getPassword()));
        return this.modelMapper.map(this.companyRepository.save(this.modelMapper.map(companySaveDTO, Company.class)), CompanyResponseDTO.class);
    }

    @Override
    public Boolean deleteCompanyById(Long companyId) {
        Company company = this.companyRepository.findById(companyId).orElse(null);
        if (company == null) {
            return false;
        }
        this.companyRepository.deleteById(companyId);
        return true;
    }

    @Override
    @Transactional
    public Boolean updateCompany(Long companyId, CompanySaveDTO companySaveDTO) {
        try {
            Company company = this.companyRepository.findById(companyId).orElseThrow(() -> new NotFoundException("No company exist with id : " + companyId));
            company.setName(companySaveDTO.getName());
            company.setBio(companySaveDTO.getBio());
            company.setIndustry(companySaveDTO.getIndustry());
            company.setEmail(companySaveDTO.getEmail());
            company.setPassword(this.passwordEncoder.encode(companySaveDTO.getPassword()));
            company.setPhone(companySaveDTO.getPhone());
            company.setWebsite(companySaveDTO.getWebsite());
            company.setAbout(companySaveDTO.getAbout());
            company.setFoundedDate(companySaveDTO.getFoundedDate());
            company.setCountry(companySaveDTO.getCountry());
            company.setCity(companySaveDTO.getCity());
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public Set<CompanyResponseDTO> getAllCompaniesByName(String searchKey) {
        return this.companyRepository.fetchAllCompaniesByName(searchKey).stream().map(company -> this.modelMapper.map(company, CompanyResponseDTO.class)).collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public Boolean companyFollow(Long userId, Long followedCompanyId) {
        try {
            User user = this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("No user exists with id : " + userId));
            Company company = this.companyRepository.findById(followedCompanyId).orElseThrow(() -> new NotFoundException("No company exists with id : " + followedCompanyId));
            company.getFollowers().add(user);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public Boolean isCompanyFollow(Long userId, Long followedCompanyId) {
        try {
            User user = this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("No user exist with id : " + userId));
            for (Company company : user.getFollowingCompanies()) {
                if (Objects.equals(company.getId(), followedCompanyId)) {
                    return true;
                }
            }
        } catch (Exception exception) {
            return false;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean companyUnfollow(Long userId, Long unfollowedCompanyId) {
        try {
            User user = this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("No user exist with id : " + userId));
            Company unfollowedCompany = this.companyRepository.findById(unfollowedCompanyId).orElseThrow(() -> new NotFoundException("No company exist with id : " + unfollowedCompanyId));
            user.getFollowingCompanies().remove(unfollowedCompany);
            unfollowedCompany.getFollowers().remove(user);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public Set<UserResponseDTO> getCompanyFollowers(Long companyId) {
        return this.companyRepository.findById(companyId).orElseThrow(() -> new NotFoundException("No company exist with id : " + companyId))
                .getFollowers()
                .stream().map(user -> this.modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Job> fetchCompanyJobs(Long companyId) {
        return this.companyRepository.fetchCompanyWithJobs(companyId).getJobs();
    }

    @Override
    public List<CompanyPost> fetchCompanyPosts(Long companyId) {
        return this.companyRepository.fetchCompanyPosts(companyId).stream()
                .sorted(Comparator.comparing(CompanyPost::getDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public TimelinePackage fetchCompanyTimeline(Long companyId) {
        TimelinePackage timelinePackage =new TimelinePackage();
        timelinePackage.setUserPosts(this.userRepository.fetchUsersPosts());
        timelinePackage.setCompanyPosts(this.companyRepository.fetchOtherCompaniesPosts(companyId));
        return timelinePackage;
    }
}
