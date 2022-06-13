package com.example.movies.util

//se crea esta interfaz para abstraer funciones comunes en las vistas
interface UIBehavior {
    fun initUI()

    interface RecyclerView{
        fun initRecyclerView()
    }

    interface ViewPager{
        fun initViewPager2()
    }


}