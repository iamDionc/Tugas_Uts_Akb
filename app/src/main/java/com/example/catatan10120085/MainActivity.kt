package com.example.catatan10120085

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.catatan10120085.adapter.AdapterViewPager
import com.example.catatan10120085.database.AppDatabase
import com.example.catatan10120085.databinding.ActivityMainBinding
import com.example.catatan10120085.fragment.CatatanFragment
import com.example.catatan10120085.fragment.InformationFragment
import com.example.catatan10120085.fragment.ProfileFragment
import java.util.Objects
//10120085 - Dion Cahyan IF-2
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var list = arrayListOf<Fragment>()
    private lateinit var database: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = AppDatabase.getAppDataBase(this)!!

        initData()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                when(position) {
                    0 -> binding.bottomNav.selectedItemId = R.id.nav_profile
                    1 -> binding.bottomNav.selectedItemId = R.id.nav_note
                    2 -> binding.bottomNav.selectedItemId = R.id.nav_info
                }

                super.onPageSelected(position)
            }
        })

        binding.bottomNav.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.nav_profile -> {
                    binding.viewPager.post {
                        binding.viewPager.setCurrentItem(0, true)
                    }
                }
                R.id.nav_note -> {
                    binding.viewPager.post {
                        binding.viewPager.setCurrentItem(1, true)
                    }
                }
                else -> {
                    binding.viewPager.post {
                        binding.viewPager.setCurrentItem(2, true)
                    }
                }
            }

        }
    }

    private fun initData() {
        list.add(ProfileFragment())
        list.add(CatatanFragment())
        list.add(InformationFragment())

        val adapterViewPager = AdapterViewPager(this, list)
        binding.viewPager.adapter = adapterViewPager
    }
}