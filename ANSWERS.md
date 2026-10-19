1) The Dockerfile uses a multi-stage build. In the first stage, FROM selects the Maven and Java 21 image, and WORKDIR sets the working directory to /app. Then, pom.xml is copied and Maven downloads the dependencies before the source code is copied and the application is compiled into a JAR file. This order improves build time because Docker can reuse the cached dependency layer when only the source code changes. If COPY src ./src were placed before RUN mvn -q dependency:go-offline, source code changes could invalidate the cache for the dependency download step and make the build slower. In the second stage, a Java runtime image is used, and only the generated JAR file is copied from the build stage. The RUN useradd and USER instructions create and use a non-root user for better security, EXPOSE 8080 documents the application’s port, and ENTRYPOINT defines the command that starts the application.


2) When a container is removed, data stored inside it can be lost. Docker volumes can preserve data, even after a container is removed. Important application data, such as user accounts and orders, should be stored in a database, with a Docker volume used to keep the database files.


3) The latest tag is risky because it can point to different versions of an image over time, making builds less predictable. I used specific version tags instead, such as maven:3.9-eclipse-temurin-21 and eclipse-temurin:21-jre. This helps ensure that the same base image versions are used when building the application again.


Comparing build vs final image sizes: The build-stage image was 1.08 GB, while the final image was 514 MB. The final image is smaller because it contains only the Java runtime and the application JAR, without the Maven build tools.

About AI usage: I used ChatGPT to understand Docker concepts, the instructions in my Dockerfile, and how multi-stage builds work. I also used it for help with Java code, troubleshooting errors, translating my Turkish writing into English, and understanding the homework questions and improving my answers.