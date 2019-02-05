package sample.Database;

import com.mysql.cj.exceptions.CJOperationNotSupportedException;
import sample.model.Task;
import sample.model.User;

import java.sql.*;

public class DatabaseHandler extends config {
    Connection bdConnect;

    public Connection getBdConnect() throws ClassNotFoundException, SQLException {
        String ConnectionString = "jdbc:mysql://" + dbhost + ":"
                + dbport + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");

        bdConnect = DriverManager.getConnection(ConnectionString, dbuser, dbpassword);
        return bdConnect;
    }

    //Write to db
    public void SignUpUpser(User user) {

        String insert = "INSERT INTO " + Const.Users_Table + "(" + Const.Users_FirstName + "," + Const.Users_LastName + ","
                + Const.Users_UserName + "," + Const.Users_Password + "," + Const.Users_Location + "," + Const.Users_Gender + ")" +
                "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getBdConnect().prepareStatement(insert);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getLocation());
            preparedStatement.setString(6, user.getGender());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getuser(User user) {
        ResultSet result = null;
        //select all from users where username ="?" and password ="?"

        if (!user.getUserName().equals("") || !user.getPassword().equals("")) {
            String query = "SELECT * FROM " + Const.Users_Table + " WHERE " + Const.Users_UserName + "=?"
                    + " AND " + Const.Users_Password + "=?";

            try {
                PreparedStatement preparedStatement = getBdConnect().prepareStatement(query);
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getPassword());
                result = preparedStatement.executeQuery();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("please enter credentials");

        }
        return result;
    }

    public void insertTask(Task task) {
        String insert = "INSERT INTO " + Const.Task_Table + "(" + Const.Users_Id + "," + Const.Task_date + ","
                + Const.Task_discription + "," + Const.Task_task + ")" + "VALUES(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getBdConnect().prepareStatement(insert);

            preparedStatement.setInt(1, task.getUserID());
            preparedStatement.setTimestamp(2, task.getDatecreated());
            preparedStatement.setString(3, task.getDiscription());
            preparedStatement.setString(4, task.getTask());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public int getallTask(int userid) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM " + Const.Task_task + " WHERE " + Const.Users_Id + "=?";


        PreparedStatement preparedStatement = getBdConnect().prepareStatement(query);
        preparedStatement.setInt(1, userid);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return resultSet.getInt(1);
    }

    public ResultSet getTaskbyUser(int userid){

        ResultSet resulttask = null;

        String query = "SELECT * FROM " + Const.Task_Table + " WHERE " + Const.Users_Id + "=?";
        try {
            PreparedStatement preparedStatement = getBdConnect().prepareStatement(query);
            preparedStatement.setInt(1,userid);
            resulttask = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return resulttask;
    }

    public void deleteTask(int userid, int taskid) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM " + Const.Task_Table + " WHERE " + Const.Users_Id + "=?"
                + " AND "+ Const.Task_ID + "=?";

        PreparedStatement preparedStatement = getBdConnect().prepareStatement(query);
        preparedStatement.setInt(1,userid);
        preparedStatement.setInt(2,taskid);

        preparedStatement.execute();
        preparedStatement.close();

    }

    public void UpdateTask(Timestamp timestamp, String description, String task, int taskid) throws SQLException, ClassNotFoundException {

        String update = "UPDATE "+ Const.Task_Table + " SET "+ Const.Task_date+ " =? "+
                ", "+ Const.Task_discription+ " =? "+ ", "+ Const.Task_task+ " =? "+ " WHERE "+
                Const.Task_ID+ "=?";

        PreparedStatement preparedStatement = getBdConnect().prepareStatement(update);

        preparedStatement.setTimestamp(1, timestamp);
        preparedStatement.setString(2, description);
        preparedStatement.setString(3, task);
        preparedStatement.setInt(4, taskid);
        preparedStatement.executeUpdate();
    }

}



