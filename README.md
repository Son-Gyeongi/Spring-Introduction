## Spring_RoadMap_Lecture
[인프런] 스프링 백엔드 로드맵 강의 정리(with.김영한)

# 스프링 입문 - 코드로 배우는 스프링

## 프로젝트 환경설정
### - 프로젝트 생성
> **사전 준비**   
* JAVA11 설치   
* IDE : IntelliJ 설치

> **프로젝트 선택**   
* Project: Gradle - Groovy Project   
* Spring Boot: 2.3.x   
* Language: Java   
* Packaging: Jar   
* Java: 11   
* Project Metadata
 - groupId: hello
 - artifactId: hello-spring
* Dependencies: Spring Web, Thymeleaf

#### 참고 [build.gradle](https://github.com/Son-Gyeongi/Spring_RoadMap_Lecture/blob/master/build.gradle)
***
> **동작 확인**   
1. [기본 메인 클래스](https://github.com/Son-Gyeongi/Spring_RoadMap_Lecture/blob/master/src/main/java/hello/hellospring/HelloSpringApplication.java)실행
2. 스프링 부트 메인 실행 후 localhost:8080에 들어가서 에러페이지가 나오면 잘 작동된 것이다.

> **IntelliJ Gradle 대신 자바로 직접 실행**   
gradle을 통해서 실행하면 실행속도가 느립니다. 그래서 설정을 IntelliJ IDEA로 바꿔줍니다.
* ios 사용자 : Preferences -> Build, Execution, Deployment -> Build Tools -> Gradle
 - Build and run using: Gradle -> IntelliJ IDEA
 - Run tests using: Gradle -> IntelliJ IDEA
* windows 사용자 : File -> Setting -> Gradle

> **IntelliJ JDK 설치 확인**  
모든 설정이 JDK11로 맞춰져있는지 확인하기
* File -> Setting -> Gradle
* File -> Project Structure

### - 라이브러리 살펴보기
Gradle은 의존관계가 있는 라이브러리를 다운로드합니다.   
<br>
**스프링 부트 라이브러리**
* spring-boot-starter-web
 - spring-boot-starter-tomcat: 톰캣(웹서버)
 - spring-webmvc: 스프링 웹 MVC
* spring-boot-starter-thymeleaf: 타임리프 템플릿 엔진(View)
* spring-boot-starter(공통): 스프링 부트 + 스프링 코어 + 로깅
 - spring-boot
  + spring-core
 - spring-boot-starter-logging
  + logback, slf4j
  
**테스트 라이브러리**
* spring-boot-starter-test
 - junit: 테스트 프레임워크
 - mockito: 목 라이브러리
 - assertj: 테스트 코드를 좀 더 편하게 작성할 수 있게 도와주는 라이브러리
 - spring-test: 스프링 통합 테스트 지원
 
### - View 환경설정
### - 빌드하고 실행하기

## 스프링 웹 개발 기초
### - 정적 컨텐츠
### - MVC와 템플릿 엔진
### - API
### 회원 관리 예제 - 백엔드 개발
### - 비즈니스 요구사항 정리
### - 회원 도메인과 리포지토리 만들기
### - 회원 리포지토리 테스트 케이스 작성
### - 회원 서비스 개발
### - 회원 서비스 테스트

## 스프링 빈과 의존관계
### - 컴포넌트 스캔과 자동 의존관계 설정
### - 자바 코드로 직접 스프링 빈 등록하기

## 회원 관리 예제 - 웹 MVC 개발
### - 회원 웹 기능 - 홈 화면 추가
### - 회원 웹 기능 - 등록
### - 회원 웹 기능 - 조회

## 스프링 DB 접근 기술
### - H2 데이터베이스 설치
### - 순수 Jdbc
### - 스프링 통합 테스트
### - 스프링 JdbcTemplate
### - JPA
### - 스프링 데이터 JPA

## AOP
### - AOP가 필요한 상황
### - AOP 적용

## 다음으로
