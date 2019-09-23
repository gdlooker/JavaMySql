package gdchent.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * 杜大佬对Java数据库的封装
 * JDBC工具类
 */
public class DbUtil {

    private static FileReader fileReader;
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    /**
     * 工具类的构造方法一般都是私有的
     * 因为工具类当中的方法是静态的，不需要new对象
     */
    private DbUtil(){}


    //因为静态代码块只类加载器第一次加载的时候执行加载 只执行一次
    static {

        try {
            //数据库jdbc编程6步
            Properties properties=new Properties();
            URL urlObj =Thread.currentThread().getContextClassLoader().getResource("jdbc.properties");
            String path=urlObj.getPath();
            fileReader = new FileReader(path);
            properties.load(fileReader);
            //获取驱动String
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //获取连接
    public static Connection getConnection() throws Exception{
        return DriverManager.getConnection(url,user,password);
    }

    //关闭 释放资源
    public static void close(Connection connection, Statement statement, ResultSet resultSet){
        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement!=null){
            try {

                statement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(connection!=null){
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }
}
