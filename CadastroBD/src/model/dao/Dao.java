package model.dao;


import model.PessoaFisica;
import model.PessoaJuridica;


import java.util.List;

public class Dao {

    private static PessoaFisicaDAO repo1 = new PessoaFisicaDAO();
    private static PessoaJuridicaDAO repo2 = new PessoaJuridicaDAO();
    private String opcaoPessoa;

    public Dao(String opcaoPessoa) {
        this.opcaoPessoa = opcaoPessoa;
    }

    public void inserirDados(PessoaJuridica pessoa) {

        repo2.incluir(pessoa);
    }

    public void inserirDados(PessoaFisica pessoa) {
            repo1.incluir(pessoa);
    }

    public void excluir(int id){
        if (opcaoPessoa == "f") {
            excluirFisica(id);
        }else{
            excluirJuridica(id);
        }
    }
    private void excluirFisica(int id) {
        repo1.excluir(id);
    }

    private void excluirJuridica(int id) {
        repo2.excluir(id);
    }

    public PessoaFisica obterPessoaFisica(int id) {
              return repo1.getPessoa(id);
    }

    public PessoaJuridica obterPessoaJuridica(int id) {
        return repo2.getPessoa(id);
    }

    public void obterTodos() {
        if (opcaoPessoa == "f") {
            List<PessoaFisica> lPessaFisica = repo1.getPessoas();
            imprimeTodosFisica(lPessaFisica);
        } else {
            List<PessoaJuridica> lPessaJuridica = repo2.getPessoas();
            imprimeTodosJuridica(lPessaJuridica);
        }
    }

    public void alterar(PessoaFisica pessoa) {
            repo1.alterar(pessoa);
    }
    public void alterar(PessoaJuridica pessoa) {
        repo2.alterar(pessoa);
    }

    private  void imprimeTodosFisica(List<PessoaFisica> lista) {
        String sReturn="";
        for (PessoaFisica p: lista) {
            sReturn=  sReturn + (p.exibir()) +"\n" + " ---- "+"\n" ;
        }
        System.out.println(sReturn);
    }

    private void imprimeTodosJuridica(List<PessoaJuridica> lista) {
        String sReturn="";
        for (PessoaJuridica p : lista) {
            sReturn= sReturn + (p.exibir()) +"\n" + " ---- "+"\n" ;
        }
        System.out.println(sReturn);
    }

}
