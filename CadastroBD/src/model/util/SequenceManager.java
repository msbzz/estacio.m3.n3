package model.util;

 ;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceManager {

    private  ConectorBD conectorBD= new ConectorBD(); // considerar usar um pool de conexões aqui;
    /*
    public static int getValue2(String sequenceName) {

        int nextValue = -1;
        String sql = "SELECT NEXT VALUE FOR " + sequenceName; // Este SQL dependerá da sintaxe do banco de dados
        try (ResultSet resultSet = conectorBD.getSelect(sql)) {
            if (resultSet.next()) {
                nextValue = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextValue;
    }
    */
    public int getValue(String sequenceName) {
        int nextValue = -1;
        String sql = "SELECT NEXT VALUE FOR " + sequenceName; // Este SQL dependerá da sintaxe do banco de dados

        try (ResultSet resultSet = conectorBD.getSelect(sql)) {
            if (resultSet.next()) {
                nextValue = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            conectorBD.closeConnection();
        }
        return nextValue;
    }

}

