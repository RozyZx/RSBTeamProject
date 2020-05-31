package nightfury;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBCon {
	/*
	  web-info mail 'zgkodoqkndcemeyjyu@awdrt.net' pass '
	 */
	
	private final String password = "rsbk_survey";
    private final String user = "rsbk_survey";
    private final String database_name = "rsbk_survey";
    private final String url_base = "db4free.net:3306/";
    public Connection c;
    Statement script;
    
    public DBCon(){
        try {
            System.out.println("db connection started");
            c = DriverManager.getConnection(
                    "jdbc:mysql://"+
                            url_base+
                            database_name+
                            "?zeroDateTimeBehavior=CONVERT_TO_NULL",
                    user,
                    password);
            script = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            System.out.println("db connected");
        } catch (SQLException ex) {
            Logger.getLogger(DBCon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
