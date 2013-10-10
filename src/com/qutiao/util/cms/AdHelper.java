package com.qutiao.util.cms;


import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;





public class AdHelper {

    /**
     * �?��批量操作是否全部成功
     * @param statusArr
     * @return
     */
    public static boolean checkSuccess(int[] statusArr) {
        boolean result = true;
        if (statusArr == null || statusArr.length == 0) {
            return false;
        }
        for (int status : statusArr) {
            if (status == 0) {
                result = false;
                break;
            }
        }
        return result;
    }
    
    public static boolean checkSuccess(List<Long> statusArr) {
        boolean result = true;
        if (statusArr == null || statusArr.size() == 0) {
            return false;
        }
        for (long status : statusArr) {
            if (status == 0) {
                result = false;
                break;
            }
        }
        return result;
    }



    /**
     * 返回指定小时的秒数
     * @param hours
     * @return
     */
    public static long getSecondsOfPointedHours(int hours) {
        long seconds = 0L;
        if (hours <= 0) {
            seconds = 0L;
        } else {
            seconds = hours * 3600;
        }
        return seconds;
    }

    /**
     * 返回当前系统时间的小时
     * @return
     */
    public static int getHoursOfCurrentTime() {
        Calendar today = Calendar.getInstance();
        return today.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 返回当前系统时间的小时的前一个小时
     * @return
     */
    public static int getPreHoursOfCurrentTime() {
        Calendar today = Calendar.getInstance();
        int hours = today.get(Calendar.HOUR_OF_DAY);
        if (hours == 0) {
            return 23;
        } else {
            return hours - 1;
        }
    }

    public static int getPreFiveMinutesOfCurrentTime() {
        Calendar today = Calendar.getInstance();
        int minutes = today.get(Calendar.MINUTE);
        int result = -1;
        if (minutes == 0) {
            result = 55;
        } else {
            result = minutes - 5;
        }
        return result;
    }

    /**
     * 返回当前分钟数
     * @return
     */
    public static int getCurrentMinutesOfCurrentTime() {
        Calendar today = Calendar.getInstance();
        return today.get(Calendar.MINUTE);
    }

    /**
     * 返回0-23之间的数值,当输入的值小于零时，返回值为23
     * @param num
     * @return
     */
    public static int getNumIn0To23(int num) {
        if (num < 0) {
            return 23;
        } else if (num > 23) {
            return num - 24;
        }
        return num;
    }

    /**
     * 返回第二天的日期
     * @return
     */
    public static Date getTomorrowDate() {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DAY_OF_MONTH, 1);
        return today.getTime();
    }

    /**
     * 返回昨天的日期
     * @return
     */
    public static Date getYesterdayDate() {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DAY_OF_MONTH, -1);
        return today.getTime();
    }

    /**
     * 返回指定时间与当前时间的秒数为有效期
     * @param hours
     * @return
     */
    public static long getExpireTimeOfPointedTime(int hours) {
        int currHours = getHoursOfCurrentTime();
        int diffHours = Math.abs(currHours - hours) + 1;
        long seconds = getSecondsOfPointedHours(diffHours);
        return seconds;
    }



    /**
     * 判断分批的批量操作的返回结果
     * @param resultList
     * @return
     */
    public static boolean checkRetListSuccess(List<Integer> resultList) {
        boolean result = true;
        if (resultList == null || resultList.size() == 0) {
            return false;
        }
        for (int value : resultList) {
            if (value == 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * 判断当前分钟数是否小于等于30分钟
     * @return
     */
    public static boolean isLsAndEqThirtyMinutes() {
        boolean result = false;
        Calendar today = Calendar.getInstance();
        int minute = today.get(Calendar.MINUTE);
        if (minute <= 30) {
            result = true;
        }
        return result;

    }

    /**
     * 将集合转化成以逗号分隔的字符串
     * @param coll
     * @return
     */
    public static String transColltoString(Collection coll) {
        if (coll == null || coll.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Object ob : coll) {
            sb.append(ob);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }



}
