//package com.zvideo.component;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//
//import com.farwolf.weex.annotation.WeexComponent;
//import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
//import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
//import com.taobao.weex.WXSDKInstance;
//import com.taobao.weex.annotation.JSMethod;
//import com.taobao.weex.ui.action.BasicComponentData;
//import com.taobao.weex.ui.component.WXComponentProp;
//import com.taobao.weex.ui.component.WXVContainer;
//
//@WeexComponent(name="dplayer")
//public class WXDPlayer extends WXVContainer<SampleVideo> {
//
//
//    public WXDPlayer(WXSDKInstance instance, WXVContainer parent, BasicComponentData basicComponentData) {
//        super(instance, parent, basicComponentData);
//    }
//
//    @Override
//    protected SampleVideo initComponentHostView(@NonNull Context context) {
//
//
//        SampleVideo p=new SampleVideo(context);
//
//        return p;
//    }
//
//
//    @WXComponentProp(name = "src")
//    public void setSrc(String src)
//    {
////        player.setPlaySource(src);
//    }
//
//
//    @JSMethod
//    public void play(){
////       this.player.startPlay();
//
//    }
//
//    @JSMethod
//    public void pause(){
////        this.player.pausePlay();
//    }
//
//    @JSMethod
//    public void toggleFullScreen(){
////        this.player.toggleFullScreen();
//    }
//
//
//
//
//}
