package model.dao;

import model.Pessoa;
import model.PessoaJuridica;
import model.util.ConectorBD;
import model.util.SequenceManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PessoaJuridicaDAO {

    private ConectorBD conectorBD;

    public PessoaJuridicaDAO() {
        conectorBD = new ConectorBD(); // considerar usar um pool de conexões aqui
    }

    public PessoaJuridica getPessoa(int id) {
        PessoaJuridica PessoaJuridica = null;
        String sql = "SELECT p.*, pf.cnpj FROM PessoaJuridica pf INNER JOIN Pessoa p ON p.id = pf.idpessoa WHERE p.id = ?";

        try (PreparedStatement stmt = conectorBD.getPrepared(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    PessoaJuridica = new PessoaJuridica(rs.getInt("id"), rs.getString("nome"), rs.getString("logradouro"),
                            rs.getString("cidade"), rs.getString("estado"), rs.getString("telefone"),
                            rs.getString("email"), rs.getString("cnpj"));
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        } finally {
            conectorBD.closeConnection();
        }

        return PessoaJuridica;
    }

    public PessoaJuridica getPessoa(String nome) {
        PessoaJuridica PessoaJuridica = null;
        String sql = "SELECT p.*, pf.cnpj FROM PessoaJuridica pf INNER JOIN Pessoa p ON p.id = pf.idpessoa WHERE p.nome like ?";

        try (PreparedStatement stmt = conectorBD.getPrepared(sql)) {
            stmt.setString(1, nome + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    PessoaJuridica = new PessoaJuridica(rs.getInt("id"), rs.getString("nome"), rs.getString("logradouro"),
                            rs.getString("cidade"), rs.getString("estado"), rs.getString("telefone"),
                            rs.getString("email"), rs.getString("cnpj"));
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        } finally {
            conectorBD.closeConnection();
        }

        return PessoaJuridica;
    }

    public List<PessoaJuridica> getPessoas(){
        List<PessoaJuridica> lstPessoaJuridica = new ArrayList<>();
        String sql = "SELECT p.*, pf.cnpj FROM PessoaJuridica pf INNER JOIN Pessoa p ON p.id = pf.idpessoa ORDER BY p.nome";

        try (
                PreparedStatement stmt = conectorBD.getPrepared(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                lstPessoaJuridica.add(new PessoaJuridica(rs.getInt("id"), rs.getString("nome"), rs.getString("logradouro"),
                        rs.getString("cidade"), rs.getString("estado"), rs.getString("telefone"),
                        rs.getString("email"), rs.getString("cnpj")));
            }
        } catch (SQLException e) {
            handleSQLException(e);
        } finally {
            conectorBD.closeConnection();
        }

        return lstPessoaJuridica;
    }
    SequenceManager sc= new SequenceManager();

    public boolean incluir(PessoaJuridica pessoa) {
        boolean result = false;
        String sql = "INSERT INTO Pessoa (id, nome, logradouro, cidade, estado, telefone, email,tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlPessoaJuridica = "INSERT INTO PessoaJuridica (idpessoa, cnpj) VALUES (?, ?)";

        int idNext = sc.getValue("PessoaIdSeq");

        try (PreparedStatement stmt = conectorBD.getPrepared(sql);
             PreparedStatement stmtPf = conectorBD.getPrepared(sqlPessoaJuridica)) {

            stmt.setInt(1, idNext);
            stmt.setString(2, pessoa.getNome());
            stmt.setString(3, pessoa.getLogradouro());
            stmt.setString(4, pessoa.getCidade());
            stmt.setString(5, pessoa.getEstado());
            stmt.setString(6, pessoa.getTelefone());
            stmt.setString(7, pessoa.getEmail());
            stmt.setString(8, "F");
            stmtPf.setInt(1, idNext);
            stmtPf.setString(2, pessoa.getCnpj());

            int r1=stmt.executeUpdate();
            int r2=stmtPf.executeUpdate();

            if(r1 > 0 && r2 > 0) {
                result = true;
            }

        } catch (SQLException e) {
            handleSQLException(e);
        } finally {
            conectorBD.closeConnection();
        }

        return result;
    }

    public boolean alterar(PessoaJuridica pessoa) {
        boolean result = false;
        String sql = "UPDATE Pessoa SET nome=?, logradouro=?, cidade=?, estado=?, telefone=?, email=? WHERE id=?";
        String sqlPessoaJuridica = "UPDATE PessoaJuridica SET cnpj=? WHERE idpessoa=?";

        try (PreparedStatement stmt = conectorBD.getPrepared(sql);
             PreparedStatement stmtPf = conectorBD.getPrepared(sqlPessoaJuridica)) {

            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getLogradouro());
            stmt.setString(3, pessoa.getCidade());
            stmt.setString(4, pessoa.getEstado());
            stmt.setString(5, pessoa.getTelefone());
            stmt.setString(6, pessoa.getEmail());
            stmt.setInt(7, pessoa.getId());


            stmtPf.setString(1, pessoa.getCnpj());
            stmtPf.setInt(2, pessoa.getId());

            if(stmt.executeUpdate() > 0 && stmtPf.executeUpdate() > 0) {
                result = true;
            }

        } catch (SQLException e) {
            handleSQLException(e);
        } finally {
            conectorBD.closeConnection();
        }

        return result;
    }

    public boolean excluir(int id) {
        boolean result = false;
        String sqlPessoaJuridica = "DELETE FROM PessoaJuridica WHERE idpessoa=?";
        String sql = "DELETE FROM Pessoa WHERE id=?";

        try (PreparedStatement stmtPf = conectorBD.getPrepared(sqlPessoaJuridica);
             PreparedStatement stmt = conectorBD.getPrepared(sql)) {

            stmtPf.setInt(1, id);
            stmt.setInt(1, id);

            // Deletando primeiro da tabela PessoaJuridica por causa da restrição de chave estrangeira.
            if(stmtPf.executeUpdate() > 0 && stmt.executeUpdate() > 0) {
                result = true;
            }

        } catch (SQLException e) {
            handleSQLException(e);
        } finally {
            conectorBD.closeConnection();
        }

        return result;
    }
    private void handleSQLException(SQLException e) {
        // Handle exceptions in a more sophisticated way, maybe logging and rethrowing
        e.printStackTrace();
    }

}

