# EMEASY
프로젝트 실행 방법:

1. Java 개발 환경이 설치되어 있어야 합니다 (JDK 11 이상 권장).
2. 프로젝트를 다운로드하거나 클론합니다.
3. 프로젝트 루트 디렉토리로 이동합니다.
4. 다음 명령으로 프로젝트를 빌드합니다:
   ./gradlew clean build
5. 빌드가 완료되면 다음 명령으로 애플리케이션을 실행합니다:
   ./gradlew bootRun
6. 웹 브라우저에서 다음 URL로 접속하여 애플리케이션을 확인할 수 있습니다:
   http://localhost:8080
7. 필요한 경우 H2 데이터베이스 콘솔에 접속할 수 있습니다:
   http://localhost:8080/h2-console
   (JDBC URL: jdbc:h2:mem:testdb, Username: sa, Password: [빈 값])
