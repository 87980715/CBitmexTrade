package org.superl.exchange.CBitmexTrade;/*    CBitmexTrade    Created by superl on 2018/6/5.                                                                      00000                                                                      00000                                                                      00000      00000000    00000  00000  00000000000     00000000    000000000 00000     00000000000  00000  00000  000000000000   00000000000  000000000 00000     00000  000   00000  00000  000000 00000  000000 00000  00000000  00000     000000000    00000  00000  00000   0000  0000000000000 000000    00000      0000000000  00000  00000  00000   00000 0000000000000 00000     00000         0000000  00000  00000  00000   00000 00000         00000     00000     00000  0000  000000000000  000000000000  000000000000  00000     00000     00000000000  000000000000  000000000000   00000000000  00000     00000      000000000   0000000000    00000000000     00000000    00000     00000                                00000                                00000                 Team:bbs.nepenthes.cn                                00000*/import lombok.extern.log4j.Log4j;import org.apache.commons.cli.*;import org.superl.exchange.CBitmexTrade.core.CollectService;import java.util.HashMap;import java.util.Map;@Log4jpublic class Client {    private Options     options = new Options();    private CommandLine cmd = null;    private Map<String,String> properties = new HashMap<>();    public static void main(String[] args) {        new Client().run(args);    }    public void run(String[] args){        options.addOption("symbol",  true, "合约代码,如:XBTUSD");        options.addOption("start",   true, "开始时间,如:2018-05-21T00:00:00");        options.addOption("end",     true, "结束时间,如:2018-05-21T01:00:00");        options.addOption("count",   true, "每页采集条数(默认500)");        options.addOption("sleep",   true,"每次采集延迟时间(默认1秒)");        options.addOption("save",    true, "保存数据方式(默认mysql)：支持mysql,oracle,csv");        options.addOption("thread",  true, "自定义入库线程数，默认50");        options.addOption("help",    false, "使用说明");        if(args == null || args.length == 0) {            printHelp(options);            System.exit(0);            return;        }        // 解析参数，如果发生异常，则打印出帮助信息        CommandLineParser parser = new DefaultParser();        try {            cmd = parser.parse(options, args);        } catch (ParseException e) {            printHelp(options);        }        printHelp(options);        if(cmd.hasOption("count")) {            properties.put("count",cmd.getOptionValue("count"));        }else{            properties.put("count","500");            log.warn("您没有设置每页显示条数，则采用默认500条！");        }        if(cmd.hasOption("sleep")) {            properties.put("sleep",cmd.getOptionValue("sleep"));        }else{            properties.put("sleep","2000");            log.warn("您没有设置采集间隔时间，则采用默认2秒！");        }        if(cmd.hasOption("save")) {            properties.put("save",cmd.getOptionValue("save"));        }else{            properties.put("save","mysql");            log.warn("您没有设置数据保存类型，则采用默认mysql");        }        if(cmd.hasOption("help")) {            log.info("has --help arg, print Help");            printHelp(options);            return;        }        if(cmd.hasOption("symbol")) {            properties.put("symbol",cmd.getOptionValue("symbol"));        }else{            log.error("必须设置合约名称！");            return;        }        if(cmd.hasOption("start")) {            properties.put("startTime",cmd.getOptionValue("start"));        }else{            log.error("必须设置开始时间！");            return;        }        if(cmd.hasOption("thread")) {            properties.put("thread",cmd.getOptionValue("thread"));        }else{            properties.put("thread","50");            log.warn("您没有设置入库线程数，则采用默认50");        }        if(cmd.hasOption("end")) {            properties.put("endTime",cmd.getOptionValue("end"));        }else{            properties.put("endTime","");            log.info("您没有设置结束时间，则一直采集到当前时间！");        }        new CollectService(properties).start();    }    /**     * 提供程序的帮助文档     */    private void printHelp(Options opts) {        HelpFormatter hf = new HelpFormatter();        String message = "The Bitmex Trade Data Client. Show how to use common.\n\n";        hf.printHelp(message, opts);    }    private String getOptValue(String opt){        return (cmd!=null) ? cmd.getOptionValue(opt):"";    }}