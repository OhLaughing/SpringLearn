package com.learn;

/**
 * Created by gustaov on 2019/3/10.
 */
public class BlankDisc implements CompactDisc {
    private String title;
    private String artist;

    public BlankDisc(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }


    @Override
    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}
