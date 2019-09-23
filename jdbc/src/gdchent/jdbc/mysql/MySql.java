package gdchent.jdbc.mysql;

import gdchent.jdbc.JDBC;

/**
 * mysql厂商实现SUN公司的JDBC
 */
public class MySql implements JDBC {
    @Override
    public void getConnection() {
        System.out.println("mysql数据库接口");
    }

}
