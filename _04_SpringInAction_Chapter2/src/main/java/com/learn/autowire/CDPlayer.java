package com.learn.autowire;

import com.learn.CompactDisc;
import com.learn.MediaPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by gustaov on 2019/3/10.
 */
@Component
public class CDPlayer implements MediaPlayer {
    @Autowired
    private CompactDisc disc;

    public CompactDisc getDisc() {
        return disc;
    }

    public void setDisc(CompactDisc disc) {
        this.disc = disc;
    }

    @Override
    public void play() {
        disc.play();
    }
}
