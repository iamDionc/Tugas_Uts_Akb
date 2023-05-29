package com.example.catatan10120085.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
//10120085 - Dion Cahyan IF-2
class AdapterViewPager(fragmentActivity: FragmentActivity, val arr : ArrayList<Fragment>) :
    FragmentStateAdapter(fragmentActivity){


    override fun getItemCount(): Int = arr.size

    override fun createFragment(position: Int): Fragment {
        return arr[position]
    }
}