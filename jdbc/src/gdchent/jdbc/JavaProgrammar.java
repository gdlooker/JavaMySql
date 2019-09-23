package gdchent.jdbc;

import gdchent.jdbc.mysql.MySql;
import gdchent.jdbc.oracle.Oracle;
import gdchent.jdbc.sqlserver.SqlServer;

import java.util.ResourceBundle;

/**
 * Java程序员角色
 * java程序员无需关注是哪个品牌的数据库，只需要实现jdbc接口写代码
 */
public class JavaProgrammar {

    public static void main(String ... args){
        //这是第一种方式实现接口
         //JDBC jdbc=new MySql();
         //JDBC jdbc=new SqlServer();
        try {
            ResourceBundle bundle=ResourceBundle.getBundle("jdbc");
            String name=bundle.getBaseBundleName();
            System.out.println("name:"+name);
            String className=bundle.getString("className");
            System.out.println("输出bundle获取的文件属性");
            Class clazz=Class.forName(className);
            JDBC jdbc = (JDBC) clazz.newInstance();
            jdbc.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

}
