## 💡 생각해볼 거리

### `@Builder` vs `new`
- 스프링에서 `new` 를 지양한다고 하지만 `@Builder`를 사용해서
```java
.title()
.content()
.build();
```
이런식으로 코드가 길어지는 것도 별로다!

### `@Builder` 대신 `new`를 사용한 코드 리팩토링

📌 기존 코드  
📂 `PostService.java`
```java
    // 게시글 작성
public PostResponse savePost(PostRequest postRequest){
    Post post = Post.builder()
            .title(postRequest.title())
            .content(postRequest.content())
            .build();

    Post savedPost = postRepository.save(post);

    return PostResponse.builder()
            .id(savedPost.getId())
            .title(savedPost.getTitle())
            .content(savedPost.getContent())
            .build();
}
```

📌 리팩토링한 코드  
📂 `PostService.java`
```java
    // 게시글 작성
public PostResponse savePost(PostCreateRequest postCreateRequest){

    Post post = new Post(postCreateRequest);
    Post savedPost = postRepository.save(post);
    
    return new PostResponse(savedPost);
}
```

📂 `Post.java`
- `PostCreatRequest`를 받아서 `Post`를 생성하도록 생성자 오버로딩

```java
    // 생성자 오버로딩
    public Post(PostCreateRequest postCreateRequest) {
        this.title = postCreateRequest.title();
        this.content = postCreateRequest.content();
    }
```

📂 `PostResponse.java`
- `Post`를 받아서 `PostResponse`를 생성하도록 생성자 오버로딩
```java
    // 생성자 오버로딩
    public PostResponse(Post post){
        this(post.getId(), post.getTitle(), post.getContent());
    }

```

### 그래서 뭐가 더 나음?
- 둘 중 어느게 더 좋은 코드인진 잘 모르겠다!
- 이걸 구분하는게 앞으로 해야되는 숙제겠지! 
