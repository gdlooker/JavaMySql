package gdchent.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 这个程序有2个任务
 *  第一： 测试DbUtil是否好用
 *  第二：模糊查询怎么写？
 *  看查询like关键字
 */
public class TestDbUtil {

    public static void main(String[] args) {

        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            //获取连接对象
            connection=DbUtil.getConnection();
            String sql="select name from gdchenttable where money like ?"; //like是模糊查询
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,150);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){

                System.out.println("name:"+resultSet.getString("name"));
            }
        }catch (Exception e){

        }
    }
}
