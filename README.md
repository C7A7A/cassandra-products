## About the project
A REST API built with Spring Boot and Cassandra for storing product data. 
It supports CRUD operations and category-based filtering, secured with cookie authentication. 
The project is fully Dockerized for easy deployment and comes with a Postman collection for API testing.

## Built With
[![Java][Java]][Java-url]
[![Spring][Spring]][Spring-url]
[![Cassandra][Cassandra]][Cassandra-url]
[![Docker][Docker]][Docker-url]


<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[Java]:https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white
[Java-url]: https://docs.oracle.com/en/java/
[Spring]:https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=Spring&logoColor=white
[Spring-url]: https://spring.io/projects/spring-boot
[Cassandra]: https://img.shields.io/badge/Cassandra-0a88b2?style=for-the-badge&logo=apache&logoColor=white
[Cassandra-url]:https://cassandra.apache.org/_/index.html
[Docker]:https://img.shields.io/badge/Docker-0db7ed?style=for-the-badge&logo=Docker&logoColor=white
[Docker-url]: https://docs.docker.com/


## How to launch
You can launch project fully in Docker or locally (backend locally, Cassandra in Docker)
### Docker
1. <code> git clone git@github.com:C7A7A/cassandra-products.git </code>
2. <code> cd cassandra-products/ </code>
3. <code> docker -up build </code>

### Locally
#### Cassandra in Docker
1. <code> docker pull cassandra:latest </code>
2. <code> docker run --name cassandra-products -p 9042:9042 -d cassandra:latest </code>
3. <code> docker exec -it cassandra-products cqlsh </code>
4. In the CQL shell, create the keyspace </br>
<code> CREATE KEYSPACE IF NOT EXISTS products
   WITH replication = {
   'class': 'SimpleStrategy',
   'replication_factor': 1
   };
</code>

#### Clone and Build Project
1. <code> git clone git@github.com:C7A7A/cassandra-products.git </code>
2. <code> cd cassandra-products/ </code>
3. <code> ./gradlew build -x test </code>
4. <code> ./gradlew bootRun </code>


## Testing the API
A Postman collection with sample requests is available in
<code> postman_collection/cassandra-products_postman_requests.json </code>