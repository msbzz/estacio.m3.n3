import model.PessoaJuridica;
import model.dao.PessoaJuridicaDAO;

import java.util.List;

public class CadastroBD_Part1_Juridica {
    public static void main(String[] args) {

       missoPraticaPessoaJuridica();
    }


    private static void missoPraticaPessoaJuridica(){
        PessoaJuridicaDAO  pessoaJuridicaDAO  = new PessoaJuridicaDAO ();
        Boolean ret=false;
        List<PessoaJuridica> lstPessoaJuridica ;
         /*
         a. Instanciar uma pessoa juridica e persistir no banco de dados.
         */


        System.out.println("");
        System.out.println("");

        System.out.println("a. Instanciar uma pessoa juridica e persistir");

        System.out.println("");

        PessoaJuridica pjNw  = new PessoaJuridica();
        pjNw .setNome("Comercio e importação S/A");
        pjNw .setCnpj("30002645999194");
        pjNw .setEstado("SP");
        pjNw .setCidade("São Paulo");
        pjNw .setLogradouro("Av Paulista em Manhattan Bank, s/n");
        pjNw .setTelefone("(11)2746222");
        pjNw .setEmail("contato@imporcom.com");

        ret= pessoaJuridicaDAO.incluir(pjNw);

        if(ret){
            System.out.println("Pessoa juridica incluida com sucesso");
            System.out.println("");
            pjNw  =pessoaJuridicaDAO .getPessoa("Com");
            System.out.println(pjNw.exibir());
        }

        //b. Alterar os dados da pessoa juridica no banco.
        System.out.println("");
        System.out.println("b. Alterar os dados da Pessoa juridica");
        pjNw .setLogradouro("Av Rio Branco , centro rj, s/n");
        pjNw .setCidade("Rio de Janeiro");
        pjNw .setEstado("RJ");
        pjNw .setTelefone("(21)2445797");
        ret= pessoaJuridicaDAO.alterar(pjNw);
        System.out.println("");
        if (ret){
            System.out.println("Pessoa juridica alterada com sucesso !");
            System.out.println("");
            PessoaJuridica pj1= pessoaJuridicaDAO.getPessoa("Com");
            System.out.println("-------");
            System.out.println(pj1.exibir());
            System.out.println("-------");
        }else{
            System.out.println("Pessoa juridica não pode ser removida");
        }

        /* c.Consultar todas as pessoas juridicas do banco de dados */
        System.out.println("");
        System.out.println("c. Consultar todas as pessoas Juridicas");



        lstPessoaJuridica = pessoaJuridicaDAO .getPessoas();

        for ( PessoaJuridica p :lstPessoaJuridica) {
            System.out.println("-------");
            System.out.println(p.exibir());
        }

        //d. Excluir a pessoa juridica criada anteriormente no banco
        PessoaJuridica pj2 =pessoaJuridicaDAO .getPessoa("Com");
        if(pj2==null){
            System.out.println("Pessoa juridica não encontrada");
        }else{
            ret=pessoaJuridicaDAO .excluir(pj2.getId());
            if (ret){
                System.out.println("");
                System.out.println("Pessoa juridica removida com sucesso !");

                lstPessoaJuridica = pessoaJuridicaDAO .getPessoas();
                for ( PessoaJuridica p :lstPessoaJuridica) {
                    System.out.println("-------");
                    System.out.println(p.exibir());
                }
            }else{
                System.out.println("Pessoa juridica não pode ser removida");
            }
            System.out.println("");

        }


    }
}


