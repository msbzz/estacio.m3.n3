import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteConexao {

    public static void main(String[] args) {
        // Substitua "usuario" e "senha" pelas suas credenciais de acesso ao banco de dados
        String url = "jdbc:sqlserver://localhost:1434;databaseName=loja;encrypt=true;trustServerCertificate=true";
        String usuario = "loja";
        String senha = "loja";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha)) {
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
