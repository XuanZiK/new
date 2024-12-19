package com.example.volleyballonline


import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction
import com.example.volleyballonline.fragment.FragmentA
import com.example.volleyballonline.fragment.FragmentB
import com.example.volleyballonline.fragment.FragmentC
import com.example.volleyballonline.fragment.FragmentD
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.selects.select

class MainActivity : AppCompatActivity() {
    var fragmenta: FragmentA?=null
    var fragmentb: FragmentB?=null
    var fragmentc: FragmentC?=null
    var fragmentd: FragmentD?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        selectedFragment(0)
        bottomNavigationView?.setOnNavigationItemSelectedListener {menuItem->
            if (menuItem.itemId==R.id.AAA){
                selectedFragment(0)
            }else if (menuItem.itemId==R.id.BBB) {
                selectedFragment(1)
            }else if (menuItem.itemId==R.id.CCC){
                selectedFragment(2)
            }else{
                selectedFragment(3)
            }
            true
        }
    }

    fun selectedFragment(postion:Int){
        val fragmentTransaction =supportFragmentManager.beginTransaction()
        hideFragment(fragmentTransaction)
        if (postion==0) {
            if (fragmenta == null) {
                fragmenta = FragmentA()
                fragmentTransaction.add(R.id.content, fragmenta!!)
            } else {
                fragmentTransaction.show(fragmenta!!)
            }
        }else if (postion==1){
            if (fragmentb == null){
                fragmentb = FragmentB()
                fragmentTransaction.add(R.id.content,fragmentb!!)
            }else{
                fragmentTransaction.show(fragmentb!!)
            }
        }else if (postion==3){
            if (fragmentd == null){
                fragmentd = FragmentD()
                fragmentTransaction.add(R.id.content,fragmentd!!)
            }else{
                fragmentTransaction.show(fragmentd!!)
            }
        }else{
            if (fragmentc==null){
                fragmentc = FragmentC()
                fragmentTransaction.add(R.id.content,fragmentc!!)
            }else{
                fragmentTransaction.show(fragmentc!!)
            }
        }
        //一定要提交
        fragmentTransaction.commit()
    }

    fun hideFragment(fragmentTransaction: FragmentTransaction){
        if(fragmenta!=null){
            fragmentTransaction.hide(fragmenta!!)
        }
        if (fragmentb!=null){
            fragmentTransaction.hide(fragmentb!!)
        }
        if (fragmentc!=null){
            fragmentTransaction.hide(fragmentc!!)
        }
        if (fragmentd!=null){
            fragmentTransaction.hide(fragmentd!!)
        }
    }

}





