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
-------------------
> **동작 확인**   
1. [기본 메인 클래스](https://github.com/Son-Gyeongi/Spring_RoadMap_Lecture/blob/master/src/main/java/hello/hellospring/HelloSpringApplication.java)실행
2. 스프링 부트 메인 실행 후 localhost:8080에 들어가서 에러페이지가 나오면 잘 작동된 것이다.

> **IntelliJ Gradle 대신 자바로 직접 실행**   
gradle을 통해서 실행하면 실행속도가 느립니다. 그래서 설정을 IntelliJ IDEA로 바꿔줍니다.
*  사용자 : Preferences -> Build, Execution, Deployment -> Build Tools -> Gradle
 - Build and run using: Gradle -> IntelliJ IDEA
 - Run tests using: Gradle -> IntelliJ IDEA
* windows 사용자 : File -> Setting -> Build, Execution, Deployment -> Build Tools -> Gradle
![이미지2](https://user-images.githubusercontent.com/78200199/228776595-0a282d7a-7cbe-4157-8ee1-e4a159e1ff08.jpg)

> **IntelliJ JDK 설치 확인**  
모든 설정이 JDK11로 맞춰져있는지 확인하기
1. Gradle JDK 설정 : File -> Setting -> Build, Execution, Deployment -> Build Tools -> Gradle
2. 프로젝트 JDK 설정 : File -> Project Structure -> Project Settings -> Project -> SDK   
![이미지4](https://user-images.githubusercontent.com/78200199/228777059-22ac2f53-ff0c-4c49-92a7-fc2e8fcad1a8.jpg)
![이미지3](https://user-images.githubusercontent.com/78200199/228774799-7f3ae2ee-a1a8-432d-bc69-c1768b2d5fa2.jpg)

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
-------------------
**이제 동작하고 프로그래밍 되는 화면 만들어보자**
### - View 환경설정
#### !! Welcome Page 만들기 !!
**정적 파일로 동작해보기**
 - static 폴더 아래에 index.html을 올려두면 Welcome page 기능을 제공한다.   
참고 : [static/index.html](https://github.com/Son-Gyeongi/Spring_RoadMap_Lecture/blob/master/src/main/resources/static/index.html)   
참고 : [스프링 공식문서 Welcome Page 만들기](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-welcome-page)

**thymeleaf 템플릿 엔진으로 동작해보기**
 - 웹 애플리케이션에서 첫 번째 진입점이 Controller이다. Controller 클래스 만들면 @Controller를 만들어줘야 한다.   
참고 : [templates/hello.html](https://github.com/Son-Gyeongi/Spring_RoadMap_Lecture/blob/master/src/main/resources/templates/hello.html)   
참고 : [HelloController에서 "hello" 찾아서 매핑](https://github.com/Son-Gyeongi/Spring_RoadMap_Lecture/blob/master/src/main/java/hello/hellospring/controller/HelloController.java)
 - thymeleaf 템플릿 엔진 동작 환경 그림   
 1. 웹 브라우저에서 localhost:8080/hello 입력
 2. 내장 톰캣 서버에서 hello와 매핑되는 Controller를 찾습니다. helloController에서 hello 메서드 찾음
 3. 그리고 반환 hello와 model(data:hello!!)
 4. 컨트롤러에서 리턴 값으로 문자를 반환하면 viewResolver가 화면을 찾아서 처리   
 스프링 부트 템플릿엔진 기본 viewName 매핑   
 resources: templates/ + {ViewName} + .html
 ![이미지6](https://user-images.githubusercontent.com/78200199/228788782-7db90bb5-3996-4ef4-9a6f-dd69e6733543.jpg)
  그림 출처 : 인프런 깅영한님 강의 [스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술]

> 참고 : [spring-boot-devtools]라이브러리를 추가하면, html파일을 컴파일만 해주면 서버 재시작 없이 View 파일 변경이 가능   
intelliJ 컴파일 방법 : 상단바메뉴 build -> Recompile
 
### - 빌드하고 실행하기
- 강의 자료처럼 cmd를 사용하려고 했는데 '강의 질문에 **linux 명령어**를 접해볼 수 있는 좋은 기회라서 gitBash를 활용하면 된다'고 알려주셔서 gitBash를 사용했다.   
   - 참고 : [windows환경에서 gitBash로 리눅스 명령어 사용해보기](https://www.inflearn.com/questions/53961/%EA%B0%95%EC%9D%98-%EC%8B%9C%EC%B2%AD-tip-%EC%9C%88%EB%8F%84%EC%9A%B0%EB%9D%BC%EC%84%9C-%EB%A7%A5%EC%9D%98-iterm%EC%9D%B4-%EC%97%86%EB%8A%94%EB%8D%B0-%EC%96%B4%EB%96%A1%ED%95%98%EB%82%98)   
   - *ls -arlth : 모든 파일, 폴더를 시간 역순으로 출력
   - *cd 해당폴더 : 해당폴더로 이동
   - *ls : 해당폴더에 있는 파일, 폴더를 출력
- 내가 만든 프로젝트 폴더에서 **깃배쉬 시작하기** tip   
   - 내가 만든 프로젝트 마우스 오른쪽 클릭하고 Git Bash Here을 누르면 된다.
   ![이미지7](https://user-images.githubusercontent.com/78200199/228801158-ec9a7031-0eae-41ab-8edc-7daeb25d11b6.jpg)
- windows 빌드하고 실행하기   
1. gradlew build 또는 gradlew.bat build 입력하기 (빌드가 됩니다)
2. 빌드가 완료 된 후 cd build/libs 입력해줍니다. (빌드가 된 파일이 저장된 곳)
3. ls 명령어를 입력하면 hello-spring-0.0.1-SNAPSHOT.jar 파일이 나옵니다.
4. jar파일 실행 java -jar hello-spring-0.0.1-SNAPSHOT.jar 입력해줍니다.
5. 스프링이 실행 됩니다.
![이미지8](https://user-images.githubusercontent.com/78200199/228803764-28367de8-be08-4bcd-8483-e361b053e285.jpg)

> 스프링 실행을 멈추고 싶다면 ctrl + c 를 눌러줍니다.   

> 서버 배포할 때 이 파일만 복사해서 서버에 넣어주고 java -jar 파일명으로 실행해주면 됩니다. 그러면 서버에서 스프링이 동작합니다.   

> *jar 파일이 잘 안된다면
> - windows
>    - gradlew.bat clean build 입력해줍니다.
>    - gradlew.bat clean만 입력해서 빌드 폴더를 지워주기만 해도 됩니다.
>    - clean은 빌드 폴더를 지워주고 build는 다시 프로젝트를 빌드 해줍니다.
> - 맥 기준
>    - ./gradlew clean build 빌드 폴더 삭제 후 다시 빌드

> 만약 cmd를 사용한다면
명령어   
dir : 폴더에 존재하는 파일, 폴더 모두 출력   
cd 해당폴더 : 해당폴더로 이동

## 스프링 웹 개발 기초
* 정적 컨텐츠
   - 파일을 그대로 클라이언트에게 전달합니다.
* MVC와 템플릿 엔진
   - Model, View(템플릿 엔진 화면), Controller 이 3가지를 MVC라고 합니다.
   - [JSP, PHP가 템플릿 엔진이다. html을 그냥 주는 게 아닌 서버에서 프로그래밍을 해서 html을 동적으로 바꿔서 내려줍니다.]
* API
   - html을 내려주는 게 아닌 **JSON 데이터 구조 포맷**을 내려줍니다.
   - JSON 형태로 클라이언트에게 데이터 전달합니다.
   - 사용 : 프론트엔드(Vue.js, React)와 통신할 때, 서버끼리 통신할 때(데이터 주고 받을 때) 
### - 정적 컨텐츠
**스프링부트는 정적 컨텐츠 기능 자동으로 제공합니다.**   
참고 : [스프링부트 공식문서](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-static-content)   
참고 : [static/hello-static.html](https://github.com/Son-Gyeongi/Spring_RoadMap_Lecture/blob/master/src/main/resources/static/hello-static.html)   
-> 실행 후 확인 : http://localhost:8080/hello-static.html 로 확인합니다. 화면에 '정적 컨텐츠 입니다.'가 나오면 성공!!   
 - 정적 컨텐츠 동작 환경 그림   
 1. 웹 브라우저에 localhost:8080/hello-static.html을 입력합니다.
 2. 내장 톰캣 서버가 요청을 받습니다.
 3. [그림에서 1] hello-static.html이 왔다고 spring에 넘겨줍니다.   
 spring은 먼저 controller에 hello-static이 있는지 찾습니다.   
 **controller가 먼저 우선순위를 가집니다.**   
 4. [그림에서 2] controller에 없다면 그 다음에 스프링부트가 내부에 있는 resources 안에 있는 static 폴더에 hello-static.html을 찾습니다. 있으면 반환해줍니다.   
 ![이미지9](https://user-images.githubusercontent.com/78200199/229110065-932a05b0-612e-42ad-9274-afad07675345.jpg)
그림 출처 : 인프런 깅영한님 강의 [스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술]

### - MVC와 템플릿 엔진
* MVC : Model, View, Controller
* 역할과 책임을 분리
   - View는 화면을 그리는데 모든 역량을 집중해야 합니다.
   - Controller나 Model과 관련된 부분은 비즈니스 로직과 관련있거나 뭔가 내부적인 처리하는데 집중해야 합니다.
* Controller는 서버 뒷단에 관련된 거 처리, Model에 관련된 화면에 필요한 걸 담아서 화면에 넘겨주는 패턴을 많이 사용합니다.   
참고 : [HelloController에서 "hello-mvc" 매핑](https://github.com/Son-Gyeongi/Spring_RoadMap_Lecture/blob/master/src/main/java/hello/hellospring/controller/HelloController.java)   
   - @RequestParam() : ()괄호 안 옵션 required클라이언트로부터 받는 값이다. 기본적으로 true여서 꼭 받아야하는 값입니다. false로 해주면 꼭 값을 안 받아도 됩니다.
   - HTTP메서드 GET방식에서 url?키=값&&키=값 으로 파라미터 넘길 수 있습니다.
참고 : [templates/hello-template.html](https://github.com/Son-Gyeongi/Spring_RoadMap_Lecture/blob/master/src/main/resources/templates/hello-template.html)   
-> 실행 후 확인 : http://localhost:8080/hello-mvc?name=spring 로 확인합니다. 화면에 'hello spring'이 나오면 성공!!  
   - 타임리프 장점 - html을 그대로 쓰고 그 파일을 서버없이 열어도 껍데기를 볼 수 있습니다.
   
 - MVC, 템플릿 엔진 동작 환경 그림   
 1. 웹 브라우저에 localhost:8080/hello-mvc?name=spring!을 입력합니다.
 2. 내장 톰캣 서버가 요청을 받습니다.
 3. hello-mvc가 왔다고 spring에 넘겨줍니다.   
 HelloController에 hello-mvc가 메서드와 매핑이 되어있는 걸 발견하고 그 메서드를 호출합니다. 
 **controller가 먼저 우선순위를 가집니다.**   
 4. return은 hello-template으로 하고 model에는 키는 name이고 값은 spring으로 spring에 넘깁니다.   
 그러면 스프링이 화면과 관련되 해결자(viewResolver)가 동작합니다.
 5. viewResolver가 templates에 hello-template이랑 똑같은 파일을 찾아서 thymeleaf템플릿 엔진에게 처리해달라고 넘깁니다.   
 그러면 템플릿 엔진이 렌더링해서 변환을 한 HTML을 웹 브라우저에 반환합니다.   
 [정적일 때는 변환 없이 그대로 반환해 주었다. 템플릿 엔진은 변환해서 웹 브라우저에 넘겨준다.]
![mvc템플릿이미지](https://user-images.githubusercontent.com/78200199/232477391-b5b9ed5d-bebb-4b63-b60e-5c83240e485a.jpg)
그림 출처 : 인프런 깅영한님 강의 [스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술]

### - API
* API는 데이터만 내려줍니다. / MVC와 템플릿 엔진은 HTML을 내려줍니다.     
   - @ResponseBody 문자 반환   
      + 참고 : [@GetMapping("hello-string")](https://github.com/Son-Gyeongi/Spring_RoadMap_Lecture/blob/master/src/main/java/hello/hellospring/controller/HelloController.java/) 
      + HTTP 통신 프로토콜에서 Head부와 Body부가 있습니다. Body부에서 데이터를 내가 직접 넣어주겠다라는 뜻입니다.   
   응답Body부에 "hello"+name을 직접 넣어주겠다라는 의미입니다.(HTTP의 Body에 문자 내용을 직접 반환)
      + @ResponseBody를 사용하면 viewResolver를 사용하지 않습니다.
      + -> 실행 후 확인 : http://localhost:8080/hello-string?name=spring 로 확인합니다. 화면에 'hello spring'이 나오면 성공!!
      
   - @ResponseBody 객체 반환
      + 참고 : [@GetMapping("hello-api")](https://github.com/Son-Gyeongi/Spring_RoadMap_Lecture/blob/master/src/main/java/hello/hellospring/controller/HelloController.java/) 
      + @ResponseBody를 사용하고, 객체를 반환하면 객체가 JSON으로 변환됩니다.
      + JSON형식은 key-value구조로 되어있습니다.
      + xml방식은 무겁고 열고닫는 태그를 두번 써야합니다.(<HTML></HTML>)   
      요즘에는 JSON방식으로 통일하고 있는 추세입니다.
      + -> 실행 후 확인 : http://localhost:8080/hello-api?name=spring 로 확인합니다. 화면에 'spring'이 나오면 성공!!
      
 - @ResponseBody 사용 원리  
 1. 웹 브라우저에 localhost:8080/hello-api를 입력합니다.
 2. 톰캣 내장 서버에서 hello-api가 왔어하고 spring에 던집니다.
 3. 스프링은 helloController 안에 hello-api가 있네 그리고 @ResponseBody 애노테니션이 붙어있네 확인을 합니다.
 4. @ResponseBody가 있으면 HTTP(Body)응답에 데이터를 그대로 넘겨야겠구나 하며 동작합니다.   
 그런데 반환하는 게 문자가 아닌 객체면 spring에서 한 번 더 생각합니다.[문자면 그냥 HTTP응답에 바로 넣어서 줬다. 그러면 끝]   
 객체가 오면 기본으로 그냥 JSON방식 데이터로 만들어서 HTTP 응답에 반환합니다.
    - @ResponseBody가 있고 hello 객체를 넘기면 이 객체를 보고 HttpMessageConverter가 동작합니다.   
    [기존에는 viewResolver가 동작했다],   
    [클라이언트의 HTTP Accept헤더와 서버의 컨트롤러 반환 타입 정보 둘을 조합해서 HttpMessageConverter가 선택된다] 
    - 단순 문자면 StringConverter가 동작
    - 객체면 JsonConverter가 동작 -> 그러면 hello 객체를 JSON스타일로 바꿉니다.
       + 객체를 JSON으로 변환하는 라이브러리
          1. Jackson2 라이브러리(스프링에 기본적으로 탑재되어 있음)
          2. gson
    - name은 값으로 요청한 웹 브라우저/서버에게 응답해 줍니다.
![Response 사용원리](https://user-images.githubusercontent.com/78200199/232477490-2eee752d-7dbe-4f61-a961-d53429fccb3b.jpg)
그림 출처 : 인프런 깅영한님 강의 [스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술]

---
**한번 더 정리**
* 정적 컨텐츠
   - 그냥 파일을 그대로 내려줍니다.
* MVC와 템플릿 엔진
   - 템플릿 엔진을 Model, View, Controller방식으로 쪼개서 View를 템플릿 엔진으로 HTML을 프로그래밍한 것을 렌더링해서 렌더링이 된 HTML을 고객에게 전달해줍니다.
* API
   - 일반적으로 객체를 반환합니다.
   - HttpMessageConverter를 통해서 JSON스타일로 바꿔서 반환합니다.
   - view 없이 바로 HTTP Response에 값을 넣어서 반환합니다.
---

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
