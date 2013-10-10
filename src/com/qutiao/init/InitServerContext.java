package com.qutiao.init;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.qutiao.crawler.thread.CrawlerTimertask;

public class InitServerContext implements  ServletContextListener{

    private Timer timer = null;
    
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        Timer timer = new Timer();
        System.out.println("CrawlerTimertask start...");
        timer.schedule(new CrawlerTimertask(), javax.management.timer.Timer.ONE_SECOND*10,javax.management.timer.Timer.ONE_HOUR*6);
        System.out.println("CrawlerTimertask over...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        timer.cancel();
    }

}
