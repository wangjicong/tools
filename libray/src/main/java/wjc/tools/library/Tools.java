package wjc.tools.library;

import java.io.File;

/**
 * Created by Administrator on 2017/1/22 0022.
 */
public class Tools {
    private static final String TAG = Tools.class.getName();
    /**
     * get file suffix
     */
    public static String getFileSuffix(String file_path){
        return file_path.substring(file_path.lastIndexOf("."));
    }
    /**
     * get file suffix
     * @param file
     * @return
     */
    public static String getFileSuffix(File file){
        if (file.exists()){
            return getFileSuffix(file.getName());
        }
        return null;
    }
    /**
     * have sub string
     */
    public static boolean hasSubString(String str,String sub){
        if (str.lastIndexOf(sub)!=-1){
            return true;
        }
        return false;
    }
}
