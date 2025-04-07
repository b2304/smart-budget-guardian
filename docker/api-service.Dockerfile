FROM hseeberger/scala-sbt:11.0.16.1_1.8.0_1.7.2

WORKDIR /app

COPY . /app

RUN sbt compile

CMD ["sbt", "run"]