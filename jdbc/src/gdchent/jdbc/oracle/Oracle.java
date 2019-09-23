package gdchent.jdbc.oracle;

import gdchent.jdbc.JDBC;

/**
 * Oracle厂商实现JDBC接口
 */
public class Oracle implements JDBC {
    @Override
    public void getConnection() {
        System.out.println("Oracle数据库连接成功");
    }
}


