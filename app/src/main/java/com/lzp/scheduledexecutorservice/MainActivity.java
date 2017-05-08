package com.lzp.scheduledexecutorservice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private ScheduledExecutorService mScheduledExecutorService = Executors.newScheduledThreadPool(4, new ThreadFactory() {
        @Override
        public Thread newThread(@NonNull Runnable r) {

            return new Thread(r){
                @Override
                public void run() {
                    Log.e("lzp", "newThread");
                    super.run();
                    Log.e("lzp", "newThread over");
                }
            };
        }
    });

    private ThreadFactory threadFactory = new ThreadFactory() {
        @Override
        public Thread newThread(@NonNull final Runnable r) {
            return new Thread() {
                @Override
                public void run() {
                    r.run();
                    Log.e("lzp", "嘿嘿嘿");
                }
            };
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 延时任务
//        mScheduledExecutorService.schedule(threadFactory.newThread(new Runnable() {
//            @Override
//            public void run() {
//                Log.e("lzp", "first task");
//            }
//        }), 1, TimeUnit.SECONDS);

        // 循环任务，按照上一次任务的发起时间计算下一次任务的开始时间
//        mScheduledExecutorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                Log.e("lzp", "first:" + System.currentTimeMillis() / 1000);
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, 1, 1, TimeUnit.SECONDS);

        // 循环任务，以上一次任务的结束时间计算下一次任务的开始时间
        mScheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                Log.e("lzp", "scheduleWithFixedDelay:" + System.currentTimeMillis() / 1000);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1, 1, TimeUnit.SECONDS);


    }
}
