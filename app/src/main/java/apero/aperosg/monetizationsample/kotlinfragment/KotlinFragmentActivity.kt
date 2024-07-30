package apero.aperosg.monetizationsample.kotlinfragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import apero.aperosg.monetizationsample.R
import apero.aperosg.monetizationsample.databinding.ActivityFragmentBinding

class KotlinFragmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())

        supportFragmentManager.beginTransaction().add(
            R.id.container, KotlinAdsFragment::class.java, null
        ).commit()
    }
}