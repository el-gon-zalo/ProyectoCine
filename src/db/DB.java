package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DB {
    private static DB instance;

    public static DB getInstance() throws SQLException {
        if(instance == null || instance.getConnection().isClosed())
            instance = new DB();

        return instance;
    }

    private Connection conn;

    //Estos datos de conexión se usaron cuando intenté conectarme a ORACLE SQL DATA MODELER.

    /*private static final  String url = "jdbc:oracle:thin:@dbcinema_high?TNS_ADMIN=C:/Users/Usuario/Desktop/Wallet_DBcinema/";
    private static final  String user = "ADMIN";
    private static final  String pass = "OracleCloud123";

    public DB() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", pass);
        props.setProperty("oracle.net.tns_admin", "C:/Users/Usuario/Desktop/Wallet_DBcinema/");
        props.setProperty("oracle.net.wallet_location", "C:/Users/Usuario/Desktop/Wallet_DBcinema/");

        conn = DriverManager.getConnection(url, props);
    }
    */

    private static final  String url = "jdbc:mysql://localhost:3306/dbcinema";
    private static final  String user = "root";
    private static final  String pass = "";

    public DB() throws SQLException {
        conn = DriverManager.getConnection(url, user, pass);
    }

    public Connection getConnection() {
        return conn;
    }
}

