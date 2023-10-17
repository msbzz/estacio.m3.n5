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

 - Criar uma segunda versão da Thread de comunicação, noprojeto do servidor, com o acréscimo da funcionalidade
 
 - Acrescentar os controladores necessários na classeprincipal, método main, e trocar a instância da Thread anterior pela nova Thread no loop de conexão.

- Criar o cliente assíncrono, utilizando o nome CadastroClientV2, do tipo console.

- Criar a janela para apresentação das mensagens descendente de JDialog 

-  Definir a Thread de preenchimento assíncrono, com o nome ThreadClient
-  
- Gerar Relatório discente de acompanhamento (RDA Part2.pdf) 

  - Resutaldos esperados  

![image](https://github.com/msbzz/estacio.m3.n5/assets/44148209/3f5794a1-1ed9-4493-b921-8319ebbee116)

![image](https://github.com/msbzz/estacio.m3.n5/assets/44148209/9d66b014-4716-4aff-99b9-ee0de85e7906)

- Observaçoes
   - A IDE utilizada foi Intellij IDEA Ultimate
   - O jdk utilizado foi o 17
   - A execução do trabalho esta dividido em duas partes
     - parte 1
        - http://localhost:8080/CadastroEE-WAR/ServletProduto
    - parte 2 e 3   
        - http://localhost:8080/CadastroEE-WAR/ServletProdutoFC 
       
     
     ## Especificação
    
    https://sway.office.com/ertZuvZwxHwQpwAS?ref=Link&loc=play
   
