package wjc.tools.library;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.provider.Settings;

/**
 * Created by Administrator on 2017/1/23 0023.
 */
public class NetUtils {
    private static final String TAG = NetUtils.class.getName();
    public static final int NETTYPE_WIFI = 0x01;
    public static final int NETTYPE_CMWAP = 0x02;
    public static final int NETTYPE_CMNET = 0x03;
    /**
     * 当前网络是否可用
     */
    public static boolean isNetworkConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni!=null && ni.isConnectedOrConnecting();
    }

    /**
     * 当前网络类型
     * @param context
     * @return
     */
    public static int getNetworkType(Context context){
        int netType = 0xFF;
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni  = cm.getActiveNetworkInfo();
        if (null==ni){
            return netType;
        }else {
            netType = ni.getType();
        }
        return netType;
    }
    /**
     * wifi 是否链接wifi
     * @param context
     * @return
     */
    public static boolean isConnectWifi(Context context){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni  = cm.getActiveNetworkInfo();
        if (null==ni){
            return false;
        }else{
            if (ni.getType()==ConnectivityManager.TYPE_WIFI){
                return ni.isConnected();
            }
            ni.isAvailable();

        }
        return false;
    }
    /**
     * wifi是否可用
     * @param context
     * @return
     */
    public static boolean isAvilableWifi(Context context){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (null==ni){
            return false;
        }else{
            if (ni.getType()==ConnectivityManager.TYPE_WIFI){
                return ni.isAvailable();
            }
        }
        return false;
    }
    /**
     * 判断是否是飞行模式
     * @param context
     * @return
     */
    public static boolean isAirplaneMode(Context context){
        int isAirplaneMode = Settings.System.getInt(context.getContentResolver(),Settings.System.AIRPLANE_MODE_ON,0);
        return isAirplaneMode==1?false:true;
    }
    /**
     * 设置手机飞行模式开关
     * @param context
     * @param enabling
     */
    public static void setAirplaneModeOn(Context context,boolean enabling) {
        Settings.System.putInt(context.getContentResolver(),
                Settings.System.AIRPLANE_MODE_ON,enabling ? 1 : 0);
        Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        intent.putExtra("state", enabling);
        context.sendBroadcast(intent);
    }
}
