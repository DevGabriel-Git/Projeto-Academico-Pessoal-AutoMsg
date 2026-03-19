# 🚀 AutoMsg - Sistema de Gestão com Notificação WhatsApp

API REST desenvolvida com **Spring Boot** para gerenciamento de mecânicas, clientes e ordens de serviço, com envio automático de notificações via WhatsApp.

---

## 📌 Sobre o Projeto

O **AutoMsg** foi criado com o objetivo de automatizar o controle de serviços em oficinas mecânicas, permitindo acompanhar o status de equipamentos e notificar clientes em tempo real.

---

## ⚙️ Funcionalidades

* 📋 Cadastro de clientes
* 🔧 Cadastro de veículos
* 🛠️ Controle de ordens de serviço
* 📊 Atualização de status (Em manutenção, Liberado, Orçamento, etc)
* 📲 Envio automático de mensagens via WhatsApp
* 🔗 API REST para integração com outros sistemas

---

## 🛠️ Tecnologias utilizadas

* Java 17+
* Spring Boot
* Spring Data JPA
* Hibernate
* Maven
* Postman (testes de API)

---


## ▶️ Como rodar o projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/DevGabriel-Git/Projeto-Academico-Pessoal-AutoMsg.git
```

### 2. Entrar na pasta

```bash
cd Projeto-Academico-Pessoal-AutoMsg
```

### 3. Rodar o projeto

```bash
mvn spring-boot:run
```

---

## 📬 Testes da API

A collection do Postman está disponível na pasta:

```
/postman
```

Basta importar no Postman e testar os endpoints.

---

## 📡 Exemplos de endpoints

```
GET    /api/clientes
POST   /api/clientes
PUT    /api/clientes/{id}
DELETE /api/clientes/{id}
```

---

## 💡 Objetivo do Projeto

Projeto desenvolvido com foco em aprendizado e prática de:

* Desenvolvimento backend com Java
* Arquitetura REST
* Integração com APIs externas (WhatsApp)
* Boas práticas de organização de código

---

## 📈 Melhorias futuras

* 🔐 Autenticação com JWT
* 📱 Interface frontend (React ou Angular)
* ☁️ Deploy em nuvem
* 📊 Dashboard com métricas

---

## 👨‍💻 Autor

Gabriel Silva
🔗 https://github.com/DevGabriel-Git
