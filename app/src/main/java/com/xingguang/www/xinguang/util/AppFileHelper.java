package com.xingguang.www.xinguang.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;

import com.xingguang.www.xinguang.activity.BaseActivity;
import com.xingguang.www.xinguang.base.OnPermissionGrantListener;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by 大灯泡 on 2017/3/23.
 * <p>
 * https://github.com/razerdp/FriendCircle
 * <p>
 * app文件helper，针对7.0，需要留意path与filepaths一致
 */

public class AppFileHelper {
    private static       boolean OVERM = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    private static final String  TAG   = "AppFileHelper";

    public static final String[] INTERNAL_STORAGE_PATHS = new String[]{"/mnt/", "/emmc/"};
    public static final String   ROOT_PATH              = "com/xinguang/";
    public static final String   DATA_PATH              = ROOT_PATH + "data/";
    public static final String   CACHE_PATH             = ROOT_PATH + "cache/";
    public static final String   PIC_PATH               = ROOT_PATH + "pic/";
    public static final String   CAMERA_PATH            = ROOT_PATH + "pic/camera/";
    public static final String   LOG_PATH               = ROOT_PATH + "log/";
    public static final String   DOWNLOAD_PATH          = ROOT_PATH + "download/";
    public static final String   TEMP_PATH              = ROOT_PATH + "temp/";

    private static String storagePath;

    public static void initStroagePath(Activity activity) {
        if (!TextUtils.isEmpty(storagePath))
            return;
        if (OVERM) {
            if (activity instanceof BaseActivity) {
                ((BaseActivity) activity).getPermissionHelper().requestPermission(new OnPermissionGrantListener() {
                    @Override
                    public void onPermissionGranted(PermissionHelper.Permission... grantedPermissions) {
                        initStroagePathInternal();
                    }

                    @Override
                    public void onPermissionsDenied(PermissionHelper.Permission... deniedPermissions) {

                    }
                }, PermissionHelper.Permission.WRITE_EXTERNAL_STORAGE, PermissionHelper.Permission
                        .READ_EXTERNAL_STORAGE);
            } else {
                int permission1 = ActivityCompat.checkSelfPermission(activity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);
                int permission2 = ActivityCompat.checkSelfPermission(activity,
                        Manifest.permission.READ_EXTERNAL_STORAGE);

                if (permission1 != PackageManager.PERMISSION_GRANTED ||
                        permission2 != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            activity,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1
                    );
                } else {
                    initStroagePathInternal();
                }
            }
        } else {
            initStroagePathInternal();
        }
    }


    private static void initStroagePathInternal() {
        if (TextUtils.isEmpty(storagePath)) {
            //M开始用的filePorider
            if (!OVERM) {
                storagePath = FileUtil.getStoragePath(AppContext.getAppContext(), false);
            }
            if (TextUtils.isEmpty(storagePath)) {
                //没有路径就使用getExternalStorageDirectory
                storagePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                if (TextUtils.isEmpty(storagePath)) {
                    //依然没法创建路径则使用私有的
                    storagePath = AppContext.getAppContext().getFilesDir().getAbsolutePath();
                }
            }
        }

        storagePath = FileUtil.checkFileSeparator(storagePath);
        Log.i(TAG, "storagepath  >>  " + storagePath);

        File rootDir = new File(storagePath.concat(ROOT_PATH));
        checkAndMakeDir(rootDir);

        File dataDir = new File(storagePath.concat(DATA_PATH));
        checkAndMakeDir(dataDir);

        File cacheDir = new File(storagePath.concat(CACHE_PATH));
        checkAndMakeDir(cacheDir);

        File picDir = new File(storagePath.concat(PIC_PATH));
        checkAndMakeDir(picDir);

        File cameraDir = new File(storagePath.concat(CAMERA_PATH));
        checkAndMakeDir(cameraDir);

        File logDir = new File(storagePath.concat(LOG_PATH));
        checkAndMakeDir(logDir);

        File downLoadDir = new File(storagePath.concat(DOWNLOAD_PATH));
        checkAndMakeDir(downLoadDir);

        File tempDir = new File(storagePath.concat(TEMP_PATH));
        checkAndMakeDir(tempDir);
    }

    private static void checkAndMakeDir(File file) {
        if (!file.exists()) {
            boolean result = file.mkdirs();
            Log.i(TAG, "mkdirs  >>>  " + file.getAbsolutePath() + "  success  >>  " + result);
        }
    }

    public static String getAppStoragePath() {
        return storagePath;
    }

    public static String getAppDataPath() {
        return storagePath.concat(DATA_PATH);
    }


    public static String getAppCachePath() {
        return storagePath.concat(CACHE_PATH);
    }

    public static String getAppPicPath() {
        return storagePath.concat(PIC_PATH);
    }

    public static String getCameraPath() {
        return storagePath.concat(CAMERA_PATH);
    }

    public static String getAppLogPath() {
        return storagePath.concat(LOG_PATH);
    }

    public static String getAppTempPath() {
        return storagePath.concat(TEMP_PATH);
    }

    public static String createShareImageName() {
        return createImageName(false);
    }

    public static String createImageName(boolean isJpg) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyyMMdd_HHmmss", Locale.US);
        return dateFormat.format(date) + (isJpg ? ".jpg" : ".png");
    }




    public static void createPlanContentFileDir(long createTime, String planId, String contentId) {
        File dataDir = new File(getPlanContentFileDir(createTime, planId, contentId));
        Log.i(TAG, "--:" + getPlanContentFileDir(createTime, planId, contentId));
        checkAndMakeDir(dataDir);
    }

    public static String getPlanContentFileDir(long createTime, String planId, String ContentId) {
        return getCameraPath().concat(getPlanContentId(createTime, planId, ContentId) + File.separator);
    }

    public static String getPlanCameraPicFileDirById(String planContentId) {
        return getCameraPath().concat(planContentId + File.separator);
    }

    public static String getPlanContentId(long createTime, String planId, String ContentId) {
        Date date = new Date(createTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyyMMdd", Locale.US);
        return dateFormat.format(date) + "_" + planId + "_" + ContentId;
    }

    public static String createCropImageName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyyMMdd_HHmmss", Locale.US);
        return dateFormat.format(date) + "_crop.png";
    }

}
