package com.chaeeunm.webservice.web;

import com.chaeeunm.webservice.domain.posts.PostsDto;
import com.chaeeunm.webservice.domain.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/")
    public String index(Model model) { //Model객체 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있음
        //PostsService.findAllDesc로 가져온 결과를 posts로 index.mustache 에 전달함!
        model.addAttribute("posts",postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsDto.PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);

        return "posts-update";
    }
}
