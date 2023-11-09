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
  - subir container do Docker
  - npx prisma migrate dev (testar conexão e atualizar dados para o migrate)
  - npx prisma studio (abrir prisma no navegador)

- Docker:
  - podman run --name projeto_03-pg -e POSTGRESQL_USERNAME=admin -e POSTGRESQL_PASSWORD=admin -e POSTGRESQL_DATABASE=projeto03 -p 5432:5432 bitnami/postgresql:latest (cria imagem do postgre)
  - podman ps -a (listar imagens disponíveis)
  - podman ps (listar containers ativos)
  - podman start <nome ou ID do container> (iniciar o container)
  - podman stop <nome ou ID do container> (para o container)
  - podman rm <nome ou ID do container> (remove o container)
  - podman logs <nome ou ID do container> (remove o container)
  - criar compose.yml
  - podman machine start (iniciar serviço do podman)
  - podman-compose up (iniciar containers do compose com logs)
  - podman-compose up -d (iniciar containers do compose sem logs)
  - podman-compose down (parar containers do compose)

- Hash da senha e validação:
  - npm i bcryptjs (biblioteca para fazer hash)
  - npm i -D @types/bcryptjs

- Testes:
  - npm i vitest vite-tsconfig-paths -D (instalar o vitest e pacote de paths para que o vitest leia os paths do tsconfig.json)
  - criar arquivo vite.config.ts
  - criar scripts para o vitest no package.json
  - npm i @vitest/coverage-v8 -D
  - criar scripts para o vitest coverage no package.json
  - npm i -D @vitest/ui
  - criar scripts para o vitest ui no package.json
  - npm i dayjs (biblioteca para trabalhar com data)

- Token JWT
  - npm i @fastify/jwt

- Testes E2E
 - criar pasta com o nome vitest-environment-prisma
 - acessar a pasta e dar o comando npm init -y
 - no package.json da pasta vitest-environment-prisma, altere "main": "prisma-test-environment.js",
 - criar objeto test no arquivo vite.config.ts,
 - dentro da pasta vitest-environment-prisma dar o comando npm link,
 - dentro da pasta do projeto dar o comando npm link vitest-environment-prisma
 - npm i -D npm-run-all
 - no package.json da pasta do projeto, altere "test:create-prisma-environment": "npm link ./prisma/vitest-environment-prisma", "test:install-prisma-environment": "npm link vitest-environment-prisma", "pretest:e2e": "run-s test:create-prisma-environment test:install-prisma-environment"
 - npm i supertest -D
 - npm i @types/supertest -D

# App

GymPass style app.

##RF (Requisitos funcionais)
[X] Dever ser possível se cadastrar;
[X] Dever ser possível se autenticar;
[X] Dever ser possível obter o perfil de um usuário logado;
[X] Dever ser possível obter o número de check-ins realizados pelo usuário logado;
[X] Dever ser possível o usuário obter seu histórico de check-ins;
[X] Dever ser possível o usuário buscar academias próximas (até 10km);
[X] Dever ser possível o usuário buscar academias pelo nome;
[X] Dever ser possível o usuário realizar check-in em uma academia;
[X] Dever ser possível validar o check-in de um usuário;
[X] Dever ser possível cadastrar uma academia;

##RNs (Regras de negócio)
[X] O usuário não deve poder se cadastrar um e-mail duplicado;
[X] O usuário não fazer 2 check-ins no mesmo dia;
[X] O usuário não pode fazer check-in se não estiver perto (100m) da academia;
[X] O check-in só pode ser validado até 20 minutos após criado;
[ ] O check-in só pode ser validado por administradores;
[ ] A academia só pode ser cadastrada por administradores;

##RNFs (Requisitos não-funcionais)
[X] A senha do usuário precisa estar criptografada;
[X] Os dados da aplicação precisam estar persistidos em um banco PostgreSQL;
[X] Todas listas de dados precisam estar paginadas com 20 itens por página;
[ ] O usuário deve ser identificado por um JWT (Json Web Token).