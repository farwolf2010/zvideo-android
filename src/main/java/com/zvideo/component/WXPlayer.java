package com.zvideo.component;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;

import com.farwolf.weex.annotation.WeexComponent;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

import tcking.github.com.giraffeplayer2.VideoView;

@WeexComponent(name="player")
public class WXPlayer extends WXVContainer<VideoView> {
    public WXPlayer(WXSDKInstance instance, WXVContainer parent, BasicComponentData basicComponentData) {
        super(instance, parent, basicComponentData);
    }

    @Override
    protected VideoView initComponentHostView(@NonNull Context context) {
        VideoView v=new VideoView(context);
        v.setBackgroundColor(Color.BLACK);
        v.getVideoInfo().setBgColor(Color.BLACK);
//         v.getVideoInfo().setBgColor(Color.GRAY).setAspectRatio(VideoInfo.AR_MATCH_PARENT);/
        return v;
    }


    @WXComponentProp(name = "src")
    public void setSrc(String src)
    {
        getHostView().setVideoPath(src);
    }

    @JSMethod
    public void seek(int sec){
        getHostView().getPlayer().seekTo(sec);
    }


    @JSMethod
    public void play(){
         getHostView().getPlayer().start();

    }

    @JSMethod
    public void pause(){
        getHostView().getPlayer().pause();
    }

    @JSMethod
    public void toggleFullScreen(){
        getHostView().getPlayer().toggleFullScreen();
    }


}
