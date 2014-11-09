# Build:
#   docker build -t dgageot/jug .
#
# Run:
#   docker run --rm -ti dgageot/jug

FROM dgageot/maven

# Set working directory first
#
WORKDIR /home/jug

# Warmup maven by building an old version that we don't change often
#
ADD docker/old_version.tgz /home/jug
RUN mvn verify dependency:copy-dependencies -DskipTests \
    && rm -Rf /home/jug

# Set run environment
#
#ENV PROD_MODE true
#ENV MEMORY 4
#EXPOSE 8080
#CMD java -DPROD_MODE=${PROD_MODE} -Xmx${MEMORY}G -jar target/jug.jar

# Add all sources from docker context
#
ADD . /home/jug
ADD Dockerfile.run /home/jug/target/Dockerfile

# Build the app
# (This command being last, a change in the code triggers a
# minimal rebuild)
#
RUN mvn verify dependency:copy-dependencies -DskipTests

#CMD cat target/Dockerfile
CMD cd target && tar c .
