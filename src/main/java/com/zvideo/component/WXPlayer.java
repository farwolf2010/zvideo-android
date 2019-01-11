package com.zvideo.component;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.farwolf.weex.annotation.WeexComponent;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

import tcking.github.com.giraffeplayer2.VideoView;

@WeexComponent(name="player")
public class WXPlayer extends WXVContainer<LinearLayout> {
    VideoView player;
    public WXPlayer(WXSDKInstance instance, WXVContainer parent, BasicComponentData basicComponentData) {
        super(instance, parent, basicComponentData);
    }

    @Override
    protected LinearLayout initComponentHostView(@NonNull Context context) {
        LinearLayout l=new LinearLayout(context);
        VideoView v=new VideoView(context);
        v.setBackgroundColor(Color.BLACK);
        v.getVideoInfo().setBgColor(Color.BLACK);
        l.addView(v);
        this.player=v;
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        v.setLayoutParams(lp);
        l.setBackgroundColor(Color.BLACK);

//         v.getVideoInfo().setBgColor(Color.GRAY).setAspectRatio(VideoInfo.AR_MATCH_PARENT);/
        return l;
    }


    @WXComponentProp(name = "src")
    public void setSrc(String src)
    {
        player.setVideoPath(src);
    }

    @WXComponentProp(name = "title")
    public void setTitle(String title)
    {
        player.getVideoInfo().setTitle(title);
    }

    @WXComponentProp(name = "autoPlay")
    public void setAutoPlay(boolean auto){
       if(auto){
           if(player.getVideoInfo().getUri()!=null){
               this.play();
           }
       }
    }

    @JSMethod
    public void seek(int sec){
        player.getPlayer().seekTo(sec);
    }


    @JSMethod
    public void play(){
        player.getPlayer().start();

    }

    @JSMethod
    public void pause(){
        player.getPlayer().pause();
    }

    @JSMethod
    public void toggleFullScreen(){
        player.getPlayer().toggleFullScreen();
    }





}
