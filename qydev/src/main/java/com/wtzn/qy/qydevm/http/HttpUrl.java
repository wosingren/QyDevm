package com.wtzn.qy.qydevm.http;

/**
 * 服务器端接口地址
 */
public class HttpUrl {
//    public static final String SERVER_URL = "http://192.168.43.142:8080/repertory/";
public static final String SERVER_URL = "http://219.144.95.239:20001/repertory/";
//public static final String SERVER_URL = "http://192.168.1.100:8080/repertory/";
    public static final String LOGIN = SERVER_URL + "loginFromApp.do";//登录
    public static final String ImportOneDevice = SERVER_URL + "ImportOneDevice.do";//单个设备入库
    //http://127.0.0.1:8080/repertory/findImportInfoByPageFromApp.do?page=1&rows=10
//    {"content":[{"batchNo":"CG-1-05-1","wareHouseNo":"1","manager":"admin","consigno":"gg","consignee":null,"remark":"acx","createTime":"2017-08-22 05:40:02","operate":"CG","fundsStuation":null,"disManager":null},{"batchNo":"CG-1-11-1","wareHouseNo":"1","manager":"admin","consigno":"a","consignee":null,"remark":"c","createTime":"2017-08-27 10:19:55","operate":"CG","fundsStuation":null,"disManager":null},{"batchNo":"CG-1-21-1","wareHouseNo":"1","manager":"admin","consigno":"aa","consignee":null,"remark":"aa","createTime":"2017-08-22 04:53:30","operate":"CG","fundsStuation":null,"disManager":null},{"batchNo":"CG-1-81-1","wareHouseNo":"1","manager":"admin","consigno":"b1","consignee":null,"remark":"rkb01","createTime":"2017-08-24 16:58:51","operate":"CG","fundsStuation":null,"disManager":null},{"batchNo":"CG-1-82-1","wareHouseNo":"1","manager":"admin","consigno":"aa","consignee":null,"remark":"c0001","createTime":"2017-08-24 19:05:14","operate":"CG","fundsStuation":null,"disManager":null},{"batchNo":"CG-1-90-1","wareHouseNo":"1","manager":"admin","consigno":"sfsdf","consignee":null,"remark":"sdfsdf","createTime":"2017-08-17 22:22:47","operate":"CG","fundsStuation":null,"disManager":null}],"success":true}
    public static final String findImportInfoByPage = SERVER_URL + "findImportInfoByPageFromApp.do";//分页获取入库信息

    public static final String getAllDevice = SERVER_URL + "getAllDeviceInfoFromApp.do";//获取所有设备
    public static final String findDeviceByPage = SERVER_URL + "findDeviceByPageFromApp.do";//分页获取设备
    //deleteImportInfo.do?batchNo=xx
    public static final String deleteImportInfo = SERVER_URL + "deleteImportInfo.do";//删除入库信息
    public static final String addDevice = SERVER_URL + "addDevice.do";//添加设备
    public static final String updateDevice = SERVER_URL + "updateDevice.do";//修改设备
    //deleteDevice.do?devNo=xx
    public static final String deleteDevice = SERVER_URL + "deleteDevice.do";//删除设备

    public static final String outportOneDevice = SERVER_URL + "outportOneDevice.do";//单个设备出库
    public static final String findOutportInfoByPage = SERVER_URL + "findOutportInfoByPageFromApp.do";//分页获取出库信息
    //deleteOutInfo.do?batchNo=xx
    public static final String deleteOutInfo = SERVER_URL + "deleteImportInfo.do";//删除出库信息

    public static final String hkOneDevice = SERVER_URL + "hkOneDevice.do";//回库设备
    public static final String findHkInfoByPage = SERVER_URL + "findHkInfoByPageFromApp.do";//分页获取回库信息
    //deleteHkInfo.do?batchNo=xx
    public static final String deleteHkInfo = SERVER_URL + "deleteHkInfo.do";//删除回库信息

    public static final String dpOneDevice = SERVER_URL + "dpOneDevice.do";//调配设备
    public static final String findDpInfoByPage = SERVER_URL + "findDpInfoByPageFromApp.do";//分页获取调配信息
    //deleteDpInfo.do?batchNo=xx
    public static final String deleteDpInfo = SERVER_URL + "deleteDpInfo.do";//删除调配信息

    public static final String IoOneDevice = SERVER_URL + "IoOneDevice.do";// 即入即出
}
