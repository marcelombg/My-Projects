# Passo a passo do projeto

- Estrutura projeto node
  - Criar pasta do projeto
  - npm init -y
  - npm i typescript @types/node tsx tsup -D
  - npx tsc --init
  - Arquivo tsconfig.json trocar "targe" para "es2020"
  - npm i fastify
  - criar arquivo app.ts na pasta src
  - criar arquivo server.ts na pasta src
  - alterar package.json:
      "scripts": {
      "dev": "tsx watch src/server.ts",
      "build":"tsup src --out-dir build",
      "start": "node build/server.js"
    }
  - criar arquivo .npmrc com o conteúdo save-exact=true
  - criar arquivo .env
  - npm i dotenv
  - npm i zod
  - npm i eslint @rocketseat/eslint-config -D
  - criar arquivo .eslintrc.json e .eslintignore
  - alterar tsconfig.json:
    "baseUrl": "./",
    "paths": {
      "@/*": ["./src/*"]
    }
- Banco de Dados
  - npm i prisma -D
  - npx prisma init
  - npx prisma generate (após criar model de uma tabela)
  - npm i @prisma/client (depência de production)




# App

GymPass style app.

##RF (Requisitos funcionais)
[ ] Dever ser possível se cadastrar;
[ ] Dever ser possível se autenticar;
[ ] Dever ser possível obter o perfil de um usuário logado;
[ ] Dever ser possível obter o número de check-ins realizados pelo usuário logado;
[ ] Dever ser possível o usuário obter seu histórico de check-ins;
[ ] Dever ser possível o usuário buscar academias próximas;
[ ] Dever ser possível o usuário buscar academias pelo nome;
[ ] Dever ser possível o usuário realizar check-in em uma academia;
[ ] Dever ser possível validar o check-in de um usuário;
[ ] Dever ser possível cadastrar uma academia;

##RNs (Regras de negócio)
[ ] O usuário não deve poder se cadastrar um e-mail duplicado;
[ ] O usuário não fazer 2 check-ins no mesmo dia;
[ ] O usuário não pode fazer check-in se não estiver perto (100m) da academia;
[ ] O check-in só pode ser validado até 20 minutos após criado;
[ ] O check-in só pode ser validado por administradores;
[ ] A academia só pode ser cadastrada por administradores;

##RNFs (Requisitos não-funcionais)
[ ] A senha do usuário precisa estar criptografada;
[ ] Os dados da aplicação precisam estar persistidos em um banco PostgreSQL;
[ ] Todas listas de dados precisam estar paginadas com 20 itens por página;
[ ] O usuário deve ser identificado por um JWT (Json Web Token).