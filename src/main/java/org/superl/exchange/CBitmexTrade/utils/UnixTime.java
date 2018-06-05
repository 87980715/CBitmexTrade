package org.superl.exchange.CBitmexTrade.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
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
public class UnixTime {

    /**
     * 时间转时间戳 ok、huobi不用做处理
     *
     * @param orderTime         订单发起时间
     * @return                  时间戳
     */
    public static String getUnixTime(String orderTime,String type) throws ParseException {
        SimpleDateFormat sdf;
        Date date;
        String mydata;

        switch (type){
            case "bitmex":
                sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                sdf.setTimeZone(TimeZone.getTimeZone("GTC"));
                date = sdf.parse(orderTime);
                mydata = String.valueOf(date.getTime());
                break;
            case "bittrex":
                sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                date = sdf.parse(orderTime);
                mydata = String.valueOf(date.getTime());
                break;

            case "coinegg":
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = sdf.parse(orderTime);
                mydata = String.valueOf(date.getTime());
                break;

            case "hitbtc":
                sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                sdf.setTimeZone(TimeZone.getTimeZone("GTC"));
                date = sdf.parse(orderTime);
                mydata = String.valueOf(date.getTime());
                break;

            case "gateio":
                long theOrderTime = Long.parseLong(orderTime)*1000;
                mydata = String.valueOf(theOrderTime);
                break;

            default:
                mydata = orderTime;
                break;
        }
        return mydata;
    }

    public static void main(String[] args) throws ParseException {

//        System.out.print(getUnixTime("2018-04-20T14:47:54.77","bittrex")+"\n");
//
//        System.out.print(getUnixTime("2018-04-20 14:47:54","coinegg")+"\n");
//
//        System.out.print(getUnixTime("2018-04-20T14:47:54.437Z","hitbtc")+"\n");

//        System.out.print(getUnixTime("1407828913","gateio")+"\n");


        System.out.print(getUnixTime("2018-05-08T12:08:04.185Z","bitmex")+"\n");
        long currentTime = System.currentTimeMillis();
        System.out.print(currentTime+"\n");

    }
}
