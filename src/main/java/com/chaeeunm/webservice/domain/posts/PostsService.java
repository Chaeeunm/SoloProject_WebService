package com.chaeeunm.webservice.domain.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    public Long save(PostsDto.PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    public Long update(Long id, PostsDto.PostsUpdateRequestDto requestDto) {
        Posts verifiedPosts = VerifyPosts(id);
        verifiedPosts.update(requestDto.getTitle(), requestDto.getContent());

        return postsRepository.save(verifiedPosts).getId(); //더티체킹으로 인해 update 쿼리를 날릴 필요가 없다!
    }

    public PostsDto.PostsResponsestDto findById(Long id) {
        Posts verifidePosts = VerifyPosts(id);
        return new PostsDto.PostsResponsestDto(verifidePosts);
    }

    private Posts VerifyPosts(Long id) {
        return postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다 id = " + id));
    }
}
