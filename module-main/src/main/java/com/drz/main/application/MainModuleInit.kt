package com.drz.main.application

import android.util.Log
import com.blankj.utilcode.util.Utils
import com.drz.base.base.BaseApplication
import com.drz.base.loadsir.EmptyCallback
import com.drz.base.loadsir.ErrorCallback
import com.drz.base.loadsir.LoadingCallback
import com.drz.base.loadsir.TimeoutCallback
import com.drz.common.IModuleInit
import com.drz.common.adapter.ScreenAutoAdapter
import com.kingja.loadsir.core.LoadSir
import com.zhouyou.http.EasyHttp
import com.zhouyou.http.cache.converter.GsonDiskConverter
import com.zhouyou.http.cache.model.CacheMode

/**
 * 应用模块: main
 *
 *
 * 类描述: main组件的业务初始化
 *
 *
 *
 * @author darryrzhoong
 * @since 2020-02-26
 */
class MainModuleInit : IModuleInit {
    override fun onInitAhead(application: BaseApplication): Boolean {
        ScreenAutoAdapter.setup(application)
        EasyHttp.init(application)
        if (application.issDebug()) {
            EasyHttp.getInstance().debug("easyhttp", true)
        }
        EasyHttp.getInstance()
            .setBaseUrl("http://baobab.kaiyanapp.com")
            .setReadTimeOut(15 * 1000.toLong())
            .setWriteTimeOut(15 * 1000.toLong())
            .setConnectTimeout(15 * 1000.toLong())
            .setRetryCount(3)
            .setCacheDiskConverter(GsonDiskConverter())
            .setCacheMode(CacheMode.FIRSTREMOTE)
        LoadSir.beginBuilder()
            .addCallback(ErrorCallback())
            .addCallback(LoadingCallback())
            .addCallback(EmptyCallback())
            .addCallback(TimeoutCallback())
            .setDefaultCallback(LoadingCallback::class.java)
            .commit()
        Utils.init(application)
        Log.d("MainModuleInit", "main组件初始化完成 -- onInitAhead")
        return false
    }

    override fun onInitLow(application: BaseApplication): Boolean {
        return false
    }
}