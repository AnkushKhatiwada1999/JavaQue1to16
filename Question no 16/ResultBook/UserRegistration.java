
import Helpers.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRegistration {
    private Connection con;

    public UserRegistration() {
        try {
            this.con = DBUtils.getDbConnection();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

    }

    ResultSet get() {
        try {
            String select = "SELECT * from users";
            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();
        } catch (SQLException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    void insert(String name, String address, String gender, String positive) {
        try {
            String insert = "INSERT INTO users (name, address, gender, covid19)" + "VALUES(?,?,?,?)";
            PreparedStatement statement = this.con.prepareStatement(insert);
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, gender);
            statement.setString(4, positive);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException var7) {
            var7.printStackTrace();
        }
    }
    void update(int id,String name,String address, String gender, String positive){
        try{
            String update = "UPDATE users SET name= ?, address=?, gender=?, covid19=? where id=?";
            PreparedStatement statement=con.prepareStatement(update);
            statement.setString(1,name);
            statement.setString(2,address);
            statement.setString(3,gender);
            statement.setString(4,positive);
            statement.setInt(5,id);
            statement.executeUpdate();
            statement.close();

        }
        catch(SQLException x){
            x.printStackTrace();
        }

    }
    void delete(int id){
        String delete="DELETE FROM users where id=?";
        try{
            PreparedStatement statement=con.prepareStatement(delete);
            statement.setInt(1,id);
            statement.execute();
            statement.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

    }

}

