# BackEnd <br>
### HealthCare 웹서비스의 백엔드 파트입니다.
---------------------------------
<br> 

# 개발 기간 및 환경
+ ### 개발 기간: 2022.7.3 ~ 2022.8.20
+ ### LANGAUGE : Java
+ ### Tools : Intelij
+ ### 기술 : SpringBoot, JPA, Mysql
---------------------------------
<br> 

# working
#### 1. Rest API를 제공. 클라이언트 서버간 통신을 하는 Web App 제작 <br><br>
#### 2. 스프링개발의 디자인 패턴을 준수 <br>
+ #### Controller, Service, Repository 계층 분할
+ #### DTO 를 통해 개발의 확장성을 유연하게 함
+ #### exception handler 를 통한 예외처리
+ #### JUnit4 테스트코드 작성 <br><br>
#### 3. JPA 사용. 객체 지향적 프로그래밍을 통한 DB 구축 <br><br>
## 배포
#### Docker를 사용해 AWS에 배포하였습니다. <br><br>
### 실행 방법
#### 도커 데스크탑으로 실행: <br>
#### 사이트로 접속: <br>
---------------------------------
<br><br>
# 기능
### 유저 회원가입.
### 로그인 & 로그아웃
### 게시판 작성. 해당 게시판에 댓글 작성 기능
### 달력에 원하는 날짜를 클릭해 사용자가 운동을 기록하는 기능
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
