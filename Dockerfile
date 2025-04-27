# Usa imagem base do Java 17 (ou ajuste para sua versão do Java)
FROM openjdk:23-oracle

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o jar da sua aplicação para dentro do container
COPY target/*.jar app.jar

# Expõe a porta 8080 para acesso externo
EXPOSE 8081

# Comando para rodar sua aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
