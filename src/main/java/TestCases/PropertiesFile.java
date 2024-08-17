package TestCases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertiesFile {

    private static Properties properties;
    private static FileInputStream fileIn;
    private static FileOutputStream fileOut;

    static String projectPath = System.getProperty("user.dir") + "/";
    private static String propertiesFilePathRoot = "src/test/resources/configs.properties";

    public static void setPropertiesFile() {
        properties = new Properties();
        try {
            //Khởi tạo giá trị cho đối tượng của class FileInputStream
            fileIn = new FileInputStream(projectPath + propertiesFilePathRoot);
            //Load properties file
            properties.load(fileIn);
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            System.out.println(exp.getCause());
            exp.printStackTrace();
        }
    }

    public static String getPropValue(String KeyProp) {
        String value = null;
        try {
            //get values from properties file
            value = properties.getProperty(KeyProp);
            System.out.println(value);
            return value;
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            System.out.println(exp.getCause());
            exp.printStackTrace();
        }
        return value;
    }

    public static void setPropValue(String KeyProp, String Value) {
        try {
            //Khởi tạo giá trị cho đối tượng của class FileOutputStream
            fileOut = new FileOutputStream(projectPath + propertiesFilePathRoot);
            //Load properties file hiện tại và thực hiện mapping value với key tương ứng
            properties.setProperty(KeyProp, Value);
            //Lưu key và value vào properties file
            properties.store(fileOut, "Set new value in properties file");
            System.out.println("Set new value in file properties success.");
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            System.out.println(exp.getCause());
            exp.printStackTrace();
        }
    }

}
