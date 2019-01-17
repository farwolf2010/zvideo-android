package com.zvideo.component;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.farwolf.weex.annotation.WeexComponent;
import com.farwolf.weex.util.Weex;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

import org.song.videoplayer.DemoQSVideoView;
import org.song.videoplayer.PlayListener;

//import chuangyuan.ycj.videolibrary.video.VideoPlayerManager;
//import chuangyuan.ycj.videolibrary.widget.VideoPlayerView;

@WeexComponent(name="pvideo")
public class WXPVideo extends  WXVContainer<DemoQSVideoView>{


    public WXPVideo(WXSDKInstance instance, WXVContainer parent, BasicComponentData basicComponentData) {
        super(instance, parent, basicComponentData);
    }

    @Override
    protected DemoQSVideoView initComponentHostView(@NonNull Context context) {
//        VideoPlayerView player=new VideoPlayerView(context);
        DemoQSVideoView qsVideoView=new DemoQSVideoView(context);
//        JCVideoPlayer player=new JCVideoPlayer(context,null);
//        VideoPlayerManager.getInstance().getVideoPlayer()
        qsVideoView.setPlayListener(new PlayListener() {
            @Override
            public void onStatus(int status) {

            }

            @Override
            public void onMode(int mode) {

            }

            @Override
            public void onEvent(int what, Integer... extra) {

            }
        });

        return qsVideoView;
    }

    @WXComponentProp(name = "img")
    public void setImg(String src){
       src= Weex.getRootUrl(src,getInstance());
       if(getHostView()!=null){
           Glide
                   .with((Activity)getContext())
                   .load(src)
                   .into( getHostView().getCoverImageView());
       }

    }
    @WXComponentProp(name = "autoPlay")
    public void setAutoPlay(boolean auto){
        if(auto){
            if(getHostView().getUrl()!=null){
                this.play();
            }
        }
    }


    @WXComponentProp(name = "src")
    public void setSrc(String src)
    {
        getHostView().setUp(src,null,"");
    }

    @JSMethod
    public void seek(int sec){
        getHostView().seekTo(sec);
    }



    @JSMethod
    public void play(){
         getHostView().play();
    }

    @JSMethod
    public void pause(){
        getHostView().pause();
    }

    @WXComponentProp(name = "title")
    public void setTitle(String title)
    {
       getHostView().setUp(getHostView().getUrl(),title);
//        ((TextView)getHostView().findViewById(R.id.title)).setText(title);
    }

    @JSMethod
    public void full(){

        getHostView().enterWindowFullscreen();

    }

    @JSMethod
    public void quit(){
//        VE_SURFACEHOLDER_FINISH_FULLSCREEN
        getHostView().quitWindowFullscreen();
    }

    @JSMethod
    public void toggleFullScreen(){

    }









}
