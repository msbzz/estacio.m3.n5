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

## Ativando o projeto parte 1

  - Essa parte do trabalho esta dividido em dois projetos separados onde o projeto da thraad que estará atuando como servidor estará na pasta "CadastroServerPart1Server" e outro projeto que é a thread cliente que esta em "CadastroServerPart1Client"

   ![image](Parte1/assets/image2.png)

   - Nesse caso sera necessário ativar a thread server em primeiramente e a client em seguida onde teremos o resultado esperado.

    - Ativando Server

   ![image](Parte1/assets/image3.png)

  - Ativando Client

   ![image](Parte1/assets/image4.png)

  -Resultado esperado
      
  ![image](Parte1/assets/image1.png)

  ## Banco e sua conexão

   - Considerando a existência do banco sql server conforme em especificação. a configuração da conexão é feita no arquivo persistence.xml conforme indicado abaixo
   
    - localização 

   ![image](Parte1/assets/image6.png)

    - configurações 

   ![image](Parte1/assets/image5.png)


 👉 2º Procedimento | Servidor Completo e Cliente Assíncrono 

 - Criar uma segunda versão da Thread de comunicação, noprojeto do servidor, com o acréscimo da funcionalidade
 
 - Acrescentar os controladores necessários na classeprincipal, método main, e trocar a instância da Thread anterior pela nova Thread no loop de conexão.

- Criar o cliente assíncrono, utilizando o nome CadastroClientV2, do tipo console.

- Criar a janela para apresentação das mensagens descendente de JDialog 

-  Definir a Thread de preenchimento assíncrono, com o nome ThreadClient
   
- Gerar Relatório discente de acompanhamento (RDA Part2.pdf) 

## Ativando o projeto parte 2

  - Essa parte do trabalho deveria estar dividido em dois projetos porem devido a um problema não superado que foi a grande dificulde em se ter cadastro do movimento juntamente com a opção de listagem utilizando no mesmo objeto, mais especificamente em "Object receivedObject", na thread server foi dividida em duas threads servers sendo uma para listagem e verificação do usuario e outra para cadastro. Sendo assim a terceira foi para cliente que tem todas essas opções.

  - Outro ponteo foi que essa solução fez com que o numero de conexões ao banco aumentasse e devido a isso surgiu problema de limitação de conexões ao sql server express. A solução foi adotar o postgres, que funcionou sem problemas.

      - Ativando Server 1

   ![image](Parte2/assets/image1.png)  

      - Ativando Server 2

   ![image](Parte2/assets/image2.png)

      - Ativando Client 

   ![image](Parte2/assets/image3.png)

  
  ## Banco e sua conexão

    - Etapas de configuração do postgres
      
      - Necessária a existencia da instalação já ativada para que se faça a criação do 
      banco e execução do script das tabelas que pode ser encontrado na 
      pasta info conforme figura abaixo
     
      - pasta info

   ![image](Parte2/assets/image4.png)


      - criação do banco
      
   ![image](Parte2/assets/create%20db%20prostgress.png)

         - interface para geração dos scripts
      
   ![image](Parte2/assets/execute%20script%20table.png)

      - scripts a serem executados na sequencia
      
   ![image](Parte2/assets/image5.png)


      - configuração da conexão (para ambos os servers)

       - localização do persistence.xml
      
   ![image](Parte2/assets/image6.png)

       - configuração das credênciais, banco e porta
      
   ![image](Parte2/assets/image7.png)


  - Resutaldos esperados  
 
      - Autenticação via client
            
   ![image](Parte2/assets/etapa1%20login.png)
 
      - Listagem de produtos
            
   ![image](Parte2/assets/lista%20produtos.png)
    
      - Inclusão de movimento de entrada
            
   ![image](Parte2/assets/cadastro%20movimento%20E.png)

      - listagem contendo movimento de entrada
            
   ![image](Parte2/assets/resultado%20movimento%20E.png)

- Os relatórios RDAs podem ser encontrados na pasta raiz

![image](https://github.com/msbzz/estacio.m3.n5/assets/44148209/3c58710b-e2e9-475c-82f7-85ba5a72c9be)

 
- Observaçoes
   - A IDE utilizada foi Intellij IDEA Ultimate
   - O jdk utilizado foi o 17
   - A execução do trabalho esta dividido em duas partes
        
     
     ## Especificação
    
    https://sway.office.com/ertZuvZwxHwQpwAS?ref=Link&loc=play
   
