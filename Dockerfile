FROM dgageot/maven

# Set working directory first
#
WORKDIR /home/jug

# Warmup maven by building an old version that we don't change often
#
ADD docker/old_version.tgz /home/jug
RUN mvn verify dependency:copy-dependencies -DskipTests && rm -Rf /home/jug

# Set run environement
#
ENV PROD_MODE true
ENV MEMORY 4
CMD java -server -DPROD_MODE=${PROD_MODE} -Xmx${MEMORY}G -jar target/jug.jar

# Add all sources from docker context
#
ADD . /home/jug

# Build the app
# (This command being last, a change in the code does not trigger a
# full rebuild)
#
RUN mvn verify dependency:copy-dependencies -DskipTests
