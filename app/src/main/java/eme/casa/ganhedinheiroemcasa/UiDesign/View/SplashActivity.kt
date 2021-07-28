package eme.casa.ganhedinheiroemcasa.UiDesign.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kaiguanjs.utils.YQCUtils
import eme.casa.ganhedinheiroemcasa.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        checkData()
    }

     private fun checkData() {
        YQCUtils.splashAction(this) { version, downUrl ->
            logosplashs.visibility = View.VISIBLE
            logosplashs.alpha = 0f
            logosplashs.animate().setDuration(1400).alpha(1f).withEndAction {
                startActivity(Intent(this, IndexActivity::class.java))
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }
    }
}