package com.example.animalname

import android.app.Activity
import android.media.Image
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatViewInflater
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class Play : AppCompatActivity(), View.OnClickListener {

//    publik mempadukan gambar, nama, sound
    lateinit var pager: ViewPager
    internal var nama = arrayOf("A", "B", "C", "D","E", "F","gajah", "singa", "monyet")
    internal var gambar = intArrayOf(R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e,R.drawable.f, R.drawable.gajah, R.drawable.singa, R.drawable.monyet)
    internal var suara = intArrayOf(R.raw.a,R.raw.b,R.raw.c,R.raw.d,R.raw.e,R.raw.f,R.raw.gajah, R.raw.singa, R.raw.monyet)
    lateinit var adapter: PagerAdapter
    internal var mp: MediaPlayer? = null

//    menyeleksi gambar dengan suara
    var onPage:
            ViewPager.OnPageChangeListener = object :
    ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
//            if (mp != null) {
//                mp!!.reset()
//                mp!!.release()
//            }
//            mp = MediaPlayer.create(this@Play, suara[position])
//            mp!!.start()
        }

        override fun onPageScrollStateChanged(state: Int) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_play)
        pager = findViewById(R.id.viewpager)as ViewPager
        adapter= GambarAdapter(this,gambar,nama)
        pager.adapter=adapter
        pager.setOnPageChangeListener(onPage)
        }
//stop untuk suara
    override fun onStop() {
        super.onStop()
        if (mp != null) {
            mp!!.reset()
            mp!!.release()
        }

    }

        private inner class GambarAdapter(play: Play, internal var gambar: IntArray,internal var nama: Array<String>) : PagerAdapter(){
            lateinit var inflater: LayoutInflater
            internal var activity: Activity

            init {
                this.activity= play

            }

            override fun getCount(): Int {
                return gambar.size
            }

//            bagian tampilan gambar

            override fun isViewFromObject(view: View, `object`: Any): Boolean {
                return view===`object` as ScrollView }

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                inflater = activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val tampil = inflater.inflate(R.layout.item_view_pager, container,false)
                val img = tampil.findViewById(R.id.imgbinatang) as ImageView
                val text = tampil.findViewById(R.id.textbinatang) as TextView
                img.setImageResource(gambar[position])
                text.text = nama[position]

//clik untuk suara
                img.setOnClickListener{
                    if (mp != null) {
                        mp!!.reset()
                        mp!!.release()
                    }
                    mp = MediaPlayer.create(this@Play, suara[position])
                    mp!!.start()
                }

                (container as ViewPager).addView(tampil)
                return tampil
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                (container as ViewPager).removeView(`object`as ScrollView)
            }
        }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}

