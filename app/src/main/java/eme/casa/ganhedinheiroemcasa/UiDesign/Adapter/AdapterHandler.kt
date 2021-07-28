package eme.casa.ganhedinheiroemcasa.UiDesign.Adapter

import androidx.fragment.app.*
import eme.casa.ganhedinheiroemcasa.UiDesign.View.*
internal class AdapterHandler(fm: FragmentManager, var totalTabs: Int) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                MakeMoneyFragment()
            }
            1 -> {
                AboutFragment()
            }
            2 -> {
                AdvanceFragment()
            }
            3 -> {
                DisadvanceFragment()
            }
            else -> getItem(position)
        }
    }
}