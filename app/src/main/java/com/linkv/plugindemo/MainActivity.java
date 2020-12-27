package com.linkv.plugindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.linkv.commonlibrary.ICommonDeveloper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MainActivity extends AppCompatActivity {

    private TextView mTvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvInfo = findViewById(R.id.tv_info);
    }

    ICommonDeveloper  commonDeveloper;

    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_android:
                commonDeveloper = null;
                try {
                    Class clazz = Class.forName("com.linkv.androiddevelop.AndroidDeveloper");
                    Constructor<ICommonDeveloper> constructor = clazz.getConstructor();
                    commonDeveloper = constructor.newInstance();
                } catch (NoClassDefFoundError | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    e.printStackTrace();
                }

                if (commonDeveloper ==null){
                    // 反射获取实例对象失败
                    mTvInfo.setText("获取安卓程序代码失败");
                }else {
                    mTvInfo.setText(String.format("获取安卓代码成功 ： %s", commonDeveloper.writeCode()));
                }

                break;
            case R.id.btn_ios:
                commonDeveloper = null;
                try {
                    Class clazz = Class.forName("com.linkv.iosdevelop.IOSDeveloper");
                    Constructor<ICommonDeveloper> constructor = clazz.getConstructor();
                    commonDeveloper = constructor.newInstance();
                } catch (NoClassDefFoundError | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    e.printStackTrace();
                }

                if (commonDeveloper ==null){
                    // 反射获取实例对象失败
                    mTvInfo.setText("获取iOS程序代码失败");
                }else {
                    mTvInfo.setText(String.format("获取iOS代码成功 ： %s", commonDeveloper.writeCode()));
                }

                break;
        }
    }
}
