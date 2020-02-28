package com.mygdx.numrain;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidDaydream;
import com.badlogic.gdx.backends.android.AndroidWallpaperListener;

public class LWP_Android_Daydream extends AndroidDaydream {
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        final AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useGL30 = false;
        config.useCompass = false;
        config.useWakelock = false;
        config.useAccelerometer = false;
        config.getTouchEventsForLiveWallpaper = false; // No need it in daydream

        final ApplicationListener listener = new WallpaperListener();
        initialize(listener, config);
    }

    public static class WallpaperListener extends LWP implements AndroidWallpaperListener {
        @Override
        public void offsetChange (float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset) {
        }

        @Override
        public void previewStateChange (boolean isPreview) {
        }

        @Override
        public void iconDropped(int x,int y){

        }
    }
}
