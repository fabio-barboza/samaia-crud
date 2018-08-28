# Aplicação de Avaliação da Empresa Samaia IT

Projeto foi criado com a ferramente da própria empresa criadora do Spring. A Spring Tool Suite (STS).
As tecnologias utilizadas foram:

Spring Boot 2
AngularJS
Boostrap 4

A aplicação possui 2 configurações de banco de dados uma em H2 e outra em MySQL, por default está sendo usado o H2, mas caso deseje utilizar a base em MySQL, deverá comentar a configuração do H2 no arquivo "/src/main/resources/application.properties" e descomentar a configuração do MySQL. (Não esquecendo de ajustar os parâmetros como Schema, Usuário e Senha e também rodar o Script de criação da base.)

Script para criação da tabela:

CREATE TABLE `usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(250) NULL,
  `email` VARCHAR(250) NULL,
  `data` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `ativo` TINYINT NULL DEFAULT 1,
  PRIMARY KEY (`id`));

Foram criados testes unitários e os integrados sendo que eles são rodados em uma Base de dados do H2 Database Engine diferente da base onde a aplicação está rodando.


## Live Sample

API pode ser acessada em uma instâcia do AWS EC2 através da URL:

http://ec2-13-59-22-163.us-east-2.compute.amazonaws.com:8000/samaia-crud

Documentação Swagger:

http://ec2-13-59-22-163.us-east-2.compute.amazonaws.com:8000/samaia-crud/swagger-ui.html


## Rodando a aplicação

Para rodar a aplicação é necessário ter o Java 8 ou superior instalado.

Para rodar a aplicação em ambiente de testes execute o comando:

Linux

./mvnw spring-boot:run

Windows

mvnw.cmd spring-boot:run

Em seguida abrir o navegador desejado e abrir a URL: http://localhost:8000/samaia-crud


## Gerando a imagem e rodando dentro de um container Docker

sudo ./mvnw install dockerfile:build

sudo docker run -p 8000:8000 -t springio/samaia-crud

Em seguida abrir o navegador desejado e abrir a URL: http://localhost:8000/samaia-crud


## Documentação Swagger Local

Foi criada uma documentação utilizando a API Swagger.
Após inicializar a aplicação a documentação via Swagger estará disponivel através da URL:

http://localhost:8000/samaia-crud/swagger-ui.html


## Rodando os Testes

Para executar o testes unitários utilize o comando:

Linux

./mvnw clean test

Windows

mvnw.cmd clean test

Obs: Também podem ser rodados dentro da IDE utilizando o JUnit.


## Gerando o executável da aplicação

Linux

./mvnw clean package

Windows

mvnw.cmd clean package

Observação: Por padrão essa aplicação gera um arquivo ".WAR" que pode ser executado diretamente da linha de comando ou feito o deploy em qualquer servidor de aplicações moderno padrão JAVA EE.


