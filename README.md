# Microsservices CI/CD Example

Projeto de exemplo demonstrando a implementação de uma arquitetura baseada em microsserviços em Java (Spring Boot) com foco em Continuous Integration (CI) e Continuous Deployment (CD), utilizando conteinerização, orquestração local, mensageria e persistência de dados.

## Descrição

Este repositório contém um projeto backend construído com Spring Boot, que serve como base para explorar conceitos de microsserviços e pipelines de CI/CD. Ele inclui um exemplo de serviço (como um User Service), integração com Kafka para comunicação assíncrona e PostgreSQL para armazenamento de dados, tudo orquestrado localmente via Docker Compose.

O objetivo é fornecer um ponto de partida para entender como configurar um ambiente de desenvolvimento para microsserviços conteinerizados e como prepará-lo para automação de build, teste e deploy.

## Funcionalidades (Exemplos)

* Implementação de um serviço backend RESTful (Ex: User Service).
* Endpoints RESTful para operações CRUD (Create, Read, Update, Delete).
* Validação de entrada de dados usando Jakarta Validation.
* Tratamento global de exceções (`@ControllerAdvice`).
* Comunicação assíncrona (integração com Kafka).
* Persistência de dados (integração com PostgreSQL).
* Conteinerização das aplicações (Java App, Kafka, PostgreSQL, Zookeeper).
* Orquestração do ambiente local com Docker Compose.

## Tecnologias Utilizadas

* **Linguagem:** Java
* **Framework:** Spring Boot
* **Build Tool:** Apache Maven
* **Conteinerização:** Docker
* **Orquestração Local:** Docker Compose
* **Mensageria:** Apache Kafka (via `docker-compose`)
* **Gerenciamento de Broker Kafka:** Apache Zookeeper (via `docker-compose`)
* **Banco de Dados:** PostgreSQL (via `docker-compose`)
* **Logging:** SLF4J / Logback (via Lombok `@Slf4j`)
* **Redução de Boilerplate:** Project Lombok
* **Validação:** Jakarta Validation / Hibernate Validator

## Pré-requisitos

Para rodar este projeto localmente, você precisará ter instalado:

* Java Development Kit (JDK) 11 ou superior.
* Apache Maven.
* Docker Desktop (ou Docker Engine) instalado e rodando no seu sistema operacional.
* (Opcional, mas útil) Um cliente Kafka de linha de comando como o `kcat` (anteriormente `kafkacat`).

## Primeiros Passos

Siga estas instruções para configurar e executar o projeto na sua máquina local.

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/lipebarba2/Microsservices-CI-CD.git](https://github.com/lipebarba2/Microsservices-CI-CD.git)
    cd Microsservices-CI-CD
    ```

2.  **Construa o Projeto Java:**
    Use o Maven para compilar o código e gerar o pacote executável (JAR ou WAR, dependendo da configuração). Pule os testes inicialmente se preferir agilizar o processo.
    ```bash
    mvn clean install -DskipTests
    ```
    *Nota: Certifique-se de que seu `Dockerfile` para a aplicação Java esteja configurado para copiar este pacote gerado.*

3.  **Construa as Imagens Docker (se necessário):**
    Se o seu arquivo `docker-compose.yml` referencia um `Dockerfile` local para a sua aplicação Java, construa a imagem:
    ```bash
    docker compose build
    ```
    Se ele apenas usa imagens públicas (ex: para Kafka, Postgres), este passo não é necessário para essas imagens.

4.  **Inicie os Serviços com Docker Compose:**
    Este comando irá criar a rede, volumes e iniciar os containers definidos no `docker-compose.yml` em modo detached (`-d`).
    ```bash
    docker compose up -d
    ```
    *Certifique-se de que nenhuma porta necessária (como 8080/8081 para a aplicação, 5432 para Postgres, 9092 para Kafka) esteja sendo usada por outros processos no seu host.*

5.  **Verifique o status dos containers:**
    Confirme se todos os containers foram iniciados corretamente:
    ```bash
    docker compose ps
    ```
    Você deverá ver os serviços `kafka`, `postgres` (e Zookeeper, se incluído) e o container da sua aplicação Java (`microsservices-ci-cd-app`, ou nome similar) com status `Up`.

## Estrutura do Projeto

Microsservices-CI-CD/
├── src/
│   ├── main/
│   │   ├── java/            # Código fonte Java (Controllers, Services, Models, Exceptions)
│   │   └── resources/       # Arquivos de configuração (application.properties/yml)
│   └── test/              # Código de testes
├── pom.xml                # Arquivo de configuração do Maven
├── docker-compose.yml     # Configuração para Docker Compose
├── Dockerfile             # Dockerfile para a aplicação Java (se necessário)
└── README.md              # Este arquivo
