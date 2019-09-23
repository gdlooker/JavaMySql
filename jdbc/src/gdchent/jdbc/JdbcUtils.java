package gdchent.jdbc;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class JdbcUtils {

    private static String url;
    private static String user;
    private static String password;
    private static String driver;
    static {
        try {
            // 1 创建一个Properties集合类
            Properties properties=new Properties();

            URL urlObj=Thread.currentThread().getContextClassLoader().getResource("jdbc.properties");
            String path=urlObj.getPath();
            System.out.println("获取getPath"+path);
            //2 加载文件
            FileReader fileReader=new FileReader(path);
            properties.load(fileReader);
            //3 获取数据 赋值
            url=properties.getProperty("url");
            user=properties.getProperty("user");
            password=properties.getProperty("password");
            driver=properties.getProperty("driver");
            System.out.println("url:"+url);
            System.out.println("user:"+user);
            System.out.println("password:"+password);
            System.out.println("driver:"+driver);
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public JdbcUtils() {
    }
}
