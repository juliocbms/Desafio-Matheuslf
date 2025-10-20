#  API de Gestão de Projetos e Tarefas

##  1. Visão Geral

Esta é uma **API RESTful** completa para o gerenciamento de **projetos e tarefas (demandas)**, desenvolvida com **Java 21** e **Spring Boot 3+** como solução para o *Desafio Técnico proposto*.

O projeto foi arquitetado desde sua concepção para ser **robusto**, **escalável**, **seguro** e de **fácil manutenção**, servindo como uma demonstração de proficiência em tecnologias e padrões de arquitetura.

###  Funcionalidades Implementadas

- **Gestão de Projetos:** Criação e listagem de projetos.  
- **Gestão de Tarefas:** Criação, atualização de status e exclusão de tarefas associadas a projetos.  
- **Autenticação e Autorização:** Login e registro com **JWT (JSON Web Token)** e controle de papéis (Roles).  
- **Buscas Avançadas:** Filtros dinâmicos e paginação para consultas eficientes.  
- **Documentação Interativa:** Swagger/OpenAPI.  
- **Pipeline de CI/CD:** Integração e entrega contínuas com **GitHub Actions** e **Docker**.

---

##  2. Arquitetura e Decisões de Design

A aplicação segue uma **Arquitetura em Camadas (Layered Architecture)**), que implementa o padrão **Model-View-Controller (MVC)** adaptado para o contexto de APIs RESTful, garantindo uma clara S **Separação de Responsabilidades** 


###  2.1 Estratégia de Persistência: Flyway + Hibernate

A gestão do schema é tratada como código (*Database as Code*).

- **Flyway:** Controla as migrações SQL versionadas (V1__, V2__, ...).  
- **Hibernate:** Configurado com `spring.jpa.hibernate.ddl-auto=validate`, atuando apenas como **validador** das entidades.  
- **PostgreSQL:** Uso de **ENUMs nativos** e **ON DELETE CASCADE** para performance e integridade de dados.

---

###  2.2 Design do Contrato da API (DTO + MapStruct)

- **DTOs com `record`:** Objetos imutáveis e concisos.  
- **Separação de Request e Response:** Evita mass assignment e loops de serialização.  
- **MapStruct:** Mapeamento automático entre entidades e DTOs, gerado em tempo de compilação (sem reflexão).

---

###  2.3 Segurança com Spring Security e JWT

- **Registro:** Senhas criptografadas com `BCryptPasswordEncoder`.  
- **Login:** Geração de token JWT com `userId`, `email` e `roles`.  
- **Autorização:** `OncePerRequestFilter` valida tokens e popula o `SecurityContextHolder`.  
- Suporte a anotações como `@PreAuthorize`.

---

###  2.4 Estratégia de Testes

- **Testes de Unidade:** Camada de serviço, com **JUnit 5** e **Mockito**.  
- **Testes de Integração:** Camada de controller, com **@WebMvcTest** e **@MockBean**.

---

##  3. Pipeline de CI/CD com GitHub Actions

O pipeline é definido em `.github/workflows/ci-cd.yml` e executa:

1. **Gatilho:** `git push` na branch `main`.  
2. **Setup:** Ambiente Linux, Java 21 e cache do Maven.  
3. **Testes:** Execução com banco **PostgreSQL** em contêiner temporário.  
4. **Build da Imagem Docker:** `mvn spring-boot:build-image`.  
5. **Publicação:** Imagem é publicada no **Docker Hub**.

---

##  4. Manual de Execução

Há duas formas de executar o projeto, conforme sua necessidade.

---

###  Opção 1: Executar Apenas com Docker

Ideal para uso final ou implantação — **não é necessário clonar o projeto**.

####  Crie o arquivo `docker-compose.yml`

```yaml
version: '3.8'

services:
  desafio-db:
    image: postgres:16.3
    container_name: postgres_db
    restart: always
    environment:
      TZ: America/Sao_Paulo
      POSTGRES_DB: Desafio
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: root
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
    networks:
      - julio-network

  api:
    image: juliocbms/desafio:latest
    container_name: desafio_app
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://desafio-db:5432/Desafio
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: root
    ports:
      - "8080:8080"
    depends_on:
      - desafio-db
    networks:
      - julio-network

networks:
  julio-network:
    driver: bridge

volumes:
  postgres_data:


```

##  Inicie a aplicação

````markdown
##  Inicie a aplicação

```bash
docker-compose up -d
````

A aplicação estará disponível em:

* **API:** [http://localhost:8080](http://localhost:8080)
* **Swagger:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

##  Opção 2: Ambiente de Desenvolvimento (para Contribuidores)

###  Pré-requisitos

* Git
* Java (JDK 21)
* Maven
* Docker

---

###  Clone o repositório

```bash
git clone https://github.com/juliocbms/Desafio-Matheuslf.git
cd Desafio-Matheuslf
```

---

###  Inicie com Docker Compose

```bash
docker-compose up -d
```

---

###  Rodar localmente (via IDE)

Execute a aplicação pela sua IDE.
Ela se conectará automaticamente ao banco iniciado pelo Docker Compose.

---

###  Parar a aplicação

```bash
docker-compose down
```

---

##  Autor

**Júlio César**
 Docker Hub: [juliocbms](https://hub.docker.com/u/juliocbms)



