FROM dgageot/maven

WORKDIR /home/jug

# Warmup maven by building an old version that we don't change often
ADD docker/old_version.tgz /home/jug
RUN mvn verify dependency:copy-dependencies -DskipTests && rm -Rf /home/jug

ENV PROD_MODE true
ENV MEMORY 4

CMD java -server -DPROD_MODE=${PROD_MODE} -Xmx${MEMORY}G -jar target/jug.jar

# Add all sources from docker context
ADD . /home/jug
RUN mvn verify dependency:copy-dependencies -DskipTests

