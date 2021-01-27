package com.yongyeol.Springboot.service.posts;

import com.yongyeol.Springboot.domain.posts.Posts;
import com.yongyeol.Springboot.domain.posts.PostsRepository;
import com.yongyeol.Springboot.web.dto.PostsResponseDto;
import com.yongyeol.Springboot.web.dto.PostsSaveRequestDto;
import com.yongyeol.Springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private  final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 개시글이 없습니다. id = "+ id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 업습니다. id = "+ id));

        return new PostsResponseDto(entity);
    }
}
