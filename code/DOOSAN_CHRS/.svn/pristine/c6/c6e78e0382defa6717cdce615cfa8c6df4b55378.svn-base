package com.ait.ar.scheduler;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

//import com.ait.util.SqlUtil;

public class ArDataReader {

    private String propertiesFileName = null;// 配置文件名

    private String recordOfDateStr = null;// 读取特定日期的记录

    private int readMode = 0;// 读取模式，0代表读取特定日期， 1代表读取自特定日期0时0分0秒起的所有数据

    public static final int GIVEN = 0;

    public static final int APPEND = 1;

    public ArDataReader() {
        Logger.getLogger(getClass()).debug("ArDataReader Constructed");
        // default constructor
    }

    public void setPropertiesFileName(String proFileName) {
        propertiesFileName = proFileName;
    }

    public String getPropertiesFileName() {
        if (propertiesFileName == null) {
            propertiesFileName = "/db.properties";
        }
        return propertiesFileName;
    }

    public void setRecordOfDateStr(String a_recordOfDate) {
        recordOfDateStr = a_recordOfDate;
    }

    public String getRecordOfDateStr() {// 返回读取记录的日期，
        if (recordOfDateStr == null) {// 默认读取前一天的数据 返回字符串格式为"yyyy-MM-dd"
            recordOfDateStr = new SimpleDateFormat("yyyy-MM-dd")// 时间格式
                    .format(new Date(System.currentTimeMillis() - (86400000)));// 前一天的日期字符串，用于读取记录
        }
        return recordOfDateStr;
    }

    // 设置读取模式，0为特定日期的记录 1为特定日期起以后的所有记录
    public void setReadMode(int a_readMode) {
        readMode = a_readMode;
    }

    public int getReadMode() {
        return readMode;
    }

