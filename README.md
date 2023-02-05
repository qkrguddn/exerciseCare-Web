
<br> 

# 개발 기간 및 환경
+ ### 개발 기간: 2022.7.3 ~ 2022.8.20
+ ### LANGAUGE : Java
+ ### Tools : Intelij
+ ### 기술 : SpringBoot, JPA, Mysql, React
---------------------------------
<br> 
팀원<br><br>
fe: 김영덕, 진가영<br><br>
be: 박형우, 하정수<br><br>

---------------------------------
# working
#### 1. Rest API를 제공. 클라이언트 서버간 통신을 하는 Web App 제작 <br><br>
#### 2. 스프링개발의 디자인 패턴을 준수 <br>
+ #### Controller, Service, Repository 계층 분할
+ #### DTO 를 통해 개발의 확장성을 유연하게 함
+ #### exception handler 를 통한 예외처리
+ #### JUnit4 테스트코드 작성 <br><br>
#### 3. JPA 사용. 객체 지향적 프로그래밍을 통한 DB 구축 <br><br>

<br><br>
# 기능
### [회원가입](https://user-images.githubusercontent.com/85045177/216799833-a2a5ec8c-3610-4a6d-a84b-0a77c4fddaec.gif)
### [로그인 & 로그아웃](https://user-images.githubusercontent.com/85045177/216799848-122753be-5051-48f8-a4e8-937b766f8ce0.gif)
### [게시판 작성](https://user-images.githubusercontent.com/85045177/216799860-e76361de-4e6a-4723-a9c9-44b95d589c2a.gif)
### [댓글 작성](https://user-images.githubusercontent.com/85045177/216799871-c4602b29-e379-4919-abbb-8d1ea1e2db9a.gif)
### [게시글삭제](https://user-images.githubusercontent.com/85045177/216799892-c09c96b4-304b-432b-b07f-5fb24191c82d.gif)
### [운동일지기록](https://user-images.githubusercontent.com/85045177/216799711-15c01496-afb0-44d8-99a6-92a5993245aa.gif)
---------------------------------
<br><br>
# rest api
### ex) Board (게시판) <br> 
생성  
> POST /board <br>

조회 
> GET /board <br>
  GET /board/{boardId} <br>
  
수정
> PATCH /board/{boardId} <br> 

삭제 
> DELETE /board/{boardId} <br> 

#### 위와 같은 방식으로 댓글, 유저, 운동일지에도 CRUD기능을 가진 API 를 제공했습니다.


<br><br>
## ERD Diagram
![healthProject (1)](https://user-images.githubusercontent.com/85045177/186178101-06fecd08-eed2-4867-bed4-2d861c06c32d.png) <br>
#### 테이블은 User, Board, Comment, ExerciseLog 총 네 개입니다.
