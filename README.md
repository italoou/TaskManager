# TaskManager
Esse repositorio implementa o task manager: gerenciamento de tarefa, projeto feito com as tecnologias Java v17, Spring v3.1.3, Angular v14 e PostgreSQL

# Objetivo
O TaskManager tem como objetivo possibilitar aos usuarios o gerenciamento de suas tarefas, possibilitando que esses definam um titulo, uma descrição e um prazo para ser executado a tarefa.

# Funcionalidade idealizadas
dentre as funcionalidade idealizadas estavam 
- Login (Implementada)
- Listar Tarefas (Implementada)
- Adicionar Tarefa (Implementada)
- Alterar Tarefa (Implementada)
- Buscar Tarefas (Implementada com bugs)
- Adicionar Usuario (Não implementada)

# Como executar

## Requisitos

- java 17
- spring ^3
- angular 14
- docker

## Clone o repositório 

```bash
git clone https://github.com/italoou/TaskManager.git
cd TaskManager
```

## Executar

```bash
docker-compose build
docker-compose up -d
```

