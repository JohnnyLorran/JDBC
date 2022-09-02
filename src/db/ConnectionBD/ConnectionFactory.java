package db.ConnectionBD;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionFactory {

    public DataSource dataSource;

    public ConnectionFactory() {
        try {
            ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
            comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC");
            comboPooledDataSource.setUser("johnny");
            comboPooledDataSource.setPassword("admin");

            //Tamanho máximo de conexões abertas
            comboPooledDataSource.setMaxPoolSize(15);
            this.dataSource = comboPooledDataSource;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Connection newConnectionBD() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
