package com.example.ccgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
// 바인딩시 필요한 import문
import com.example.ccgame.databinding.ActivityMainBinding
// MPCHART 사용시 필요한 import문
import com.github.mikephil.charting.data.*
// 그외
import android.graphics.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val entries = ArrayList<CandleEntry>()
        for (csStock in DataUtil.getCSStockData()) {
            entries.add(CandleEntry(
                csStock.createdAt.toFloat(),
                csStock.shadowHigh,
                csStock.shadowLow,
                csStock.open,
                csStock.close
            ))
        }

        val dataSet = CandleDataSet(entries, "").apply {
            // 심지 부분
            shadowColor = Color.LTGRAY
            shadowWidth = 1F

            // 음봄
            decreasingColor = Color.BLUE
            decreasingPaintStyle = Paint.Style.FILL
            // 양봉
            increasingColor = Color.RED
            increasingPaintStyle = Paint.Style.FILL

            neutralColor = Color.DKGRAY
            setDrawValues(false)
            // 터치시 노란 선 제거
            highLightColor = Color.TRANSPARENT
        }

        // 좌측 눈금, 박스라인
        binding.chart.axisLeft.run {
            setDrawAxisLine(false)
            setDrawGridLines(false)
            textColor = Color.TRANSPARENT
        }

        // 우측 눈금
        binding.chart.axisRight.run {
            isEnabled = false
        }

        // X 축
        binding.chart.xAxis.run {
            textColor = Color.TRANSPARENT
            setDrawAxisLine(false)
            setDrawGridLines(false)
            setAvoidFirstLastClipping(true)
        }

        // 범례
        binding.chart.legend.run {
            isEnabled = false
        }

        binding.chart.apply {
            this.data = CandleData(dataSet)
            description.isEnabled = false
            isHighlightPerDragEnabled = true
            requestDisallowInterceptTouchEvent(true)
            invalidate()
        }
    }
}
