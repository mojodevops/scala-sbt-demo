package cc.xstack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class XstackResourceUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(XstackResourceUtil.class);
    ClassLoader defaultClassLoader;
    ClassLoader systemClassLoader;

    XstackResourceUtil() {
        try {
            //初始化类加载器
            systemClassLoader = ClassLoader.getSystemClassLoader();
        } catch (SecurityException ignored) {
            // AccessControlException on Google App Engine
        }
    }

    public static void main(String[] args) {
        XstackResourceUtil XstackResourceUtil = new XstackResourceUtil();
        XstackResourceUtil.loadProperties1();//ClassLoader
        XstackResourceUtil.loadProperties2();//classLoader
        XstackResourceUtil.loadProperties3();//class
        XstackResourceUtil.loadProperties4();//class
        XstackResourceUtil.loadProperties5();//class
        XstackResourceUtil.loadProperties6();//mybatis中调用系统classLoader
        XstackResourceUtil.loadProperties7();//mybatis中调用系统classLoader
    }

    public void loadProperties1() {
        LOGGER.info("loadProperties1: ");
        InputStream input;
        try {
            input = XstackResourceUtil.class.getClassLoader().getResourceAsStream("resources/application.ini");
            printProperties(input);
        } catch (Exception e) {
            LOGGER.error("loadProperties1: ", e);
        }
    }

    public void loadProperties2() {
        LOGGER.info("loadProperties2: ");
        InputStream input;
        try {
            input = XstackResourceUtil.class.getClassLoader().getResourceAsStream("application.ini");
            printProperties(input);
        } catch (Exception e) {
            LOGGER.error("loadProperties2: ", e);
        }
    }

    public void loadProperties3() {
        LOGGER.info("loadProperties3: ");
        InputStream input;
        try {
            input = XstackResourceUtil.class.getResourceAsStream("resources/application.ini");
            printProperties(input);
        } catch (Exception e) {
            LOGGER.error("loadProperties3: ", e);
        }
    }

    public void loadProperties4() {
        LOGGER.info("loadProperties4: ");
        InputStream input;
        try {
            input = XstackResourceUtil.class.getResourceAsStream("/application.ini");
            printProperties(input);
        } catch (Exception e) {
            LOGGER.error("loadProperties4: ", e);
        }
    }

    public void loadProperties5() {
        LOGGER.info("loadProperties5: ");
        InputStream input;
        try {
            input = XstackResourceUtil.class.getResourceAsStream("/resources/application.ini");
            printProperties(input);
        } catch (Exception e) {
            LOGGER.error("loadProperties5: ", e);
        }
    }

    public void loadProperties6() {
        LOGGER.info("loadProperties6: ");
        new ClassLoader() {
        };
        InputStream input;
        try {
            input = getResourceAsStream("resources/application.ini");
            printProperties(input);
        } catch (Exception e) {
            LOGGER.error("loadProperties6: ", e);
        }
    }

    public void loadProperties7() {
        LOGGER.info("loadProperties7: ");
        InputStream input;
        try {
            input = getResourceAsStream("application.ini");
            printProperties(input);
        } catch (Exception e) {
            LOGGER.error("loadProperties7: ", e);
        }
    }

    public InputStream getResourceAsStream(String resource) {
        return getResourceAsStream(null, resource);
    }

    public InputStream getResourceAsStream(ClassLoader classLoader, String resource) {
        return getResourceAsStream(resource, getClassLoaders(classLoader));
    }

    //用5个类加载器一个个查找资源，只要其中任何一个找到，就返回
    InputStream getResourceAsStream(String resource, ClassLoader[] classLoader) {
        for (ClassLoader cl : classLoader) {
            if (null != cl) {
                // try to find the resource as passed
                InputStream returnValue = cl.getResourceAsStream(resource);

                // now, some class loaders want this leading "/", so we'll add it and try again if we didn't find the resource
                if (null == returnValue) {
                    returnValue = cl.getResourceAsStream("/" + resource);
                }

                if (null != returnValue) {
                    return returnValue;
                }
            }
        }
        return null;
    }

    private void printProperties(InputStream input) throws IOException {
        Properties properties = new Properties();
        properties.load(input);
        System.out.println(properties.getProperty("name"));
    }

    //一共5个类加载器
    ClassLoader[] getClassLoaders(ClassLoader classLoader) {
        return new ClassLoader[]{
                classLoader,
                defaultClassLoader,
                Thread.currentThread().getContextClassLoader(),
                getClass().getClassLoader(),
                systemClassLoader};
    }
}
