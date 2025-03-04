### ✅학습 목표

- [x]  유효성 검사
- [x]  예외 처리

### ✅요구 사항

1. **게시글 작성 기능**
- [x]  게시글 `제목`, `내용`은 필수적으로 포함해야 한다.
- [x]  `제목`은 1글자 이상 15글자 이하여야 한다.
- [x]  `내용`은 1글자 이상 1000글자 이하여야 한다.
- [x]  `제목`은 공백으로만 이루어질 수는 없다.
- `@NotBlank`, `@NotEmpty`, `@NotNull` 차이 정리하기

2. **게시글 전체 조회 기능**
- [x]  (추가 요구사항 없음)

3. **특정 게시글 조회 기능**
- [x]  게시글의 `id`(PK, primary key)로 특정 게시글을 조회했을 때,  
존재하지 않는 게시글일 경우 에러 메시지로 응답하기
- 💡 `PostRepository`에서 이미 처리해둠!
```java
    default Post findOne(Long id){
        return findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다!"));
    }
```

4. **특정 게시글 수정 기능**
- [x]  게시글 작성할 때의 유효성 검사 조건과 동일하게 가져가야 한다.

5. **특정 게시글 삭제 기능**
- [x]  게시글의 `id`(PK, primary key)로 특정 게시글을 삭제하기 위해 조회했을 때,  
존재하지 않는 게시글일 경우 에러 메시지로 응답하기
- 💡 `PostRepository`에서 이미 처리해둠!
```java
    default Post findOne(Long id){
        return findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다!"));
    }
```

6. **게시글 검색 기능**
- [x]  `검색 키워드`는 공백을 제외한 1글자 이상이어야 한다.
- `@Vaildated` + `@NotBlank`를 이용해 유효성 검사