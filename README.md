## Estacio| Missão Prática | Nível 3 | Mundo 3

## Descrição

Modelagem e implementação de um banco de dados simples,utilizando como base o SQL Server.
     

### Objetivos da prática

  - Implementar persistência com base no middleware JDBC.
  - Utilizar o padrão DAO (Data Access Object) no manuseio de dados.
  - Implementar o mapeamento objeto-relacional em sistemas Java.
  - Criar sistemas cadastrais com persistência em banco relacional.
   
### Procedimentos

- Os procedimentos são divididos em duas etapas

  -1º Procedimento | Mapeamento Objeto-Relacional e DAO
     -   Gerar Relatório discente de acompanhamento (RDA Part1.pdf) 
      
   
 -  2º Procedimento | Criação do Cadastro em Modo Texto    
     -   Gerar Relatório discente de acompanhamento (RDA Part2.pdf)
    
  - Execução e verificação dos resultados
    - Os scripts devem ser gerados na ordem descrita onde estarão realizando a criação do banco,usuario, assim como a carga com dados fictcios (mock data)
    - os scripts serão encontrados na pasta "scripts" e estão descritos na sequencia que devem ser executados
       - Criação do banco loja e usuario loja
          - 01_Pratica.m3,n2.part1 banco user.sql
       - Criação de todas as tabelas
          - 02_Pratica.m3.n2.part2 create tbs views.sql
       - Criando usuarios pessao fisica e juridica
          - 03_Pratica.m3,n2.part3 insert tbs pessoas usuario.sql
       - Criando Produtos
          - 04_Pratica.m3,n2.part4 prod.sql
       - Criando movimentos de entrada 
          - 05_Pratica.m3,n2.part5 mov E.sql
       - Criando movimentos de saida
          - 06_Pratica.m3,n2.part6 mov S.sql
      - Inserindo vendas extras 
          - 07_Compras Extras part8.sql
      - Inserindo compras extras
          - 08_Vendas Extrass part7.sql

     ![image](https://github.com/msbzz/estacio.m3.n2/assets/44148209/f6cb17bd-ec42-4092-bfff-7635eaea8870)

    obs1: apenas o primero script deve ser utilizado com sa, os seguintes é necessário que se esteja logado com usuario "loja"


 - Os relatórios podem ser encontrados na pasta raiz

    ![image](https://github.com/msbzz/estacio.m3.n2/assets/44148209/918b82ca-3055-4bb4-91c2-7de81782f761)


     
     ## Especificação
       https://sway.office.com/LgKHUsnFtAiNx48i?ref=Link&loc=play
    
   
