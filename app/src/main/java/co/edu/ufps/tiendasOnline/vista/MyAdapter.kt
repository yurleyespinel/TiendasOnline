package co.edu.ufps.tiendasOnline.vista

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import co.edu.ufps.fragments.TiendasFragment
import co.edu.ufps.tiendasOnline.fragmentos.FavoritosFragment
import co.edu.ufps.tiendasOnline.fragmentos.PerfilFragment


class MyAdapter(
    var context: Context,
    fm: FragmentManager,
    val totalTabs: Int): FragmentPagerAdapter(fm){
    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> {
                TiendasFragment()
            }
            1 -> {
                FavoritosFragment()
            }
            2 -> {
                PerfilFragment()
            }
            else -> getItem(position)
        }

    }

}