package com.zvideo.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ViewGroup;

import com.taobao.weex.WXSDKInstance;

import org.song.videoplayer.DemoQSVideoView;
import org.song.videoplayer.Util;

import wbs.hundsun.com.zvideo.R;

public class VideoView extends DemoQSVideoView {

    public WXSDKInstance instace;

    public VideoView(Context context) {
        super(context);
    }


    //移动网络提示框
    @Override
    protected boolean showWifiDialog() {
        if (!isShowWifiDialog)
            return false;
        AlertDialog.Builder builder = new AlertDialog.Builder(instace.getContext());
        builder.setMessage(getResources().getString(R.string.tips_not_wifi));
        builder.setPositiveButton(getResources().getString(R.string.tips_not_wifi_confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                prepareMediaPlayer();
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.tips_not_wifi_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
        return true;
    }



    //全屏
    @Override
    public void enterWindowFullscreen() {
        if (currentMode == MODE_WINDOW_NORMAL & checkSpaceOK()) {
            boolean flag = false;
            if (enterFullMode == 3) {
                flag = true;
                enterFullMode = height > width ? 1 : 0;
            }

            full_flag = Util.SET_FULL(getContext());
            orientation_flag = Util.isScreenOriatationPortrait(getContext());

            if (enterFullMode == 0)
                Util.SET_LANDSCAPE(getContext());
            else if (enterFullMode == 1)
                Util.SET_PORTRAIT(getContext());
            else if (enterFullMode == 2)
                Util.SET_SENSOR(getContext());
            if (flag)
                enterFullMode = 3;

            Util.showNavigationBar(getContext(), false);

            ViewGroup vp = (ViewGroup) videoView.getParent();
            if (vp != null)
                vp.removeView(videoView);
            ViewGroup decorView = (ViewGroup) (Util.scanForActivity(instace.getContext())).getWindow().getDecorView();
            //.findViewById(Window.ID_ANDROID_CONTENT);
            decorView.addView(videoView, new LayoutParams(-1, -1));
            setStateAndMode(currentState, MODE_WINDOW_FULLSCREEN);
        }
    }



}
