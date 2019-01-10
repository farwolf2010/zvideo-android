package com.zvideo.component;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.farwolf.weex.annotation.WeexComponent;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.Manager;

//import chuangyuan.ycj.videolibrary.video.VideoPlayerManager;
//import chuangyuan.ycj.videolibrary.widget.VideoPlayerView;

@WeexComponent(name="zvideo")
public class WXZVideo extends  WXVContainer<JCVideoPlayer>{


    public WXZVideo(WXSDKInstance instance, WXVContainer parent, BasicComponentData basicComponentData) {
        super(instance, parent, basicComponentData);
    }

    @Override
    protected JCVideoPlayer initComponentHostView(@NonNull Context context) {
//        VideoPlayerView player=new VideoPlayerView(context);
        JCVideoPlayer player=new JCVideoPlayer(context,null);
//        VideoPlayerManager.getInstance().getVideoPlayer()
        return player;
    }


    @WXComponentProp(name = "src")
    public void setSrc(String src)
    {
        getHostView().setUp(src,null,"");
    }

    @JSMethod
    public void seek(int sec){
        Manager.seekTo(sec);
    }

    @JSMethod
    public void play(){
        if(getHostView().CURRENT_STATE==JCVideoPlayer.CURRENT_STATE_NORMAL){
            View v=getHostView().findViewById(fm.jiecao.jcvideoplayer_lib.R.id.start);
            getHostView().onClick(v);
        }

    }

    @JSMethod
    public void pause(){
        if(getHostView().CURRENT_STATE==JCVideoPlayer.CURRENT_STATE_PLAYING){
            View v=getHostView().findViewById(fm.jiecao.jcvideoplayer_lib.R.id.start);
            getHostView().onClick(v);
        }
    }

    @JSMethod
    public void full(){

            View v=getHostView().findViewById(fm.jiecao.jcvideoplayer_lib.R.id.fullscreen);
            getHostView().onClick(v);

    }

    @JSMethod
    public void quit(){
//        VE_SURFACEHOLDER_FINISH_FULLSCREEN
        getHostView().quitFullScreen();
    }









}
