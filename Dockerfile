#FROM amazoncorretto:11
#MAINTAINER nikolaspiric86@gmail.com
#
#ADD build/libs/app-*.jar /app.jar
#
#EXPOSE 8080
#
#CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]