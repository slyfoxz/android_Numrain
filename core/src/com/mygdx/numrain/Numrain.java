package com.mygdx.numrain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;
import java.util.Vector;

public class Numrain {
    public int x;
    public int y;
    public int length1;
    public Vector<Num> Nums;
    public Random random;
    Numrain(int length,int x1,int y1)
    {
        y=y1;
        x=x1;
        random=new Random();
        Nums=new Vector<Num>();
        for(int i=0;i<length;i++)
        {
            Nums.add(new Num((char)(random.nextInt(93)+33)));
        }
    }

    public void DownMov()
    {
        y-=26;
        if(y+(Nums.size()-1)*26<0)
        {
            y= Gdx.graphics.getHeight()+random.nextInt(48);
            length1=random.nextInt(11)+10;
            Nums.clear();
            for(int i=0;i<length1;i++)
            {
                Nums.add(new Num((char)(random.nextInt(93)+33)));
            }
        }
    }

    public void print(BitmapFont bitmapFont,SpriteBatch sb) {
        bitmapFont.setColor(1,1,1,1);
        bitmapFont.draw(sb,String.valueOf(Nums.get(0).ch),x,y);
        for(int i=1;i<Nums.size();i++)
        {
            bitmapFont.setColor(0,1.0F-(1.0F/Nums.size())*i,0,1);
            bitmapFont.draw(sb,String.valueOf(Nums.get(i).ch),x,y+26*i);
        }
    }

    public void change()
    {
        for(int i=Nums.size()-1;i>0;i--)
        {
            Nums.get(i).ch=Nums.get(i-1).ch;
        }
        Nums.get(0).ch=(char)(random.nextInt(93)+33);
    }
}
