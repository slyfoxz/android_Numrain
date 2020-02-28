package com.mygdx.numrain;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;
import java.util.Vector;

public class Main extends Base {
    private OrthographicCamera cam;
    private SpriteBatch sb;
    BitmapFont bitmapFont;
    Vector<Numrain> numrains;
    boolean num1;
    Random random;
    private long diff, start;

    public Main(Game game, Resolver resolver){
        super(game, resolver);
    }

    @Override
    public void show(){
        Config.load();
        sb=new SpriteBatch();
        start = System.currentTimeMillis();
        resetCamera();
        num1=true;
        random=new Random();
        bitmapFont = new BitmapFont(Gdx.files.internal("Game.fnt"),Gdx.files.internal("Game.png"),false);
        numrains=new Vector<Numrain>();
        for(int i=0;i<Gdx.graphics.getWidth()/32+1;i++)//length[8,17]
        {
            numrains.add(new Numrain(random.nextInt(11)+10,i*32,Gdx.graphics.getHeight()+random.nextInt(500)));
        }
    }

    @Override
    public void dispose(){
        sb.dispose();
    }

    @Override
    public void resize(int width,int height){
        super.resize(width,height);
        resetCamera();
    }

    @Override
    public void render(float delta){
        draw(delta);
    }

    private void draw(float delta){
        super.render(delta);
        if (isAndroid && resolver != null) // In daydream resolver is null
            cam.position.x = (sW / 2) - resolver.getxPixelOffset();
        cam.update();
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        for(Numrain numrain:numrains)
        {
            numrain.print(bitmapFont,sb);
        }
        sb.end();
        update();
        sleep(50);
    }

    public void update(){
        if(num1)
        {
            for(Numrain numrain:numrains)
            {
                if(numrain.Nums.size()<15)
                {
                    numrain.DownMov();
                    numrain.change();
                }
            }
        }
        else
        {
            for(Numrain numrain:numrains)
            {
                numrain.DownMov();
                numrain.change();
            }
        }
        num1=!num1;
    }

    private void resetCamera() {
        cam = new OrthographicCamera(sW, sH);
        cam.setToOrtho(false, sW, sH);
        cam.position.set(sW /2, sH / 2, 0);
    }

    public void sleep(int fps) {
        if(fps>0){
            diff = System.currentTimeMillis() - start;
            long targetDelay = 1000/fps;
            if (diff < targetDelay) {
                try{
                    Thread.sleep(targetDelay - diff);
                } catch (InterruptedException e) {}
            }
            start = System.currentTimeMillis();
        }
    }
}
