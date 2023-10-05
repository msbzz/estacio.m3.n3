import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;

        ResultSet rs = null;
        ResultSet rs2 = null;

        try{
            conn = DB.getConnection();
            st = conn.createStatement();
            rs=st.executeQuery("SELECT * FROM PessoaFisica");

            while(rs.next()){
                System.out.println(rs.getInt("idPessoa"));
            }

            System.out.println("------------------------------------------------") ;

            rs2=st.executeQuery("SELECT * FROM PessoaFisica");

            while(rs2.next()){
                System.out.println(rs2.getInt("idPessoa"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}