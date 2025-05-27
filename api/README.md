# 🔗 Link Shortener - Java + Spring Boot

Este é um projeto de encurtador de URLs desenvolvido em **Java com Spring Boot**, que permite a criação de links curtos e redireciona os usuários para o destino original. Utiliza **Docker** para empacotamento e **Traefik** como proxy reverso para roteamento dinâmico de requisições HTTP.

---

## 🚀 Funcionalidades

- Criar URLs curtas (ex: `www.shorturl.com/abc123`)
- Redirecionamento automático para a URL original
- Interface RESTful simples
- Integração com **Traefik** para roteamento baseado em path
- Contêineres Docker para app e banco de dados PostgreSQL

---

## 🧱 Tecnologias

- Java 21
- Spring Boot 3
- PostgreSQL
- Docker & Docker Compose
- Traefik
- Hibernate / JPA

---

## 📦 Estrutura do Projeto
api/   
├── src/    
│ └── main/java/... # Código fonte Java   
├── docker-compose.yml # Define app, banco de dados e Traefik   
├── application.properties # Configuração Spring   
├── Dockerfile # Dockerfile da aplicação   
└── README.md

## 🌐 Traefik
O Traefik é um proxy reverso moderno que gerencia e redireciona automaticamente o tráfego para seus serviços em containers, como se fosse um "porteiro inteligente" para nossa aplicação.

A URL curta (exemplo: /abc123) é roteada pelo Traefik, que envia a requisição para o container da aplicação, sem precisar expor diretamente a porta do app.

## ⚙️ Configuração com Docker + Traefik

### 1. Clone o projeto

```bash
git clone https://github.com/seu-usuario/link-shortener.git
cd link-shortener
```
### 2. Adicione as variáveis de ambiente
Existem duas formas de lidar com as variáveis de ambiente no projeto. Caso esteja utilizando Intellij, pode usar a extensão "DotEnv" que permite a IDE ler variáveis em um arquivo ".env" na raiz do projeto conforme o ".env-example".

Também pode adicionar manualmente as variáveis de ambiente no seu sistema seguindo a nomenclatura do arquivo .env-example.

### 3. Executar o projeto
* #### 3.1 Gerar o arquivo jar do projeto:
```bash
mvn clean package 
```
* #### 3.2 Buildar a iamgem docker do projeto:
```bash
docker build -t link-shorten-img .
```
* #### 3.3 Executar a aplicação via docker:
```bash
docker-compose up --build
```

### 4. Acesse a aplicação - Endpoints
* #### 4.1 Criação de link:

```bash
POST http://localhost/api/links/shorten
Body JSON: { "originalUrl": "https://example.com" }
```

#### 4.2 Acesso todos os links criados:

```bash
GET http://localhost/api/links
```

### 4.3 Cancelar um link criado
```bash
PATCH  http://localhost/api/links/{codigoCurto}
```
