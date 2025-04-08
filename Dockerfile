FROM amd64/amazoncorretto:17
COPY cubco-api/build/libs/cubco-api-0.0.1-SNAPSHOT.jar cubco.jar
ENTRYPOINT ["java", "-Duser.timezone=Asia/Seoul", "-jar", "-Dspring.profiles.active=dev", "cubco.jar"]