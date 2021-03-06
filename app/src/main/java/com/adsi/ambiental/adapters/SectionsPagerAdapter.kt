package com.adsi.ambiental.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.adsi.ambiental.R
import com.adsi.ambiental.ui.main.fragments.PlayFragment
import com.adsi.ambiental.ui.main.fragments.RankFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> PlayFragment()
            else -> RankFragment()
        }
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlayFragment (defined as a static inner class below).
//        return PlayFragment.newInstance(position + 1)
    }

    override fun getPageTitle(position: Int): CharSequence? = context.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = 2
}