package apero.aperosg.monetizationsample

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import apero.aperosg.monetizationsample.java.JavaAdsActivity
import apero.aperosg.monetizationsample.javafragment.JavaFragmentActivity
import apero.aperosg.monetizationsample.kotlin.KotlinAdsActivity
import apero.aperosg.monetizationsample.kotlinfragment.KotlinFragmentActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.kotlinBtn).setOnClickListener {
            startActivity(Intent(this, KotlinAdsActivity::class.java))
        }

        findViewById<Button>(R.id.kotlinFragmentBtn).setOnClickListener {
            startActivity(Intent(this, KotlinFragmentActivity::class.java))
        }

        findViewById<Button>(R.id.javaBtn).setOnClickListener {
            startActivity(Intent(this, JavaAdsActivity::class.java))
        }

        findViewById<Button>(R.id.javaFragmentBtn).setOnClickListener {
            startActivity(Intent(this, JavaFragmentActivity::class.java))
        }
    }
}