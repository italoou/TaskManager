# TaskManager
Esse repositório implementa o TaskManager: gerenciamento de tarefas, projeto feito com as tecnologias Java, Spring, Angular e PostgreSQL

# Objetivo
O TaskManager tem como objetivo possibilitar aos usuários o gerenciamento de suas tarefas, possibilitando que esses definam um titulo, uma descrição e um prazo para ser executado a tarefa.

# Tecnologias
- Java 17
- Spring 3.1.3
- Typescript
- Angular 16.2.1
- Bootstrap 5.2.3
- Postgresql
- Docker

# Funcionalidade idealizadas
Dentre as funcionalidade idealizadas estavam 
- Login (Implementada)
- Listar Tarefas (Implementada)
- Adicionar Tarefa (Implementada)
- Alterar Tarefa (Implementada)
- Buscar Tarefas (Implementada com bugs)
- Adicionar Usuario (Não implementada)

# Como executar

## Requisitos

- java 17
- spring 3.1.3
- angular 16.2.1
- postgresql
- docker

## Clone o repositório 

```bash
git clone https://github.com/italoou/TaskManager.git
cd TaskManager
```

## .ENV

Remova o .sample do nome do arquivo .env.sample, por padrão os dados presentes no .env são os abaixo
```json
DB_PORT=5432
DB_USERNAME=username
DB_PASSWORD=senha
DB_DATABASE=TaskManagerDB
DB_URL=task-manager-postgres
APP_PORT=8030
```
## Execução

```bash
docker-compose build
docker-compose up -d
```

## Acesso

Após a inicialização dos containers o sistemas estarão disponiveis nas portas padrão, qualquer necessidade de modificação deverá ser replicada no código.

- Frontend: http://localhost:80
- Backend: http://localhost:8030

# Autoria

Esse projeto foi desenvolvido por Ítalo Lima
- Github: https://italolima.com/
- WebSite: https://github.com/italoou