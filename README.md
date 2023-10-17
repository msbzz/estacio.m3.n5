## Estacio| Missão Prática | Nível 5 | Mundo 3

## Descrição

  Conhecer como funciona a comunicação entre threads 
      

### Objetivos da prática

- Criar servidores Java com base em Sockets.
- Criar clientes síncronos para servidores com base em Sockets.
- Criar clientes assíncronos para servidores com base em Sockets.
- Utilizar Threads para implementação de processos paralelos.
- No final do exercício, o aluno terá criado um servidor Java baseado em Socket, com
  acesso ao banco de dados via JPA, além de utilizar os recursos nativos do Java para
  implementação de clientes síncronos e assíncronos. As Threads serão usadas tanto
  no servidor, para viabilizar múltiplos clientes paralelos, quanto no cliente, para
  implementar a resposta assíncrona.
   
### Procedimentos

- Os procedimentos são divididos em duas etapas

👉 1º Procedimento | Criando o Servidor e Cliente de Teste

 - Criar o projeto do servidor, utilizando o nome
CadastroServer, do tipo console.

- Criar a camada de persistência em CadastroServer.

- Criar a camada de controle em CadastroServer 

- No pacote principal, cadastroserver, adicionar a Thread de
comunicação, com o nome CadastroThread.

- Implementar a classe de execução (main)

- Criar o cliente de teste, utilizando o nome CadastroClient,
do tipo console, no modelo Ant padrão

- Configurar o projeto do cliente para uso das entidades

- Testar o sistema criado, com a execução dos dois projetos.

- Gerar Relatório discente de acompanhamento (RDA Part1.pdf) 

  -Resultado esperado
      
  ![image](https://github.com/msbzz/estacio.m3.n5/assets/44148209/549dbb5f-2961-443c-a399-1872585e38d1)




 👉 2º Procedimento | Servidor Completo e Cliente Assíncrono 

 - Criar um Servlet com o nome ServletProdutoFC, no projeto CadastroEE-
   war
 - Criar a página de consulta, com o nome ProdutoLista.jsp
 - Criar a página de cadastro, com o nome ProdutoDados.jsp
 - Testar as funcionalidades do sistema
 - Gerar Relatório discente de acompanhamento (RDA Part2.pdf)
    
   
  ![image](https://github.com/msbzz/estacio.m3.n4/assets/44148209/d7963d63-cd9c-491c-a411-001ec2b0dd12)


👉 3º Procedimento | Melhorando o Design da Interface


  - Incluir as bibliotecas do framework Bootstrap nos arquivos ProdutoLista.jsp
    e ProdutoDados.jsp
  - Modificar as características de ProdutoLista.jsp

  - Modificar as características de ProdutoDados.jsp
  - Gerar Relatório discente de acompanhamento (RDA Part3.pdf) 
    
![image](https://github.com/msbzz/estacio.m3.n4/assets/44148209/d9e5d083-0db6-4ca5-b955-3f3bdb8474ca)
![image](https://github.com/msbzz/estacio.m3.n4/assets/44148209/04c8b4b9-69e7-4a11-81d9-b2a98b94ef11)


 - Os relatórios podem ser encontrados na pasta raiz

   ![image](https://github.com/msbzz/estacio.m3.n4/assets/44148209/27ff4f90-83f2-4a9f-9ab5-ae30ab2fdf68)

- Observaçoes
   - A IDE utilizada foi Intellij IDEA Ultimate
   - O jdk utilizado foi o 17
   - A execução do trabalho esta dividido em duas partes
     - parte 1
        - http://localhost:8080/CadastroEE-WAR/ServletProduto
    - parte 2 e 3   
        - http://localhost:8080/CadastroEE-WAR/ServletProdutoFC 
       
     
     ## Especificação
    https://sway.office.com/xixE9HxcyecGGJa3?ref=Link&loc=play
    
   
