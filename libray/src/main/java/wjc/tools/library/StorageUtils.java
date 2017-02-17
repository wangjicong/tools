package wjc.tools.library;

import android.content.Context;
import android.os.storage.StorageManager;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/23 0023.
 */
public class StorageUtils {
    private static final String TAG = StorageUtils.class.getName();

    public static List<StorageInfo> listAvaliableStorage(Context context) {
        List<StorageInfo> storageInfos = new ArrayList<>();
        ArrayList storagges = new ArrayList();
        StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        try {
            Class<?>[] paramClasses = {};
            Method getVolumeList = StorageManager.class.getMethod("getVolumeList", paramClasses);
            getVolumeList.setAccessible(true);
            Object[] params = {};
            Object[] invokes = (Object[]) getVolumeList.invoke(storageManager, params);
            if (invokes != null) {
                StorageInfo info = null;
                for (int i = 0; i < invokes.length; i++) {
                    StorageInfo storageInfo = new StorageInfo();
                    Object obj = invokes[i];
                    Method getPath = obj.getClass().getMethod("getPath", new Class[0]);
                    String path = (String) getPath.invoke(obj, new Object[0]);
                    storageInfo.setmPathName(path);

                    Method getPathFile = obj.getClass().getMethod("getPathFile", new Class[0]);
                    File file = (File) getPathFile.invoke(obj, new Object[0]);
                    storageInfo.setmPath(file);

                    Method getId = obj.getClass().getMethod("getId", new Class[0]);
                    String id = (String) getId.invoke(obj, new Object[0]);
                    storageInfo.setmId(id);


                    Method getMaxFileSize = obj.getClass().getMethod("getMaxFileSize", new Class[0]);
                    long maxFileSize = (long) getMaxFileSize.invoke(obj, new Object[0]);
                    storageInfo.setmMaxFileSize(maxFileSize);

                    Method getState = obj.getClass().getMethod("getState", new Class[0]);
                    String state = (String) getState.invoke(obj, new Object[0]);
                    storageInfo.setmState(state);

                    Method isRemovable = obj.getClass().getMethod("isRemovable", new Class[0]);
                    boolean removable = (boolean) isRemovable.invoke(obj, new Object[0]);
                    storageInfo.setmRemovable(removable);

                    Method isPrimary = obj.getClass().getMethod("isPrimary", new Class[0]);
                    boolean primary = (boolean) isPrimary.invoke(obj, new Object[0]);
                    storageInfo.setmPrimary(primary);

                    Method isEmulated = obj.getClass().getMethod("isEmulated", new Class[0]);
                    boolean emulated = (boolean) isEmulated.invoke(obj, new Object[0]);
                    storageInfo.setmEmulated(emulated);

                    storageInfos.add(storageInfo);
                }
            }
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return storageInfos;
    }
}
