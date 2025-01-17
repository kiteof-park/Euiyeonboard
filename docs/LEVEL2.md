## 학습 목표
- URL, Http Method를 활용한 기본 라우팅 방법 학습
- Request Body, Query Params, Path Params 사용해보기
  - `@RequestBody` : 요청 데이터(JSON)을 객체로 변환 _- Jackson 라이브러리 사용_
  - `@RequestParam`: URL 쿼리 문자열에 데이터를 전달 받을 때 사용
  - `@PathVariable`: URL 경로에 포함된 변수를 매핑
- ORM 사용
    - 엔티티 생성
    - 기본적인 CRUD 활용
- 요청부터 응답까지 전체 과정 경험
- REST API 설계
- HTTP Status Code (상태 코드)
- Postman 사용

## 요구사항
1. **게시글 작성 기능**
- [x] 게시글은 `제목`, `내용`을 포함
- [x] 게시글이 저장될 때, `id`(PK, primary key)도 같이 Auto-increment 형식으로 저장
- [x] 게시글 작성에 성공했을 때, 응답값으로 작성된 게시글에 대한 정보를 응답으로 반환

2. **게시글 전체 조회 기능**
- [x] 게시글을 조회할 때 `id`, `제목`, `내용`의 값이 포함

3. **특정 게시글 조회 기능**
- [x] 게시글의 `id`(PK, primary key)로 특정 게시글을 조회
- [x] 게시글을 조회할 때 `id`, `제목`, `내용`의 값이 포함

4. **특정 게시글 수정 기능**
- [x] 게시글의 `id`(PK, primary key)로 특정 게시글을 수정 가능하도록해야 한다.
- [x] 게시글의 `제목`, `내용`을 수정
- [x] 게시글 수정에 성공했을 때, 응답값으로 수정된 게시글에 대한 정보를 응답으로 반환

5. **특정 게시글 삭제 기능**
- [ ] 게시글의 `id`(PK, primary key)로 특정 게시글을 삭제할 수 있도록

## 제약 조건
1. REST API 설계에 맞게 API 생성
2. Http Status Code 규약에 맞게 응답 코드 설정
3. POSTMAN으로 만든 API에 대해 올바른 응답 테스트