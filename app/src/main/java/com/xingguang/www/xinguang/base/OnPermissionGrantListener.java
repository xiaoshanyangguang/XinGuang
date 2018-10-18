package com.xingguang.www.xinguang.base;



import com.xingguang.www.xinguang.util.PermissionHelper;

/**
 * Created by 大灯泡 on 2018/5/7.
 */
public interface OnPermissionGrantListener {
    void onPermissionGranted(PermissionHelper.Permission... grantedPermissions);

    void onPermissionsDenied(PermissionHelper.Permission... deniedPermissions);

    abstract class OnPermissionGrantListenerAdapter implements OnPermissionGrantListener {
        @Override
        public void onPermissionGranted(PermissionHelper.Permission... grantedPermissions) {

        }

        @Override
        public void onPermissionsDenied(PermissionHelper.Permission... deniedPermissions) {

        }
    }
}
