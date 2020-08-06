package factory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


public class LogFactory {

  static ConsoleHandler consoleHandler = null;

  static FileHandler fileHandler = null;
  // 正常的日期格式
  public static final String DATE_PATTERN_FULL = "yyyy-MM-dd HH:mm:ss";
  // 不带符号的日期格式，用来记录时间戳
  public static final String DATE_PATTERN_NOMARK = "yyyyMMddHHmmss";

  /**
   * 初始化全局Logger
   * 
   * @return
   */
  public static Logger initLog(int type1Write, int type2File, String name) {


    Logger log = Logger.getLogger(name);


    log.setLevel(Level.INFO);

//     addConsoleHandler(log, Level.INFO); //不注释这句，还是在控制台输出两次。。。
    if (type1Write == 1) {
      addFileHandle(type2File, log, Level.INFO, "src/graph/" + name + "_e.txt");
    } else if (type1Write == 2) {
      addFileHandle(type2File, log, Level.INFO, "src/graph/" + name + "_o.txt");
    }

//     log.setUseParentHandlers(false); //设置false，防止控制台输出两次信息
    // 加了addConsoleHandler输出一次
    // 本身Log输出一次
    return log;

  }

  /**
   * 初始化全局Logger
   * 
   * @return
   */
  public static Logger getLogger(int type1Write, int type2File, String name) {
    return initLog(type1Write, type2File, name);
  }

  /**
   * 为log设置等级
   * 
   * @param log
   * @param level
   */
  public static void setLogLevel(Logger log, Level level) {
    log.setLevel(level);
  }

  /**
   * 为log添加控制台handler
   * 
   * @param log 要添加handler的log
   * @param level 控制台的输出等级
   */
  public static void addConsoleHandler(Logger log, Level level) {
    // 控制台输出的handler
    consoleHandler = new ConsoleHandler();
    // 设置控制台输出的等级（如果ConsoleHandler的等级高于或者等于log的level，则按照ConsoleHandler的level输出到控制台，如果低于，则按照Log等级输出）
    consoleHandler.setLevel(level);
    // 添加控制台的handler
    log.addHandler(consoleHandler);

  }

  public static void addFileHandle(int type, Logger log, Level level, String filepath) {

    try {
      fileHandler = new FileHandler(filepath, true);
      fileHandler.setLevel(level);
      if (type == 1) { // 图的文件异常日志格式
        fileHandler.setFormatter(new Formatter() {
          @Override
          public String format(LogRecord record) {
            String[] information = record.getMessage().split("\t");
            return "发生文件错误的时间： " + getCurrentDateStr(DATE_PATTERN_FULL) + "\r\n" + "发生错误的文件名： "
                + information[0] + "\r\n" + "发生错误的文件内容： " + information[1] + "\r\n" + "发生错误的类名： "
                + record.getSourceClassName() + "\r\n" + "发生错误的方法名： " + record.getSourceMethodName()
                + "\r\n" + "发生错误的类型： " + information[2] + "\r\n" + "发生错误的具体信息： " + information[3]
                + "\r\n" + "发生错误的处理结果： " + information[4] + "\r\n"
                + "--------------------------------" + "\r\n";
          }

        });
      } else if (type == 2) { // 图的操作日志格式
        fileHandler.setFormatter(new Formatter() {

          @Override
          public String format(LogRecord record) {
            String[] information = record.getMessage().split("\t");
            return "操作的时间： " + getCurrentDateStr(DATE_PATTERN_FULL) + "\r\n" + "操作的内容： "
                + information[0] + "\r\n" + "--------------------------------" + "\r\n";
          }

        });
      } else if (type == 3) { // 图的语法解析异常日志格式
        fileHandler.setFormatter(new Formatter() {

          @Override
          public String format(LogRecord record) {
            String[] information = record.getMessage().split("\t");
            return "发生图操作指令错误的的时间： " + getCurrentDateStr(DATE_PATTERN_FULL) + "\r\n"
                + "发生错误的图操作指令： " + information[0] + "\r\n" + "发生错误的类名： "
                + record.getSourceClassName() + "\r\n" + "发生错误的方法名： " + record.getSourceMethodName()
                + "\r\n" + "发生错误的类型： " + information[1] + "\r\n" + "发生错误的具体信息： " + information[2]
                + "\r\n" + "发生错误的处理结果： " + information[3] + "\r\n"
                + "--------------------------------" + "\r\n";
          }

        });
      } else if (type == 4) { // 图的语法解析操作日志格式
        fileHandler.setFormatter(new Formatter() {

          @Override
          public String format(LogRecord record) {
            String[] information = record.getMessage().split("\t");
            return "操作的时间： " + getCurrentDateStr(DATE_PATTERN_FULL) + "\r\n" + "操作的内容： "
                + information[0] + "\r\n" + "--------------------------------" + "\r\n";
          }

        });
      }

    } catch (SecurityException e) {
      // e.printStackTrace();
    } catch (IOException e) {
      // e.printStackTrace();
    }
    log.addHandler(fileHandler);
  }

  /**
   * 获取当前时间
   * 
   * @return
   */
  public static String getCurrentDateStr(String pattern) {
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    return sdf.format(date);
  }

  public static void close() {
    fileHandler.close();
    // consoleHandler.close();
  }
}
