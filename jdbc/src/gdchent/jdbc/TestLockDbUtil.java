package gdchent.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 这个程序开启一个事务，这个事务专门进行查询，并且使用行级锁/悲观锁，锁住相关记录
 */
public class TestLockDbUtil {
    public static void main(String[] args) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            connection=DbUtil.getConnection();
            //手动开启事务
            connection.setAutoCommit(false);

            String sql="select name,age from gdchenttable where money =? for update"; //悲观锁
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,150); //money=150的记录不能改变了

            resultSet=preparedStatement.executeQuery() ;//执行查询
            while ((resultSet.next())){
                System.out.println(resultSet.getString("name")+","+resultSet.getString("age"));
            }
            //手动提交事务
            connection.commit();
        }catch (SQLException e) {
            e.printStackTrace();
            if(connection!=null){
                try {
                    //回滚事务
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
