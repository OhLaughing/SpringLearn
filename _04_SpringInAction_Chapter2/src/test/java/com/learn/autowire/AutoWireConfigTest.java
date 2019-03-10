package com.learn.autowire;

import com.learn.MediaPlayer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by gustaov on 2019/3/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AutoWireConfig.class)
public class AutoWireConfigTest {

    @Autowired
    MediaPlayer mediaPlayer;

    @Test
    public void test(){
        assertNotNull(mediaPlayer);
        assertTrue(mediaPlayer instanceof CDPlayer);
        CDPlayer player = (CDPlayer) mediaPlayer;
        assertNotNull(player.getDisc());
    }
}