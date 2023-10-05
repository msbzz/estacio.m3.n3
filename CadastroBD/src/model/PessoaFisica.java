package model;

import java.io.Serializable;

public class PessoaFisica extends Pessoa {
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // Construtor padrão
    public PessoaFisica() {}

    // Construtor completo, usando polimorfismo
    public PessoaFisica(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cpf) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cpf = cpf;
    }

    // Método para exibir os dados da pessoa física
    @Override
    public String exibir() {
        return super.exibir() + "\nCPF: " + cpf;
    }


}
