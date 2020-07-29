# 코딩 테스트

# 사용 DB
1. H2 DB
   - 디비 초기 생성 (선행 필요)
     - 저장한 설정: Generic H2 (Server)
     - 설정 이름: Generic H2 (Server)
     - 드라이버 클래스: org.h2.Driver
     - JDBC URL (*): jdbc:h2:~/weddingbook
     - 사용자명: sa
     - 비밀번호: 
   - 생성 후 h2 디비 접속
     - 저장한 설정: Generic H2 (Server)
     - 설정 이름: Generic H2 (Server)
     - 드라이버 클래스: org.h2.Driver
     - JDBC URL (*): jdbc:h2:tcp://localhost/~/weddingbook
     - 사용자명: sa
     - 비밀번호: 
   
2. 게시판 (BOARD)
   - id (long) pk (id)
   - title (varchar) (제목)
   - content (text) (내용)
   - writer (varchar) (작성자)
   - password (varchar) (비밀번호)
   - DeleteStatus (varchar) (삭제 여부)
   - created_date (date) (생성일)
   - updated_date (date) (수정일)

# API 기능 명세

### 게시물 등록 API
#### Request
- URL
  ```http
  POST /board HTTP/1.1
  Host: localhost:8080
  Content-type: application/json
  ```
- Parameter
  
  | Name | Type | Description | Required |
  |---|---|---|---|
  | title | String | 제목 | O |
  | content | String | 내용 | O |
  | writer | String | 작성자 | O |
  | password | String | 비밀번호 | O |

#### Response
- Success (Status : 200)

  | Name | Type | Description |
  |---|---|---|
  | id | Long | pk |
  | title | String | 제목 |
  | content | String | 내용 |
  | writer | String | 작성자 |

- Fail (Status : 400)

  | Name | Type | Description |
  |---|---|---|
  | errorMessage | String | 첫 번째 에러 메시지 |

- Sample
  ```json
  {
      "title" : "제목!",
      "content" : "내용!",
      "writer" : "작성자!",
      "password" : "pasword123"
  }
  ```
  
---

### 게시물 조회 API
#### Request
- URL
  ```http
  GET /board HTTP/1.1
  Host: localhost:8080
  ```
- Parameter
  
  | Name | Type | Description | Required |
  |---|---|---|---|
  | page | int | 현재 페이지 (0부터 시작) | X |

#### Response
- Success (Status : 200)

  | Name | Type | Description |
  |---|---|---|
  | code | String | 상태 코드 |
  | message | String | 메시지 |
  | list | List | 내용 |
  | pagination | Pagination | 작성자 |

- Sample
  ```json
  {
      "code": "200",
      "message": "success",
      "list": [
          {
              "createdDate": "2020-07-28T22:04:22.234",
              "updatedDate": "2020-07-28T22:04:22.234",
              "id": 35,
              "title": "제목!",
              "content": "내용!",
              "writer": "작성자!"
          }
      ],
      "pagination": {
          "number": 3,
          "size": 10,
          "totalPages": 4,
          "numberOfElements": 1,
          "totalElements": 31,
          "hasPreviousPage": true,
          "firstPage": false,
          "nextPage": false,
          "lastPage": true,
          "sort": {
              "sorted": false,
              "unsorted": true,
              "empty": true
          },
          "startPage": 1,
          "endPage": 4
      }
  }
  ```
  
---

### 게시물 상세 API
#### Request
- URL
  ```http
  GET /board/{boardId} HTTP/1.1
  Host: localhost:8080
  ```
- Parameter
  
  | Name | Type | Description | Required |
  |---|---|---|---|
  |  |  |  |  |

#### Response
- Success (Status : 200)

  | Name | Type | Description |
  |---|---|---|
  | id | Long | pk |
  | title | String | 제목 |
  | content | String | 내용 |
  | writer | String | 작성자 |

- Fail 

  | Name | Type | Description |
  |---|---|---|
  |  |  |  |

---

### 게시물 비밀번호 일치 여부 API
#### Request
- URL
  ```http
  POST /board/{boardId} HTTP/1.1
  Host: localhost:8080
  Content-type: application/json
  ```
- Parameter
  
  | Name | Type | Description | Required |
  |---|---|---|---|
  | password | String | 비밀번호 | O |

#### Response
- Success (Status : 200)

  | Name | Type | Description |
  |---|---|---|
  | true | Boolean | 성공 시 true |

- Fail (Status : 401)

  | Name | Type | Description |
  |---|---|---|
  | false | Boolean | 실패 시 false |

- Sample
  ```json
  {
      "password" : "pasword123"
  }
  ```
  
---

### 게시물 수정 API
#### Request
- URL
  ```http
  PUT /board HTTP/1.1
  Host: localhost:8080
  Content-type: application/json
  ```
- Parameter
  
  | Name | Type | Description | Required |
  |---|---|---|---|
  | id | Long | pk | O |
  | title | String | 제목 | O |
  | content | String | 내용 | O |
  | writer | String | 작성자 | O |

#### Response
- Success (Status : 200)

  | Name | Type | Description |
  |---|---|---|
  | id | Long | pk |
  | title | String | 제목 |
  | content | String | 내용 |
  | writer | String | 작성자 |

- Sample
  ```json
  {
      "id" : 35,
      "title" : "제목!",
      "content" : "내용!",
      "writer" : "작성자!"
  }
  ```
  
--- 

### 게시물 삭제 API
#### Request
- URL
  ```http
  DELETE /board/{boardId} HTTP/1.1
  Host: localhost:8080
  ```
- Parameter
  
  | Name | Type | Description | Required |
  |---|---|---|---|
  |  |  |  |  |

#### Response
- Success (Status : 200)

  | Name | Type | Description |
  |---|---|---|
  |  |  |  |

- Fail 

  | Name | Type | Description |
  |---|---|---|
  |  |  |  |

  