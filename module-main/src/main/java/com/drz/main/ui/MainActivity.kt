package com.drz.main.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.drz.base.activity.MvvmBaseActivity
import com.drz.base.storage.MmkvHelper
import com.drz.base.viewmodel.IMvvmBaseViewModel
import com.drz.common.router.RouterActivityPath
import com.drz.common.router.RouterFragmentPath
import com.drz.main.R
import com.drz.main.databinding.ActivityMainBinding
import com.gyf.immersionbar.ImmersionBar
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = RouterActivityPath.Main.PAGER_MAIN)
class MainActivity : MvvmBaseActivity<ActivityMainBinding?, IMvvmBaseViewModel<*>?>(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        ImmersionBar.with(this)
            .statusBarColor(R.color.main_color_bar)
            .navigationBarColor(R.color.main_color_bar)
            .fitsSystemWindows(true)
            .autoDarkModeEnable(true)
            .init()
        initView()
    }

    private fun initView(){
       vp.adapter = object:FragmentStateAdapter(this){
           override fun getItemCount(): Int = 4
           override fun createFragment(position: Int): Fragment {
               return when(position){
                   0-> ARouter.getInstance().build(RouterFragmentPath.Home.PAGER_HOME)
                       .navigation() as Fragment
                   1->ARouter.getInstance().build(RouterFragmentPath.Community.PAGER_COMMUNITY)
                       .navigation() as Fragment
                   2->ARouter.getInstance().build(RouterFragmentPath.More.PAGER_MORE)
                       .navigation() as Fragment
                   3->ARouter.getInstance().build(RouterFragmentPath.User.PAGER_USER)
                       .navigation() as Fragment
                   else -> ARouter.getInstance().build(RouterFragmentPath.Home.PAGER_HOME)
                       .navigation() as Fragment
               }
           }
       }
// 当ViewPager切换页面时，改变底部导航栏的状态
        vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                navigation_bottom.menu.getItem(position).isChecked = true
            }
        })
        // 当ViewPager切换页面时，改变ViewPager的显示
        navigation_bottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> vp.currentItem = 0
                R.id.community -> vp.currentItem = 1
                R.id.notify -> vp.currentItem = 2
                R.id.user -> vp.currentItem = 3
            }
            true
        }
    }
    override fun getViewModel(): IMvvmBaseViewModel<*>? {
        return null
    }

    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onRetryBtnClick() {
    }

    companion object {
        fun start(context: Context) {
            MmkvHelper.getInstance().mmkv.encode("first", false)
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}