package fm.jiecao.jcvideoplayer_lib;

import android.media.MediaPlayer;
import android.media.TimedText;

public class Manager {



   public static JCMediaManager getInstance(){
      return JCMediaManager.intance();
   }

   public static void play(){
       try {
           getInstance().mediaPlayer.prepare();
           getInstance().mediaPlayer.start();
       }
       catch (Exception e){
         e.printStackTrace();
       }

   }

    public static void seekTo(int msc){
        try {
            MediaPlayer m= getInstance().mediaPlayer;
            getInstance().mediaPlayer.seekTo(msc);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void seekTo1(int msc){
        try {
            MediaPlayer m= getInstance().mediaPlayer;
            getInstance().mediaPlayer.setOnTimedTextListener(new MediaPlayer.OnTimedTextListener() {
                @Override
                public void onTimedText(MediaPlayer mediaPlayer, TimedText timedText) {

                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void prepare(){
        try {
            MediaPlayer m= getInstance().mediaPlayer;
            getInstance().mediaPlayer.prepare();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


    public static void pause(){
        getInstance().mediaPlayer.pause();
    }
}
