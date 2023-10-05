package services;

import java.util.Scanner;
import model.dao.Dao;
import model.PessoaFisica;
import model.PessoaJuridica;

import util.Util;

public class Acoes {
    private PessoaFisica pessoaFisica;
    private PessoaJuridica pessoaJuridica;
    private Scanner scanner = new Scanner(System.in);
    private String opcaoPessoa;
    private String opcaoAcao;
    static Integer idBusca = 0;
    private Util utl;
    private Dao dao;

    public Acoes(String opcaoPessoa, String opcaoAcao) {
        this.opcaoPessoa = opcaoPessoa;
        this.opcaoAcao = opcaoAcao;
        this.utl = new Util(opcaoPessoa, opcaoAcao);
        this.dao = new Dao(opcaoPessoa);
    }


    public void executandoAcoes() {
        String tpPessoa = (opcaoPessoa == "f") ? "Pessoa fisica" : "Pessoa juridica";
        switch (opcaoAcao) {
            case "I":
                System.out.println("criar nova registro " + tpPessoa);
                novo();
                break;
            case "A":
                System.out.println("editar dados " + tpPessoa);
                editar();
                break;
            case "R":
                System.out.println("excluindo dados " + tpPessoa);
                excluir();
                break;
            case "B":
                System.out.println("buscar dados by id " + tpPessoa);
                mostrarItemPorId();
                break;
            case "S":
                System.out.println("exibir todos dados " + tpPessoa);
                mostrarTodos();
                break;
            case "E":
                //sair(exit)
                System.out.println("sair !");
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Escolha inválida. Tente novamente.");
                utl.clickMe();
        }
    }
    private void mostrarItemPorId() {
        obterId();
        obtemDadosById();
    }
    private void editar() {
        obterId();
        alterarDados();


    }
    private void novo() {
        DadosPessoa();

    }
    private void excluir() {
        obterId();
        if(obtemDadosById()) {

            boolean inloop = true;

            while (inloop) {
                System.out.println("Confirma a exclusão do item apresentado(S/N) ?");
                String resp = scanner.nextLine();

                if (resp.equals("N") || resp.equals("n")) {
                    return;
                } else if (resp.equals("s") || resp.equals("S")) {
                    inloop = false;
                }
            }
            dao.excluir(idBusca);
        }
    }
    private void mostrarTodos() {
        dao.obterTodos();
        utl.clickMe();
    }
    private void DadosPessoa() {

        String nome = "";
        String logradouro="";
        String cidade="";
        String estado="";
        String telefone="";
        String email="";
        String cpf = "";
        String cnpj = "";

        if( ! opcaoAcao.equals("I")) {
            if (opcaoPessoa.equals("f")) {
                nome = pessoaFisica.getNome();
                logradouro=pessoaFisica.getLogradouro();
                cidade=pessoaFisica.getCidade();
                estado=pessoaFisica.getEstado();
                telefone=pessoaFisica.getTelefone();
                email=pessoaFisica.getEmail();
                cpf = pessoaFisica.getCpf();
            }else{
                nome = pessoaJuridica.getNome();
                logradouro=pessoaJuridica.getLogradouro();
                cidade=pessoaJuridica.getCidade();
                estado=pessoaJuridica.getEstado();
                telefone=pessoaJuridica.getTelefone();
                email=pessoaJuridica.getEmail();
                cnpj = pessoaJuridica.getCnpj();
            }
        }

        nome = utl.inputDadosText("Digite o nome (maximo 60 chars)",
                "Nome  - preencha no maximo 60 chars",nome,60);

        logradouro = utl.inputDadosText("Digite o logradouro (maximo 100 chars)",
                "Logradouro  - preencha  no maximo 100 chars",logradouro,100);

        cidade = utl.inputDadosText("Digite a cidade (maximo 60 chars)",
                "Cidade - preencha  no maximo 60 chars",cidade,60);

        estado = utl.inputDadosText("Digite o estado (maximo 2 chars)",
                "Estado - preencha 2 chars",estado,2);

        telefone = utl.inputDadosText("Digite o telefone (maximo 11 chars)",
                "Telefone - preencha  11 chars",telefone,11);

        email = utl.inputDadosText("Digite o email (maximo 60 chars)",
                "Email - preencha  no maximo 60 chars",email,60);


        if (opcaoPessoa.equals("f")) {
            cpf = utl.inputDadosText("Digite o cpf (maximo 11 chars)",
                    "o cpf precisa ser definido com no maximo 11 chars",
                    cpf,11 );

            //utl.clickMe();
         } else {
            // cnpj
            cnpj= utl.inputDadosText("Digite o cnpj (maximo 14 chars)",
                    "o cnpj precisa ser definid com no maximo 14 chars",
                    cnpj,14);

            //utl.clickMe();
         }

        if( opcaoAcao.equals("I")) {
            if (opcaoPessoa.equals("f")){
                pessoaFisica= new PessoaFisica(0,nome,logradouro,cidade,estado,telefone,email,cpf);
            }else{
                pessoaJuridica= new PessoaJuridica(0,nome,logradouro,cidade,estado,telefone,email,cnpj);
            }
        }else{
            if (opcaoPessoa.equals("f")){
                pessoaFisica.setNome(nome);
                pessoaFisica.setLogradouro(logradouro);
                pessoaFisica.setCidade(cidade);
                pessoaFisica.setEstado(estado);
                pessoaFisica.setTelefone(telefone);
                pessoaFisica.setEmail(email);
                pessoaFisica.setCpf(cpf);
            }else{
                pessoaJuridica.setNome(nome);
                pessoaJuridica.setLogradouro(logradouro);
                pessoaJuridica.setCidade(cidade);
                pessoaJuridica.setEstado(estado);
                pessoaJuridica.setTelefone(telefone);
                pessoaJuridica.setEmail(email);
                pessoaJuridica.setCnpj(cnpj);
             }
        }

        if( opcaoAcao.equals("I")) {
            if (opcaoPessoa.equals("f")){
                dao.inserirDados(pessoaFisica);
            }else{
                dao.inserirDados(pessoaJuridica);
            }

        }else{
            if (opcaoPessoa.equals("f")){
                dao.alterar(pessoaFisica);
            }else{
                dao.alterar(pessoaJuridica);
            }
        }

    }

    private void obterId() {
        //somente valido para alteraçao/exclusão/busca
        while (true) {
            try {
                System.out.println("Digite o id");
                idBusca = scanner.nextInt();
                scanner.nextLine();
                return;
            } catch (RuntimeException e) {
                System.out.println("Id inválido,valor deve ser numerico. Tente novamente");
                utl.clickMe();
            }
        }
    }
    private void alterarDados() {

        if (obtemDadosById()) {
            System.out.println("=============================================================================");
            System.out.println("Caso deseja manter o valor original, tecle [enter] para seguir o proximo item");
            System.out.println("=============================================================================");
            //utl.clickMe();
            DadosPessoa();
        }

    }
    private boolean obtemDadosById() {

        boolean pessoaValida=false;
        Integer id=idBusca;

        if (opcaoPessoa == "f") {
            pessoaFisica = dao.obterPessoaFisica(id);
            if (pessoaFisica==null){
                System.out.println("Pessoa fisica não encontrada");
            }else{
                System.out.println(pessoaFisica.exibir());
                pessoaValida=true;
            }
        } else {
            pessoaJuridica = dao.obterPessoaJuridica(id);
            if (pessoaJuridica==null){
                System.out.println("Pessoa juridica não encontrada");
            }else{
                System.out.println(pessoaJuridica.exibir());
                pessoaValida=true;
            }

        }

        return pessoaValida;

    }

}
