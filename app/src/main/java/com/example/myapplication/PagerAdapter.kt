package com.example.myapplication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(fm: Fragment) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return ExampleFragment.newInstance("Ejemplo1",getAquaticAnimals())
            1 -> return ExampleFragment.newInstance("Ejemplo2",getLandAnimals())
            2 -> return ExampleFragment.newInstance("Ejemplo3",getFlyingAnimals())
        }
        return ExampleFragment.newInstance("Ejemplo4",getAquaticAnimals())
    }

    private fun getAquaticAnimals() : ArrayList<Animal>{
        val aquaticAnimals = ArrayList<Animal>()
        aquaticAnimals.add(Animal("Pez payaso","Descripcion del pez",R.drawable.clow))
        aquaticAnimals.add(Animal("Delfin","Descripcion del Delfin",R.drawable.delfin))
        aquaticAnimals.add(Animal("Tortuga","Descripcion de la tortuga",R.drawable.tortuga))
        return aquaticAnimals
    }

    private fun getFlyingAnimals() : ArrayList<Animal>{
        val aquaticAnimals = ArrayList<Animal>()
        aquaticAnimals.add(Animal("Buho","Descripcion del pez",R.drawable.clow))
        aquaticAnimals.add(Animal("Loro","Descripcion del Delfin",R.drawable.delfin))
        aquaticAnimals.add(Animal("Paloma","Descripcion de la tortuga",R.drawable.tortuga))
        return aquaticAnimals
    }

    private fun getLandAnimals() : ArrayList<Animal>{
        val aquaticAnimals = ArrayList<Animal>()
        aquaticAnimals.add(Animal("Tigre","Descripcion del pez",R.drawable.clow))
        aquaticAnimals.add(Animal("Oso","Descripcion del Delfin",R.drawable.delfin))
        aquaticAnimals.add(Animal("Perro","Descripcion de la tortuga",R.drawable.tortuga))
        return aquaticAnimals
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Ejemplo ${position+1}"
    }
}