    // get db connection
    private Connection getDBConnect(String connType) {
        if (connType == null)
            connType = "";
        Properties dbPro = new Properties();
        InputStream is = getClass()
                .getResourceAsStream(getPropertiesFileName());// 连接属性文件
        try {
            Logger.getLogger(getClass()).debug("read db properties......");
            dbPro.load(is);
        } catch (IOException ioex) {
            Logger.getLogger(getClass()).error(
                    "read db properties fail! " + ioex.toString());
        }

        try {
            if (is != null) {
                Logger.getLogger(getClass()).debug("close db properties file");
                is.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(getClass()).error(
                    "close property file fail ! " + ex.toString());
        }

        try {
            Class.forName(dbPro.getProperty(connType + "Driver").trim());
        } catch (ClassNotFoundException cnfex) {
            Logger.getLogger(getClass()).error(
                    "register jdbc driver fail ! " + cnfex.toString());
        }

        Logger.getLogger(getClass()).debug(
                "DBConnect Infomation(TYPE:" + connType + "):" + "\n"
                        + "\tDriver :"
                        + dbPro.getProperty(connType + "Driver").trim() + "\n"
                        + "\tURL :"
                        + dbPro.getProperty(connType + "Url").trim() + "\n"
                        + "\tUser:"
                        + dbPro.getProperty(connType + "User").trim() + "\n"
                        + "\tPassword:"
                        + dbPro.getProperty(connType + "Password").trim()
                        + "\n");
        Connection conn = null;
        try {
            Logger.getLogger(getClass()).debug("get db Connection... ");
            conn = DriverManager.getConnection(dbPro.getProperty(
                    connType + "Url").trim(), dbPro.getProperty(
                    connType + "User").trim(), dbPro.getProperty(
                    connType + "Password").trim());
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(
                    "get DB connection fail! return null" + sqlex.toString());
            conn = null;
        }
        return conn;
    }

    /**
     * 处理方式：逐条读取按照时间由早到晚排序的考勤数据， 如果是进门卡，直接写入数据库，且出门数据为空。
     * 如果是出门卡，寻找ar_records的进门数据并与之匹配， 如果找不到，说明进门漏刷，直接写入数据库，且出门数据为空。
     */
    // 读取考勤集中的特定日期的数据写入2.0原始考勤数据表
    public void readArRecord() {
        // 记录开始执行时间
        Logger.getLogger(getClass()).debug(
                "#######################################");
        Logger.getLogger(getClass()).debug(
                "read ar record start at :"
                        + new Timestamp(System.currentTimeMillis()));
        // 从考勤机数据库读取记录
        Connection connRead = getDBConnect("read");// 获取读取用数据库连接
        Connection connWrite = null;// 获取写入用数据库连接
        Statement state = null;
        ResultSet rs = null;
        int readRowCounter = 0;// 读取计数
        int writeRowCounter = 0;// 写入计数
        String sqlString = null;

        if (connRead != null) {
            if (getReadMode() == ArDataReader.GIVEN) {
                /* 读取特定日期记录 */
                sqlString = "SELECT " + "CARDSERIAL AS CARD_NO, " + // 卡号
                        "WORKNO AS EMP_ID, " + // 工号
                        "SKTIME AS PUNCH_TIME, " + // 打卡时间
                        "READER AS STATE " + // 状态，上班或者下班
                        "FROM KQ_RECORD " + "WHERE READER IN('001-1', " + // --上班卡
                        "'002-2') " + // --下班卡" +
                        "AND CONVERT(VARCHAR(11), SKTIME, 23)='"
                        + getRecordOfDateStr() + "' " + // --指定日期
                        // "and Convert(varchar(11), skTime, 23)='2005-12-21' "
                        // + //for test
                        "GROUP BY CARDSERIAL, WORKNO, SKTIME, READER "
                        + "ORDER BY SKTIME ";
            } else if (getReadMode() == ArDataReader.APPEND) {
                sqlString = "SELECT " + "CARDSERIAL AS CARD_NO, " + // 卡号
                        "WORKNO AS EMP_ID, " + // 工号
                        "SKTIME AS PUNCH_TIME, " + // 打卡时间
                        "READER AS STATE " + // 状态，上班或者下班
                        "FROM KQ_RECORD " + "WHERE READER IN('001-1', " + // --上班卡
                        "'002-2') " + // --下班卡" +
                        "AND SKTIME >= CAST('" + getRecordOfDateStr()
                        + "' AS DATETIME) " + // --使用'年-月-日'格式
                        // "AND SKTIME >= cast('2005-12-21' AS DATETIME) " +
                        // //for test
                        "GROUP BY CARDSERIAL, WORKNO, SKTIME, READER "
                        + "ORDER BY SKTIME ";
            }
            /* 读取特定天之后的所有数据 */

            Logger.getLogger(getClass()).debug(sqlString);
            if (connRead != null) {
                try {
                    state = connRead.createStatement();
                    rs = state.executeQuery(sqlString);
                    if (rs != null) {// 存在数据
                        ArRecord row = new ArRecord();// 使用一个对象重复操作
                        connWrite = getDBConnect("write");// 获取写入用数据库连接
                        if (connWrite != null) {
                            row.setDbConnection(connWrite);
                            while (rs.next()) {
                                readRowCounter++;
                                row.setCardNo(rs.getString("CARD_NO"));
                                row.setEmpId(rs.getString("EMP_ID"));
                                row.setPunchTime(rs.getTimestamp("PUNCH_TIME"));
                                row.setState(rs.getString("STATE"));
                                if (row.getState().equalsIgnoreCase("001-1")) {
                                    row.setState("IN");
                                } else if (row.getState().equalsIgnoreCase(
                                        "002-2")) {
                                    row.setState("OUT");
                                }
                                row.writeToDb(); // 写入2.0考勤原始数据表
                            }// end while
                            writeRowCounter = row.getCountWrite();// 用于再结果报告时显示总共写入的行数
                        }// end write conn != null
                    }// end rs != null
                } catch (SQLException sqlex) {
                    Logger.getLogger(getClass()).error(
                            "read ar data found error : " + sqlex.toString());
                } finally {
                    try {
                        if (rs != null)
                            rs.close();
                        if (state != null)
                            state.close();
                        if (connRead != null)
                            connRead.close();
                        if (connWrite != null)
                            connWrite.close();
                    } catch (SQLException sqlex) {
                        Logger.getLogger(getClass()).error(
                                "close error : " + sqlex.toString());
                    } catch (Exception ex) {
                        Logger.getLogger(getClass()).error(
                                "Unknow error at close : " + ex.toString());
                    }// end close catch
                }// end read finally
            }// end conn read
        }
        Logger.getLogger(getClass())
                .debug("read row number : " + readRowCounter);
        Logger.getLogger(getClass()).debug(
                "write to db row number:" + writeRowCounter);
        Logger.getLogger(getClass()).debug(
                "read ar record start at :"
                        + new Timestamp(System.currentTimeMillis()));
        Logger.getLogger(getClass()).debug(
                "#######################################");// 输出结束时间
    }

    //
    public class ArRecord {
        private String lastEmpId = null;

        private Date lastPuchTime = null;

        private Connection conn = null;// 写入用数库连接

        private String sqlString = null;

        private String cardNo = null; // 卡号

        private String empId = null; // 工号

        private Date punchTime = null; // 打卡时间

        private String state = null; // 状态，上班或者下班

        private int countWrite = 0;// 计算总共写入的行数

        private int affRow = 0;// 记录每次写入影响的行数

        public String dateFormat = "yyyy-MM-dd";

        public String timeFormat = "yyyy-MM-dd HH:mm:ss";

        public ArRecord() {
            // default constructor
        }

        // 直接通过参数构造
        public ArRecord(String a_cardNo, String a_empId, Date a_punchTime,
                String a_state) {
            cardNo = a_cardNo;
            empId = a_empId;
            punchTime = a_punchTime;
            state = a_state;
        }

        // 获取上一个打卡人员ID
        public String getLastEmpId() {
            return lastEmpId;
        }

        // 获取上一个打卡时间{
        public Date getLastPuchTime() {
            return lastPuchTime;
        }

        public String getLastPuchTimeStr() {
            return new SimpleDateFormat(timeFormat).format(lastPuchTime);
        }

        // 卡号
        public void setCardNo(String a_cardNo) {
            cardNo = a_cardNo;
        }

        public String getCardNo() {
            return cardNo;
        }

        // 工号
        public void setEmpId(String a_empId) {
            try {
                int empIdInt = Integer.parseInt(a_empId);
                empId = String.valueOf(empIdInt);
            } catch (Exception ex) {// number format exception
                Logger.getLogger(getClass()).error(ex.toString());
                empId = a_empId;
            }
        }

        public String getEmpId() {
            return empId;
        }

        // 打卡时间
        public void setPunchTime(Date a_punchTime) {
            punchTime = a_punchTime;
        }

        public Date getPunchTime() {
            return punchTime;
        }

        public String getPuchTimeStr(String a_dateFormat) {
            return new SimpleDateFormat(a_dateFormat).format(punchTime);
        }

        public String getPuchTimeStr() {
            return getPuchTimeStr(timeFormat);
        }

        // 打卡状态，上班或者下班
        public void setState(String a_state) {
            state = a_state;
        }

        public String getState() {
            return state;
        }

        // 获取数据库连接
        public void setDbConnection(Connection a_conn) {
            conn = a_conn;
        }

        // 执行sql返回影响行，使用传入连接，不做关闭
        private int executeUpdate(String sql) {
            Statement state = null;
            int executeResult = 0;
            try {
                state = conn.createStatement();
                executeResult = state.executeUpdate(sql);
                try {
                    state.close();
                } catch (Exception ex) {
                    Logger.getLogger(getClass()).error(ex.toString());
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass()).error(ex.toString());
                executeResult = 0;
            }
            return executeResult;
        }

        // 返回此对象当前总共写入了多少条数据
        public int getCountWrite() {
            return countWrite;
        }

        // 写入数据库
        public int writeToDb() {
            // ################################写入原始记录表######################
            sqlString = "INSERT INTO AR_MAC_RECORDS(" + "Record_No, " + // --记录号
                    "Card_No, " + // --卡号
                    "R_Time, " + // --记录时间
                    "Door_Type, " + // --打卡(门)的类型，IN 是进门卡，OUT 是出门卡
                    "Active, " + // --活动状态 Y 表示活动
                    "Insert_By, " + // --记录的插入类型 H 代表人工录入 ， M 代表打卡机录入
                    "Empid" + // --工号"
                    ")VALUES(" + "AR_MAC_RECORD_SEQ.NEXTVAL, " + "'"
                    + getCardNo() + "', " + "TO_DATE('" + getPuchTimeStr()
                    + "','YYYY-MM-DD HH24:MI:SS'), " + "'" + getState() + "', "
                    + "'Y', " + "'M', " + "'" + getEmpId() + "'" + ") ";// 直接写入原始数据表
            Logger.getLogger(getClass()).debug(sqlString);
            affRow = executeUpdate(sqlString);// 写入考勤表原始数据表(直接读自考勤机并经过简单过滤的数据)
            if (affRow == 1) {// 正常写入一条数据
                countWrite++;// 记录写入总数
                lastEmpId = empId;// 记录最后一个写入的员工
                lastPuchTime = punchTime;// 记录最后一次写入的打卡时间
            } else {// 没有正常写入原始考勤记录
                Logger.getLogger(getClass())
                        .warn(
                                "Write to AR_MAC_RECORD FAIL! insert sql :"
                                        + sqlString);// 打印警告信息并记录写入的sql语句；
            }

            // ################################写入日考勤记录表######################
            /*
             * 以下包含了在写入AR_RECORDS时以及在写入过程中针对不同情况的的错误处理操作
             * //写入原始数据表后根据进出门类型处理写入考勤记录表的数据
             * if(getState().equalsIgnoreCase("IN")){//此条数据为进门卡
             * //判断记录时间是否符合排班时间，如不符合进门时间，则判断是否出进门记录，符合条件出门则作为出门记录处理
             * //setState("OUT"); //... sqlString = "INSERT INTO AR_RECORDS (" +
             * "RECORD_NO, EMPID, " + "ENTER_TIME, AR_DATE_STR, " + "INSERT_BY,
             * STATE" + ")VALUES(" + "AR_RECORDS_SEQ.NEXTVAL, " + "'" +
             * getEmpId() + "', " + "'" + getPuchTimeStr() + "', " + "'" +
             * getPuchTimeStr().split(" ")[0] + "', " + //截取日期部门，以进门的日期作为考勤日期
             * "'M', " + //M代表机器写入 "'s') ";//大写N代表此条完整记录正常，小写s代表记录还不完整或者有异常信息
             * Logger.getLogger(getClass()).debug(sqlString); countWrite +=
             * executeUpdate(sqlString);//写入考勤表 }else
             * if(getState().equalsIgnoreCase("OUT")){//此条数据为出门卡 //寻找进门记录并与之匹配
             * sqlString = "SELECT R.RECORD_NO " + "FROM AR_RECORDS R " +
             * //检查考勤记录表 "WHERE EMPID = '" + getEmpId() + "' " + //特定人员 "AND
             * ENTER_TIME IS NOT NULL " + //有进门时间 "AND OUT_TIME IS NULL " +
             * //出门时间还没有写入 "AND OUT_TIME = (" + "SELECT MAX(OUT_TIME) " + "FROM
             * AR_RECORDS " + "WHERE EMPID='" + getEmpId() + "')" +
             * //是这个人的最后一条进门记录 "AND ENTER_TIME < TO_DATE('" + "进门时间在考勤进门时间范围内" +
             * "', 'YYYY-MM-DD HH24:MI:SS')" + //进门时间在考勤进门时间范围内 "AND ENTER_TIME >
             * TO_DATE('" + "进门时间在考勤进门时间范围内" + "', 'YYYY-MM-DD HH24:MI:SS') ";
             * Logger.getLogger(getClass()).debug(sqlString); Statement state =
             * null; ResultSet rs = null; String recordNo = null; try{ state =
             * conn.createStatement(); rs = state.executeQuery(sqlString); if(rs !=
             * null && rs.next()){//存在相应的进门记录 recordNo =
             * rs.getString("RECORD_NO"); } }catch(SQLException sqlex){
             * Logger.getLogger(getClass()).error(sqlex.toString()); }finally{
             * SqlUtil.close(rs, state); } if(recordNo != null &&
             * !recordNo.equalsIgnoreCase("")){ sqlString = //找到相应的进门记录 "UPDATE
             * AR_RECORDS " + "SET OUT_TIME = '" + getPuchTimeStr() + "' " +
             * "WHERE RECORD_NO = '" + recordNo + "' "; recordNo = null;//
             * }else{ sqlString = //没有找到相应进门记录,写入考勤记录表，进门时间为空 "INSERT INTO
             * AR_RECORDS (" + "RECORD_NO, EMPID, " + "OUT_TIME, " + //
             * "AR_DATE_STR, " + "INSERT_BY, STATE" + ")VALUES(" +
             * "AR_RECORDS_SEQ.NEXTVAL, " + "'" + getEmpId() + "', " + "'" +
             * getPuchTimeStr() + "', " + // "'" + getPuchTimeStr().split("
             * ")[0] + "', " + //出门记录不作为考勤日期，保留空状态 "'M', " + //M代表机器写入 "'s')
             * ";//小写s，不知道干什么用的 } Logger.getLogger(getClass()).debug(sqlString);
             * countWrite += executeUpdate(sqlString); }
             */

            return countWrite;// 返回当前总共写入了多少行
        }
    }

    // 测试入口，参数为读取记录的日期，数据库连接配置文件的位置
    public static void main(String[] arguments) {

        Logger.getLogger(ArDataReader.class).debug("run test command line : ");
        Logger.getLogger(ArDataReader.class).debug("java [package].ArDateReader [record date] [proFile name]");
        String dateStr = null;
        String fileName = null;
        try {
            dateStr = arguments[0];
            fileName = arguments[1];
        } catch (Exception ex) {
            Logger.getLogger(ArDataReader.class).error(ex.toString());
        }
        ArDataReader arDataReader = new ArDataReader();
        arDataReader.setPropertiesFileName(fileName);// 制定属性文件名
        arDataReader.setRecordOfDateStr(dateStr);// 设置读取文件的日期
        arDataReader.setReadMode(ArDataReader.APPEND);// 设置读取方式为读取特定天
        arDataReader.readArRecord();// 开始读取
    }
}