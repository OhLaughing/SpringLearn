package com.learn.javaConfig;

import com.learn.CompactDisc;
import com.learn.MediaPlayer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by gustaov on 2019/3/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JavaConfig.class)
public class ConfigTest {
    @Autowired
    CompactDisc disc;

    @Test
    public void test(){
        assertNotNull(disc);
    }
}