package org.superl.exchange.CBitmexTrade.beans;import lombok.Data;/*    CBitmexKline    Created by superl on 2018/5/23.                                                                      00000                                                                           00000                                                                           00000           00000000    00000  00000  00000000000     00000000    000000000 00000          00000000000  00000  00000  000000000000   00000000000  000000000 00000          00000  000   00000  00000  000000 00000  000000 00000  00000000  00000          000000000    00000  00000  00000   0000  0000000000000 000000    00000           0000000000  00000  00000  00000   00000 0000000000000 00000     00000              0000000  00000  00000  00000   00000 00000         00000     00000          00000  0000  000000000000  000000000000  000000000000  00000     00000          00000000000  000000000000  000000000000   00000000000  00000     00000           000000000   0000000000    00000000000     00000000    00000     00000                                     00000                                                                           00000                 Team:bbs.nepenthes.cn                                       00000                                           */@Datapublic class TradeData {    private String symbol;    private String side;    private int size;    private double price;    private String tickDirection;    private String trdMatchID;    private long grossValue;    private double homeNotional;    private int foreignNotional;    //时间字符    private String  timestamp;    //时间戳    private long timestampl;}