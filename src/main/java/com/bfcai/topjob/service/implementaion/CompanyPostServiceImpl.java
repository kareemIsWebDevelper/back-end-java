package com.bfcai.topjob.service.implementaion;

import com.bfcai.topjob.dto.CompanyPostDTO;
import com.bfcai.topjob.model.Company;
import com.bfcai.topjob.model.CompanyPost;
import com.bfcai.topjob.repository.CompanyPostRepository;
import com.bfcai.topjob.service.CompanyPostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyPostServiceImpl implements CompanyPostService {

    private final CompanyPostRepository companyPostRepository;

    private final ModelMapper modelMapper;

    @Override
    public Boolean savePost(CompanyPostDTO companyPostDTO) {
        try {
            Company company = new Company();
            company.setId(companyPostDTO.getCompanyId());
            CompanyPost post = this.modelMapper.map(companyPostDTO, CompanyPost.class);
            post.setCompany(company);
            this.companyPostRepository.save(post);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deletePost(Long postId) {
        try {
            this.companyPostRepository.deleteById(postId);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
