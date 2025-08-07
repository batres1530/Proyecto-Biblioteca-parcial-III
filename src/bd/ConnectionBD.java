package bd;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/BDBiblioteca";
    private static final String USUARIO = "SDBM15";
    private static final String PASSWORD = "Davidmartinez1530.";

    private static HikariDataSource dataSource;
    static{
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USUARIO);
        config.setPassword(PASSWORD);
        config.setMaximumPoolSize(10); // Máximo de conexiones simultaneas
        config.setMinimumIdle(5); // Mínimo de Conexiones en espera
        config.setIdleTimeout(3000); // Tiempo de inactividad antes de colocar la conexión en espera
        config.setMaxLifetime(20000); // Tiempo de vida máximo de una conexión
        config.setConnectionTimeout(10000); // Timeout para obtener conexión
        
        dataSource = new HikariDataSource(config);
    }
    
    public static Connection getConnection() throws SQLException {
    return dataSource.getConnection();
    }

    public static void closePool() {
    if(dataSource != null){
        dataSource.close();
    }
}


}
