package model;

import java.io.Serializable;

public class PessoaJuridica extends Pessoa {
    private String cnpj;

    // Construtor padrão
    public PessoaJuridica() {}

    // Construtor completo, usando polimorfismo
    public PessoaJuridica(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cnpj) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cnpj = cnpj;
    }

    // Método para exibir os dados da pessoa jurídica

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String exibir() {
        super.exibir();
        return super.exibir() + "\nCNPJ: " + cnpj;
    }
}