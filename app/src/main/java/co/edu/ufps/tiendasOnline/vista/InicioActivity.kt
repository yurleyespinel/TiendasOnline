package co.edu.ufps.tiendasOnline.vista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import co.edu.ufps.tiendasOnline.R
import com.google.android.material.tabs.TabLayout

class InicioActivity : AppCompatActivity() {
    //usamos la carga lenta para decirle que lo vamos a inicializar después
    lateinit var tab: TabLayout
    lateinit var contenedor: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        //inicializamos las variables
        tab = findViewById(R.id.tab)
        contenedor = findViewById(R.id.contenedor)
        tab.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = MyAdapter(this, supportFragmentManager,tab.tabCount)
        contenedor.adapter = adapter
        contenedor.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab))
        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                contenedor.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }
    }
