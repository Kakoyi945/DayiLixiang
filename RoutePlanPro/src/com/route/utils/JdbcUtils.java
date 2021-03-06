package com.route.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static {
        try{
            Properties properties = new Properties();

            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");

            properties.load(inputStream);

            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection conn = conns.get();
        if(conn==null){
            try{
                conn = dataSource.getConnection();
                conns.set(conn);
                conn.setAutoCommit(false);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void commitAndClose(){
        Connection connection = conns.get();
        if(connection != null){
            try{
                connection.commit();
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        conns.remove();
    }

    public static void rollbackAndClose(){
        Connection connection  = conns.get();
        if(connection != null){
            try{
                connection.rollback();
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        conns.remove();
    }

}
