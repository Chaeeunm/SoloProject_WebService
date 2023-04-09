package com.chaeeunm.webservice.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class PostsDto {
    @Getter
    @NoArgsConstructor
    public static class PostsSaveRequestDto {
        private String title;
        private String content;
        private String author;

        @Builder
        public PostsSaveRequestDto(String title, String content, String author) {
            this.title = title;
            this.content = content;
            this.author = author;
        }

        public Posts toEntity() {
            return Posts.builder()
                    .title(title)
                    .content(content)
                    .author(author)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class PostsUpdateRequestDto {
        private String title;
        private String content;

        @Builder
        public PostsUpdateRequestDto(String title, String content) {
            this.title = title;
            this.content = content;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class PostsResponsestDto {
        private Long id;
        private String title;
        private String content;
        private String author;

        //posts객체를 넣으면 postResponseDto객체로 바꿔줌
        public PostsResponsestDto(Posts entity) {
            this.id = entity.getId();
            this.author = entity.getAuthor();
            this.content = entity.getContent();
            this.title = entity.getTitle();
        }
    }
}
