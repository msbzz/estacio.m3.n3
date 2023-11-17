import model.PessoaFisica;
import model.dao.PessoaFisicaDAO;

import java.util.List;

public class CadastroBD_Part1_Fisica {
    public static void main(String[] args) {
       missoPraticaPessoaFisica();

    }

    private static void missoPraticaPessoaFisica(){
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        Boolean ret=false;
        List<PessoaFisica> lstPessaoFisica ;
         /*
         a. Instanciar uma pessoa física e persistir no banco de dados.
         */


        System.out.println("");
        System.out.println("");

        System.out.println("a. Instanciar uma pessoa física e persistir");

        System.out.println("");

        PessoaFisica pfNw = new PessoaFisica();
        pfNw.setNome("Virgulino da Silva2");
        pfNw.setCpf("04392077039");
        pfNw.setEstado("RJ");
        pfNw.setCidade("Rio de Janeiro");
        pfNw.setLogradouro("Av Atlantica em frente a praia, s/n");
        pfNw.setTelefone("(21)4499444");
        pfNw.setEmail("virgulino2@gmail.com");

        ret= pessoaFisicaDAO.incluir(pfNw);

        if(ret){
            System.out.println("Pessoa Fisica incluida com sucesso");
            System.out.println("");
            pfNw =pessoaFisicaDAO.getPessoa("Virgulino");
            System.out.println(pfNw.exibir());
        }

        //b. Alterar os dados da pessoa física no banco.
        System.out.println("");
        System.out.println("b. Alterar os dados da pessoa fisica");
        pfNw.setLogradouro("Av Vieira souto , fundos, s/n");
        pfNw.setTelefone("(21)9999884");
        ret= pessoaFisicaDAO.alterar(pfNw);
        System.out.println("");
        if (ret){
            System.out.println("Pessoa fisica alterada com sucesso !");
            System.out.println("");

            PessoaFisica pf1= pessoaFisicaDAO.getPessoa("Virgulino");
            System.out.println("-------");
            System.out.println(pf1.exibir());
            System.out.println("-------");

        }else{
            System.out.println("Pessoa fisica não pode ser alterada");
        }

        /* c.Consultar todas as pessoas físicas do banco de dados */
        System.out.println("");
        System.out.println("-------");
        System.out.println("c. Consultar todas as pessoas físicas");
        lstPessaoFisica = pessoaFisicaDAO.getPessoas();
        for ( PessoaFisica p :lstPessaoFisica) {

            System.out.println(p.exibir());
        }
        System.out.println("-------");

        //d. Excluir a pessoa física criada anteriormente no banco
        PessoaFisica pf2 =pessoaFisicaDAO.getPessoa("Virgulino");
        if(pf2==null){
            System.out.println("Pessoa fisica não encontrada");
        }else{
            ret=pessoaFisicaDAO.excluir(pf2.getId());
            if (ret){
                System.out.println("");
                System.out.println("Pessoa fisica removida com sucesso !");
                lstPessaoFisica =null;
                lstPessaoFisica = pessoaFisicaDAO.getPessoas();
                for ( PessoaFisica p :lstPessaoFisica) {
                    System.out.println("-------");
                    System.out.println(p.exibir());
                }
            }else{
                System.out.println("Pessoa fisica não pode ser removida");
            }
            System.out.println("");

        }


    }

}


