package model.util;

import java.sql.*;

public class ConectorBD {

    private static final String URL = "jdbc:sqlserver://localhost:1434;databaseName=loja;encrypt=true;trustServerCertificate=true";
    private static final String USER = "loja";
    private static final String PASSWORD = "loja";

    private Connection connection;

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    public PreparedStatement getPrepared(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }

    public ResultSet getSelect(String sql) throws SQLException {
        PreparedStatement stmt = getPrepared(sql);
        return stmt.executeQuery();
    }

    // Fechamento de recursos usando try-with-resources deve ser feito fora desta classe, como você já faz em PessoaFisicaDAO
    // Portanto, você não precisa destes métodos de fechamento.

    // No entanto, se você quiser fechar a conexão e reiniciá-la em algum ponto, você pode manter o método closeConnection()
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
