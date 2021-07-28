package eme.casa.ganhedinheiroemcasa.UiDesign.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.synnapps.carouselview.CarouselView
import eme.casa.ganhedinheiroemcasa.R
import eme.casa.ganhedinheiroemcasa.UiDesign.Adapter.AdapterHandler

class IndexActivity : AppCompatActivity() {

    private lateinit var tabLy: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var carouselView: CarouselView
    private var dbackExit = false
    var alImage = intArrayOf(R.drawable.slide1, R.drawable.slide2, R.drawable.slide3, R.drawable.slide4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        tabLy = findViewById(R.id.tabdatalayout)
        viewPager = findViewById(R.id.tabpagerdata)
        carouselView = findViewById(R.id.slidepic)

        dataSetup()
    }

    private fun dataSetup(){

        carouselView.pageCount = alImage.size
        carouselView.setImageListener { position, imageView ->
            imageView.scaleType = ImageView.ScaleType.FIT_XY
            imageView.setImageResource(alImage[position])
        }

        tabLy.addTab(tabLy.newTab().setText("Fazer dinheiro"))
        tabLy.addTab(tabLy.newTab().setText("Cerca de"))
        tabLy.addTab(tabLy.newTab().setText("Vantagem"))
        tabLy.addTab(tabLy.newTab().setText("Desvantagem"))

        tabLy.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = AdapterHandler(supportFragmentManager, tabLy.tabCount)

        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLy))
        tabLy.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }
    override fun onBackPressed() {
        if (dbackExit) {
            super.onBackPressed()
            return
        }
        this.dbackExit = true
        Toast.makeText(this, "Clique em VOLTAR novamente para sair", Toast.LENGTH_SHORT).show()
        Handler().postDelayed( { dbackExit = false }, 2000)
    }
}