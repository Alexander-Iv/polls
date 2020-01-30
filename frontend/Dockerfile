FROM java:8
LABEL maintainer="alexander.an.ivanov@gmail.com"
RUN mkdir -p /home/app
COPY ./build/libs/ /home/app/
WORKDIR /home/app
EXPOSE 8080
ENV APP_HOST=backend
CMD java -jar frontend-0.0.1.jar
