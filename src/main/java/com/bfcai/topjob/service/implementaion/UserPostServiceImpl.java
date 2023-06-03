package com.bfcai.topjob.service.implementaion;

import com.bfcai.topjob.dto.UserPostDTO;
import com.bfcai.topjob.model.UserPost;
import com.bfcai.topjob.model.User;
import com.bfcai.topjob.repository.UserPostRepository;
import com.bfcai.topjob.service.UserPostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPostServiceImpl implements UserPostService {

    private final UserPostRepository userPostRepository;

    private final ModelMapper modelMapper;


    @Override
    public Boolean savePost(UserPostDTO userPostDTO) {
        try {
            User user = new User();
            user.setId(userPostDTO.getUserId());
            UserPost post = this.modelMapper.map(userPostDTO, UserPost.class);
            post.setUser(user);
            this.userPostRepository.save(post);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deletePost(Long postId) {
        try {
            this.userPostRepository.deleteById(postId);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
