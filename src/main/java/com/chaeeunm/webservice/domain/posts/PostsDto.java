package com.chaeeunm.webservice.domain.posts;

import lombok.*;

import java.time.LocalDateTime;

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
    public static class PostsResponseDto {
        private Long id;
        private String title;
        private String content;
        private String author;

        //posts객체를 넣으면 postResponseDto객체로 바꿔줌
        public PostsResponseDto(Posts entity) {
            this.id = entity.getId();
            this.author = entity.getAuthor();
            this.content = entity.getContent();
            this.title = entity.getTitle();
        }
    }

    @Getter
    public static class PostListResponseDto {
        private Long id;
        private String title;
        private String author;
        private LocalDateTime modifiedDate;

        public PostListResponseDto(Posts entity) {
            this.id = entity.getId();
            this.author = entity.getAuthor();
            this.title = entity.getTitle();
            this.modifiedDate = entity.getModifiedDate();
        }
    }
}
