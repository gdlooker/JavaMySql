package gdchent.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 这个程序负责修改被锁定的程序
 */
public class TestAlreadyLockedDbUtil {
    public static void main(String[] args) {

        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            connection=DbUtil.getConnection();
            connection.setAutoCommit(false);
            String sql="update gdchenttable set description=? where money =";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,"拒绝装逼"); //修改money为150的描述信息
            preparedStatement.setInt(2,150); //这个150必须要跟TestLockDbUtil这个文件的锁定money对应
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if(connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }finally {
            //关闭释放资源
            DbUtil.close(connection,preparedStatement,resultSet);
        }
    }
}
