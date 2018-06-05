package org.superl.exchange.CBitmexTrade.utils;

import lombok.extern.log4j.Log4j;
import org.superl.exchange.CBitmexTrade.beans.TradeData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/*
    CBitmexTrade
    Created by superl on 2018/6/5.
                                                                      00000
                                                                      00000
                                                                      00000
      00000000    00000  00000  00000000000     00000000    000000000 00000
     00000000000  00000  00000  000000000000   00000000000  000000000 00000
     00000  000   00000  00000  000000 00000  000000 00000  00000000  00000
     000000000    00000  00000  00000   0000  0000000000000 000000    00000
      0000000000  00000  00000  00000   00000 0000000000000 00000     00000
         0000000  00000  00000  00000   00000 00000         00000     00000
     00000  0000  000000000000  000000000000  000000000000  00000     00000
     00000000000  000000000000  000000000000   00000000000  00000     00000
      000000000   0000000000    00000000000     00000000    00000     00000
                                00000
                                00000                 Team:bbs.nepenthes.cn
                                00000
*/
@Log4j
public class MysqlDB {

    private static final String MYSQL_URL = "jdbc:mysql://127.0.0.1:3306/hedata?serverTimezone=UTC&characterEncoding=utf8&useSSL=false&autoReconnect=true&failOverReadOnly=false";
    private static final String MYSQL_NAME = "user";
    private static final String MYSQL_PASSWORD = "password";
    private static Connection conn = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(MYSQL_URL,MYSQL_NAME, MYSQL_PASSWORD);
            log.debug("Start Connect Mysql Database...");
        } catch (ClassNotFoundException e) {
            log.debug("Connect Mysql Database Error..."+e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            log.debug("Connect Mysql Database Error..."+e.getMessage());
            e.printStackTrace();
        }
    }

    //构造方法
    public MysqlDB() {
        log.debug("初始化连接数据库...");
    }

    //对外提供一个方法来获取数据库连接
    public static Connection getConnection() {
        return conn;
    }

    /**
     * 添加数据库记录
     */
    public static void insertTrade(TradeData data)  {
        String symbol = data.getSymbol();
        String insert_sql = "insert into TRADE_"+symbol+"(`side`,`size`,`price`,`tickDirection`,`trdMatchID`,`grossValue`,`homeNotional`,`foreignNotional`,`timestamp`,`timestampl`)values(?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pstmt = conn.prepareStatement(insert_sql);

            pstmt.setString(1, data.getSide());
            pstmt.setInt(2, data.getSize());
            pstmt.setDouble(3, data.getPrice());
            pstmt.setString(4, data.getTickDirection());
            pstmt.setString(5, data.getTrdMatchID());
            pstmt.setLong(6, data.getGrossValue());
            pstmt.setDouble(7, data.getHomeNotional());
            pstmt.setInt(8, data.getForeignNotional());
            pstmt.setString(9, data.getTimestamp());
            pstmt.setLong(10, data.getTimestampl());
            pstmt.execute();
            conn.commit();
        }catch (Exception e){
            log.error(e.getMessage());
            //e.printStackTrace();
        }

    }

}
