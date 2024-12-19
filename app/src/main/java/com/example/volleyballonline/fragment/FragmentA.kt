package com.example.volleyballonline.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.volleyballonline.MySQLHelper
import com.example.volleyballonline.R
import android.os.Looper
import android.os.Handler

class FragmentA : Fragment() {

    private val mySQLHelper = MySQLHelper()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_a, container, false)

        // 初始化轮播图组件
        initSlider(view)

        val textViewname11: TextView = view.findViewById(R.id.activity1View1)
        val textViewname12: TextView = view.findViewById(R.id.activity1View2)
        val textViewname13: TextView = view.findViewById(R.id.activity1View3)

        val textViewname21: TextView = view.findViewById(R.id.activity2View1)
        val textViewname22: TextView = view.findViewById(R.id.activity2View2)
        val textViewname23: TextView = view.findViewById(R.id.activity2View3)

        val textViewname31: TextView = view.findViewById(R.id.activity3View1)
        val textViewname32: TextView = view.findViewById(R.id.activity3View2)
        val textViewname33: TextView = view.findViewById(R.id.activity3View3)

        val textViewname41: TextView = view.findViewById(R.id.activity4View1)
        val textViewname42: TextView = view.findViewById(R.id.activity4View2)
        val textViewname43: TextView = view.findViewById(R.id.activity4View3)

        // 使用协程来调用挂起函数
        lifecycleScope.launch(Dispatchers.IO) {
            //第一个活动块
            val name1: String? = mySQLHelper.findActivityName(1)
            val location1: String? = mySQLHelper.findActivityLocation(1)
            val time1: String? = mySQLHelper.findActivityTime(1)

            val name2: String? = mySQLHelper.findActivityName(2)
            val location2: String? = mySQLHelper.findActivityLocation(2)
            val time2: String? = mySQLHelper.findActivityTime(2)

            val name3: String? = mySQLHelper.findActivityName(3)
            val location3: String? = mySQLHelper.findActivityLocation(3)
            val time3: String? = mySQLHelper.findActivityTime(3)

            val name4: String? = mySQLHelper.findActivityName(5)
            val location4: String? = mySQLHelper.findActivityLocation(5)
            val time4: String? = mySQLHelper.findActivityTime(5)

            withContext(Dispatchers.Main) {
                // 在主线程中更新UI
                textViewname11.text = name1
                textViewname12.text = location1
                textViewname13.text = time1

                textViewname21.text = name2
                textViewname22.text = location2
                textViewname23.text = time2

                textViewname31.text = name3
                textViewname32.text = location3
                textViewname33.text = time3

                textViewname41.text = name4
                textViewname42.text = location4
                textViewname43.text = time4
            }
        }

        return view
    }
    private fun initSlider(view: View) {
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPagerSlider)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayoutIndicator)

        // 图片
        val imageResources = listOf(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3
        )

        // 适配器
        val sliderAdapter = SliderAdapter(imageResources)
        viewPager.adapter = sliderAdapter

        // 指示器
        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        // 自动滚动逻辑
        val handler = Handler(Looper.getMainLooper())
        val runnable = Runnable {
            viewPager.setCurrentItem((viewPager.currentItem + 1) % sliderAdapter.itemCount, true)
        }
        val delay = 2000L // 每2秒切换一次

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, delay)
            }
        })

        handler.postDelayed(runnable, delay)
    }
}
