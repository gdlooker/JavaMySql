package gdchent.jdbc.sqlserver;

import gdchent.jdbc.JDBC;

/**
 * sqlserver厂商实现SUN公司的JDBC接口
 */
public class SqlServer implements JDBC {

    @Override
    public void getConnection() {
        System.out.println("Sql数据库连接成功");
    }

}
