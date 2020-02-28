package com.mygdx.numrain;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidLiveWallpaperService;
import com.badlogic.gdx.backends.android.AndroidWallpaperListener;

public class LWP_Android extends AndroidLiveWallpaperService {
    public static float pixelOffset=0;
    @Override
    public void onCreateApplication(){
        super.onCreateApplication();
        final AndroidApplicationConfiguration config=new AndroidApplicationConfiguration();
        config.useGL30 = false;
        config.useCompass = false;
        config.useWakelock = false;
        config.useAccelerometer = false;
        config.getTouchEventsForLiveWallpaper = true;

        final ApplicationListener listener = new WallpaperListener();
        initialize(listener, config);
    }

    public static class WallpaperListener extends LWP implements AndroidWallpaperListener{
        @Override
        public void create(){
            super.resolver = new Resolver() {
                @Override
                public float getxPixelOffset() {
                    return pixelOffset;
                }
            };

            super.create();
        }

        @Override
        public void offsetChange (float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset) {
            pixelOffset = xPixelOffset;
        }

        @Override
        public void previewStateChange (boolean isPreview) {
        }

        @Override
        public void iconDropped(int x, int y) {

        }
    }
}
