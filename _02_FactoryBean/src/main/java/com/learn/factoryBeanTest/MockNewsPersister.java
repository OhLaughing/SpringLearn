package com.learn.factoryBeanTest;

/**
 * Created by gustaov on 2019/3/9.
 */
public class MockNewsPersister implements IFXNewsPersister {
    private FXNewsBean newsBean;

    @Override
    public void persistNews() {
        System.out.println("persist bean:" + getNewsBean());
    }

    public FXNewsBean getNewsBean() {
        return newsBean;

    }

    public void setNewsBean(FXNewsBean newsBean) {
        this.newsBean = newsBean;
    }
}