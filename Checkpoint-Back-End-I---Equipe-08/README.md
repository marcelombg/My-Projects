# Checkpoint-Back-End-I---Equipe-08 - Dumith BouHabib, Erick Niciolli, Larissa Felipe, Marcelo Garofalo e Ricardo Brito

**Back End I - Trabalho integrador**
Sistema de reserva de consultas

Para utilizar o sistema, por gentileza siga os passos abaixo:

1º - Dê um play no arquivo de applicação CheckpointBackEndIEquipe08Application;

2º - A partir deste momento, abra o postman para seguir os passos 3, 4, 5, 6, 7, 8 e 9; 

3º - Cadastre um usuário (endpoint http://localhost:8081/user);
    Os tipos de usuários que temos disponíveis são: ROLE_PACIENTE, ROLE_DENTISTA, ROLE_ADMIN.
    Cada um dos usuários tem as seguintes permissões:
    ADMIN - permissões para registrar, modificar, buscar todos, buscar por ID e deletar todas as classes;
    PACIENTE - permissões para registrar, buscar por ID, modificar as classes Paciente e Endereço, buscar por ID a classe consulta;
    DENTISTA - permissões para registrar, buscar por ID, modificar a classe Dentista e buscar por ID a classe consulta;

4º - Crie uma autenticação para este usuário (endpoint http://localhost:8081/user/authenticate). 
    O JWT da autenticação deverá ser usado de agora em diante em todos os endpoints;

5º - Cadastre o endereço (POST, endpoint http://localhost:8081/endereco/registrar);

6º - Cadastre o paciente (POST, endpoint http://localhost:8081/paciente/registrar);

7º - Cadastre o dentista (POST, endpoint http://localhost:8081/dentista/registrar);

8º - Cadastre a consulta, somente o usuário ADMIN tem essa permissão (POST, endpoint 
http://localhost:8081/consulta/cadastrar);

9º - Realize uma busca por ID em todas as classes (GET), usando os endpoints:
    http://localhost:8081/endereco/{id});
    http://localhost:8081/paciente/{id});
    http://localhost:8081/dentista/{id});
    http://localhost:8081/consulta/{id}); 

10º - Realize uma busca por todas as classes (GET), usando os endpoints (esta busca só pode ser feita pela role ADMIN):
    http://localhost:8081/endereco/buscar);
    http://localhost:8081/paciente/buscar);
    http://localhost:8081/dentista/buscar);
    http://localhost:8081/consulta/buscar);

11º - Modificar um cadastro (PUT), usando os endpoints:
    http://localhost:8081/endereco/{id});
    http://localhost:8081/paciente/{id});
    http://localhost:8081/dentista/{id});
    http://localhost:8081/consulta/{id});

12º - Para deletar (DELETE) qualquer cadastro, devido aos relacionamentos das tabelas, será necessário fazer o caminho 
contrário em que foram feitos os registros, ou seja, respeitando a ordem consulta > dentista > paciente > endereço, 
usando os endpoints:
    http://localhost:8081/consulta/{id});
    http://localhost:8081/endereco/{id});
    http://localhost:8081/paciente/{id});
    http://localhost:8081/dentista/{id});

13º - As validações para exceptions estão na pasta main/java/CheckpointBackEndIEquipe08/validacoes;

14º - Os testes unitários estão dentro da pasta test/java/controller. Como algumas das classes precisam de dados para 
serem executados corretamente, pedimos para que sejam executados a classe de teste completa (que roda todos os testes em
ordem).