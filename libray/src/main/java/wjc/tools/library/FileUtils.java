package wjc.tools.library;

import android.util.Log;

import java.io.File;

/**
 * Created by Administrator on 2017/2/17 0017.
 */
public class FileUtils {
    private static final String TAG = FileUtils.class.getName();
    public static final String UNIT_B = "B";
    public static final String UNIT_KB = "KB";
    public static final String UNIT_MB = "MB";
    public static final String UNIT_GB = "GB";
    public static final String UNIT_TB = "TB";
    private static final int UNIT_INTERVAL = 1024;
    private static final double ROUNDING_OFF = 0.005;
    private static final int DECIMAL_NUMBER = 100;

    /**
     * This method converts a size to a string
     *
     * @param size the size of a file
     * @return the string represents the size
     */
    public static String sizeToString(long size) {
        String unit = UNIT_B;
        if (size < DECIMAL_NUMBER) {
            Log.d(TAG, "sizeToString(),size = " + size);
            return Long.toString(size) + " " + unit;
        }

        unit = UNIT_KB;
        double sizeDouble = (double) size / (double) UNIT_INTERVAL;
        if (sizeDouble > UNIT_INTERVAL) {
            sizeDouble = (double) sizeDouble / (double) UNIT_INTERVAL;
            unit = UNIT_MB;
        }
        if (sizeDouble > UNIT_INTERVAL) {
            sizeDouble = (double) sizeDouble / (double) UNIT_INTERVAL;
            unit = UNIT_GB;
        }
        if (sizeDouble > UNIT_INTERVAL) {
            sizeDouble = (double) sizeDouble / (double) UNIT_INTERVAL;
            unit = UNIT_TB;
        }

        // Add 0.005 for rounding-off.
        long sizeInt = (long) ((sizeDouble + ROUNDING_OFF) * DECIMAL_NUMBER); // strict to two
        // decimal places
        double formatedSize = ((double) sizeInt) / DECIMAL_NUMBER;
        Log.d(TAG, "sizeToString(): " + formatedSize + unit);

        if (formatedSize == 0) {
            return "0" + " " + unit;
        } else {
            return Double.toString(formatedSize) + " " + unit;
        }
    }
}
