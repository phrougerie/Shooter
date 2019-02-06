package sample.Controller;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;

/**
 * Database controller
 * @author phrougerie
 */
public class BaseController {


    /**
     * Insert into database the score of the player
     * @param playStage
     * @param playRoot
     * @param score
     * @param num Number of the level
     * @param namePlayer
     * @throws ClassNotFoundException
     */
    public void insertScore(Stage playStage, Group playRoot, int score , int num, String namePlayer) throws ClassNotFoundException {


        Connection connection = null;


        try
        {
            String table = "scoreLevel"+num;
            String levelName = "LevelName";
            // create a database connection
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:levels.sqlite");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet verif = statement.executeQuery("Select count(*) from "+levelName+" where IdLevel='"+table+"' ");
            verif.next();
            if(verif.getInt(1)==0){
                statement.executeUpdate("insert into "+levelName+" values('"+table+"')");
            }

            statement.executeUpdate("create table if not exists "+table+" (IdScore int (5) PRIMARY Key,Score int (6) not null,UserName char(3) not null)");
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) from "+table+"");
            rs.next();
            int number = rs.getInt(1);
            rs.close();
            String a ="aaa";
            statement.executeUpdate("insert into "+table+" values("+number+","+score+",'"+namePlayer+"')");

            String tot ="";
            ResultSet rs2 =statement.executeQuery("SELECT  * from "+table+" order by Score desc limit 5");
            while(rs2.next())
            {
                tot+= rs2.getString("UserName")+"  "+rs2.getInt("Score")+"\n";
            }
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e);
            }
        }
    }


    /**
     * Return the 5 high scores of a level.
     * @param num Number of the Stage
     */
    public String highScoreLevel(int num){

        Connection connection = null;
        try {
            String table = "scoreLevel"+num;
            Statement statement = connection();
            ResultSet rs2 =statement.executeQuery("SELECT  * from "+table+" order by Score desc limit 5");
            String tot ="";
            while(rs2.next())
            {

                tot+= rs2.getString("UserName")+"  "+rs2.getInt("Score")+"\n";
            }

            return tot;



        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e);
            }
        }
        return null;

    }

    /**
     * Create table of high score
     */
    public void createTableLevels(){

        Connection connection = null;
        try {

            Statement statement = connection();
            //statement.executeUpdate("drop table LevelName");
            statement.executeUpdate("create table if not exists LevelName (IdLevel varchar (20) PRIMARY Key)");

        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e);
            }
        }

    }


    /**
     * @param idLevel number of the level
     * @return True if the table is not already created.
     */
    public boolean verifLevel(int idLevel){

        Connection connection = null;
        try {
            String table = "scoreLevel"+idLevel;


            Statement statement = connection();
            ResultSet verif = statement.executeQuery("SELECT  count(*) from LevelName where IdLevel='"+table+"'");
            verif.next();
            if(verif.getInt(1)==1){
                return true;
            }

        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e);
            }
        }
        return false;
    }


    /**
     * Allow connection to the DB.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private Statement connection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:levels.sqlite");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);
        return statement;

    }

    
}
