package gdchent.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库事务 重点：
 *  比如2个账号之间进行转账 就要设置事务 要么同时成功要么同时失败,如果失败出现回滚现象
 */
public class JdbcTestCommit {

    public static void main(String ...args){

        Connection connection=null;
        PreparedStatement preparedStatement=null;
        int count=0;
        try {
            //数据库jdbc编程6步
            Properties properties=new Properties();
            URL urlObj =Thread.currentThread().getContextClassLoader().getResource("jdbc.properties");
            String path=urlObj.getPath();
            FileReader fileReader=new FileReader(path);
            properties.load(fileReader);
            //获取驱动String
            String driver=properties.getProperty("driver");
            String url=properties.getProperty("url");
            String user=properties.getProperty("user");
            String password=properties.getProperty("password");

            //第一步 注册驱动
            Class.forName(driver);
            //第二步获取链接对象
            connection=DriverManager.getConnection(url,user,password);

            //在做第三步之前先将自动提交修改为手动提交
            connection.setAutoCommit(false);// 不然后续执行这代码（preparedStatement.executeUpdate()）就修改了
            //step3 然后再执行第三步获取预编译对象
            //修改张三的账户金额
            String sql="update gdchenttable set money=? where name=? and id=?"; //要修改哪个对象
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,100+50); //索引从1开始 设置账户的值 +了
            preparedStatement.setString(2,"张三");
            preparedStatement.setInt(3,1);
            count=preparedStatement.executeUpdate(); //执行更新
//
//            sql=null;
//            sql.toString();   //设置bug中断事务
            //修改账户王麻子的金额
            preparedStatement.setInt(1,300-50);
            preparedStatement.setString(2,"王麻子");
            preparedStatement.setInt(3,3); //账号id
            count+=preparedStatement.executeUpdate();

            System.out.println(count==2?"转账成功":"转账失败");
            //前面设置了不自动提交 所以这里要手动提交
            connection.commit();
        }catch (SQLException e){
            if(connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            if(connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            if(connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            if(connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }catch (NullPointerException e){
            if(connection!=null){
                try {
                    connection.rollback();
                    System.out.println("劳资给你回滚金额了");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
