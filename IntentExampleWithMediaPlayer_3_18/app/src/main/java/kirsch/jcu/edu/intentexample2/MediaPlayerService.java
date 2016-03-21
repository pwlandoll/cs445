package kirsch.jcu.edu.intentexample2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MediaPlayerService extends Service implements MediaPlayer.OnCompletionListener{
    private MediaPlayer myMediaPlayer;

    public void onCreate()
    {
        myMediaPlayer = MediaPlayer.create(this,R.raw.awakemysoul);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!myMediaPlayer.isPlaying())
        {
            myMediaPlayer.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy()
    {
        if (myMediaPlayer.isPlaying())
        {
            myMediaPlayer.stop();
        }
        myMediaPlayer.release();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        stopSelf();
    }
}
