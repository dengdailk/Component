package com.drz.main.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.drz.base.storage.MmkvHelper
import com.drz.common.adapter.ScreenAutoAdapter
import com.drz.main.R
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar

/**
 * 应用模块: 主业务模块
 *
 *
 * 类描述: 欢迎页面
 *
 *
 *
 * @author darryrzhoong
 * @since 2020-02-26
 */
class SplashActivity : AppCompatActivity() {
    private val mHandler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ScreenAutoAdapter.match(this, 375.0f)
        setContentView(R.layout.main_activity_splash)
        ImmersionBar.with(this)
            .titleBar(findViewById<View>(R.id.top_view))
            .hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
            .init()
        mHandler.postDelayed({ startToMain() }, 3000)
    }

    private fun startToMain() {
        if (MmkvHelper.getInstance().mmkv.decodeBool("first", true)) {
            startActivity(Intent(this, GuideActivity::class.java))
        } else {
            MainActivity.start(this)
        }
    }

    override fun onStop() {
        super.onStop()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        //activity销毁时移除所有消息,防止内存泄漏
        mHandler.removeCallbacksAndMessages(null)
    }
}