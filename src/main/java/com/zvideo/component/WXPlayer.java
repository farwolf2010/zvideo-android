package com.zvideo.component;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.farwolf.weex.annotation.WeexComponent;
import com.farwolf.weex.util.Weex;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import tcking.github.com.giraffeplayer2.GiraffePlayer;
import tcking.github.com.giraffeplayer2.PlayerListener;
import tcking.github.com.giraffeplayer2.VideoView;
import tv.danmaku.ijk.media.player.IjkTimedText;

@WeexComponent(name="player")
public class WXPlayer extends WXVContainer<LinearLayout> {
    VideoView player;
    Timer timer;
    boolean compelete;
    public WXPlayer(WXSDKInstance instance, WXVContainer parent, BasicComponentData basicComponentData) {
        super(instance, parent, basicComponentData);
    }

    @Override
    protected LinearLayout initComponentHostView(@NonNull Context context) {
        LinearLayout l=new LinearLayout(context);
        VideoView v=new VideoView(context);

//        player.getCoverView().setImageDrawable(getContext().getResources().getDrawable(com.farwolf.libary.R.drawable.abc_ab_share_pack_mtrl_alpha));
        v.setBackgroundColor(Color.BLACK);
        v.getVideoInfo().setBgColor(Color.BLACK);
        l.addView(v);
        this.player=v;
        this.player.setPlayerListener(new PlayerListener() {
            @Override
            public void onPrepared(GiraffePlayer giraffePlayer) {
                WXPlayer.this.fireEvent("onPrepared");
            }

            @Override
            public void onBufferingUpdate(GiraffePlayer giraffePlayer, int percent) {
                HashMap m=new HashMap();
                m.put("percent",percent);
                WXPlayer.this.fireEvent("onBufferingUpdate",m);
            }

            @Override
            public boolean onInfo(GiraffePlayer giraffePlayer, int what, int extra) {
                WXPlayer.this.fireEvent("onInfo");
                return false;
            }

            @Override
            public void onCompletion(GiraffePlayer giraffePlayer) {

                WXPlayer.this.fireEvent("onCompletion");
                cancelTimer();
                firePlaying(true);

            }

            @Override
            public void onSeekComplete(GiraffePlayer giraffePlayer) {
                WXPlayer.this.fireEvent("onSeekComplete");
            }

            @Override
            public boolean onError(GiraffePlayer giraffePlayer, int what, int extra) {
                WXPlayer.this.fireEvent("onError");
                cancelTimer();
                return false;
            }

            @Override
            public void onPause(GiraffePlayer giraffePlayer) {
                WXPlayer.this.fireEvent("onPause");
            }

            @Override
            public void onRelease(GiraffePlayer giraffePlayer) {
                WXPlayer.this.fireEvent("onRelease");
            }

            @Override
            public void onStart(GiraffePlayer giraffePlayer) {
                HashMap m=new HashMap();
                compelete=false;
                m.put("position", player.getPlayer().getCurrentPosition());
                WXPlayer.this.fireEvent("onStart",m);
                statTimer();

            }

            @Override
            public void onTargetStateChange(int oldState, int newState) {
                HashMap m=new HashMap();
                m.put("oldState",oldState);
                m.put("newState",newState);
                WXPlayer.this.fireEvent("onTargetStateChange",m);
            }

            @Override
            public void onCurrentStateChange(int oldState, int newState) {
                HashMap m=new HashMap();
                m.put("oldState",oldState);
                m.put("newState",newState);
                WXPlayer.this.fireEvent("onCurrentStateChange",m);
            }

            @Override
            public void onDisplayModelChange(int oldModel, int newModel) {
                HashMap m=new HashMap();
                m.put("oldModel",oldModel);
                m.put("newModel",newModel);
                WXPlayer.this.fireEvent("onDisplayModelChange",m);
            }

            @Override
            public void onPreparing(GiraffePlayer giraffePlayer) {
                WXPlayer.this.fireEvent("onPreparing");
            }

            @Override
            public void onTimedText(GiraffePlayer giraffePlayer, IjkTimedText text) {
                HashMap m=new HashMap();
                m.put("text",text.getText());
                WXPlayer.this.fireEvent("onTimedText",m);
            }

            @Override
            public void onLazyLoadProgress(GiraffePlayer giraffePlayer, int progress) {
                HashMap m=new HashMap();
                m.put("progress",progress);
                WXPlayer.this.fireEvent("onLazyLoadProgress",m);
            }

            @Override
            public void onLazyLoadError(GiraffePlayer giraffePlayer, String message) {
                HashMap m=new HashMap();
                m.put("message",message);
                WXPlayer.this.fireEvent("onLazyLoadError",m);
            }
        });
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        v.setLayoutParams(lp);
        l.setBackgroundColor(Color.BLACK);
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

    @WXComponentProp(name = "img")
    public void setImg(String src){
       src= Weex.getRootUrl(src,getInstance());
       if(player!=null){
           Glide
                   .with((Activity)getContext())
                   .load(src)
                   .into( player.getCoverView());
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




    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1){
                //do something
                ((Activity)getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        firePlaying(false);
                    }
                });

            }
            super.handleMessage(msg);
        }
    };

    public void firePlaying(boolean compelete){
        HashMap m=new HashMap();
        m.put("current",player.getPlayer().getCurrentPosition());
        m.put("total",player.getPlayer().getDuration());

        this.compelete=compelete;
        if(this.compelete){
            m.put("percent",1);
            fireEvent("onPlaying",m);
            cancelTimer();
        }else{
            if(player.getPlayer().getDuration()!=0)
                m.put("percent",player.getPlayer().getCurrentPosition()/(float)player.getPlayer().getDuration());
            else
                m.put("percent",0);
            fireEvent("onPlaying",m);
        }



    }

    public void cancelTimer(){
       if(timer!=null)
           timer.cancel();
    }

    public void statTimer(){
//        timerTask.scheduledExecutionTime();
         timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };
        timer.schedule(timerTask,0,500);
    }





}
