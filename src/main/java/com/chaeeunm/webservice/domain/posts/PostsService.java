package com.chaeeunm.webservice.domain.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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

    public PostsDto.PostsResponseDto findById(Long id) {
        Posts verifidePosts = VerifyPosts(id);
        return new PostsDto.PostsResponseDto(verifidePosts);
    }

    @Transactional
    public void delete(Long id){
        Posts posts = VerifyPosts(id);
        postsRepository.delete(posts);
    }

    @Transactional//(readOnly = true) //readOnly=turn를 주면 트랜젝션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선! 등록,수정,삭제 기능이 없는 서비스 메소드에서 사용하면 좋다
    public List<PostsDto.PostListResponseDto> findAllDesc(){
        return postsRepository.findAllDes().stream()
                .map(PostsDto.PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    private Posts VerifyPosts(Long id) {
        return postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다 id = " + id));
    }
}
