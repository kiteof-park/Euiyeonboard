package com.example.euiyeonboard.domain;

import jakarta.persistence.*;;
import lombok.*;


@AllArgsConstructor     // access = AccessLevel.?
@NoArgsConstructor      // access = AccessLevel.?
@Builder                // @Builder는 모든 필드를 초기화할 수 있는 생성자를 기반으로 동작 -> @AllArgsCosntructor가 필요
@Entity
@Table(name = "post")
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;
}
