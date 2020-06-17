FROM java:8-jre
MAINTAINER afmartin3z

COPY ./wait-for-it.sh /app/
RUN chmod a+x /app/wait-for-it.sh
ADD ./target/tasks-1.0.0.jar /app/
CMD ["/app/wait-for-it.sh", "tasksdb:1521", "--timeout=60", "--strict", "--", "java", "-Xmx200m", "-Doracle.jdbc.timezoneAsRegion=false", "-jar", "/app/tasks-1.0.0.jar"]

EXPOSE 8080