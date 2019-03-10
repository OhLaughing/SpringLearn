package com.learn;

import org.springframework.stereotype.Component;

/**
 * Created by gustaov on 2019/3/10.
 */
@Component("beatlesSong")
public class SgtPeppers_1 implements CompactDisc {
    private String title = "Sgt. Pepper's";
    private String artist = "The Beatles";

    @Override
    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}
