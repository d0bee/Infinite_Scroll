package com.example.ccgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
// 바인딩시 필요한 import문
import com.example.ccgame.databinding.ActivityMainBinding
// MPCHART 사용시 필요한 import문
import com.github.mikephil.charting.data.*
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

        binding.chart.axisLeft.run {
            setDrawAxisLine(false)
            setDrawGridLines(false)
            textColor = Color.TRANSPARENT
        }

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

    data class CSStock(
        var createdAt: Long = 0,
        val open: Float,
        val close: Float,
        val shadowHigh: Float,
        val shadowLow: Float
    )

    object DataUtil {
        fun getCSStockData(): List<CSStock> {
            return listOf(
                CSStock(
                    createdAt = 0,
                    open = 222.8F,
                    close = 222.9F,
                    shadowHigh = 224.0F,
                    shadowLow = 222.2F
                ),
                CSStock(
                    createdAt = 1,
                    open = 222.0F,
                    close = 222.2F,
                    shadowHigh = 222.4F,
                    shadowLow = 222.0F
                ),
                CSStock(
                    createdAt = 2,
                    open = 222.2F,
                    close = 221.9F,
                    shadowHigh = 222.5F,
                    shadowLow = 221.5F
                ),
                CSStock(
                    createdAt = 3,
                    open = 222.4F,
                    close = 222.3F,
                    shadowHigh = 223.7F,
                    shadowLow = 222.1F
                ),
                CSStock(
                    createdAt = 4,
                    open = 221.6F,
                    close = 221.9F,
                    shadowHigh = 221.9F,
                    shadowLow = 221.5F
                ),
                CSStock(
                    createdAt = 5,
                    open = 221.8F,
                    close = 224.9F,
                    shadowHigh = 225.0F,
                    shadowLow = 221.0F
                ),
                CSStock(
                    createdAt = 6,
                    open = 225.0F,
                    close = 220.2F,
                    shadowHigh = 225.4F,
                    shadowLow = 219.2F
                ),
                CSStock(
                    createdAt = 7,
                    open = 222.2F,
                    close = 225.9F,
                    shadowHigh = 227.5F,
                    shadowLow = 222.2F
                ),
                CSStock(
                    createdAt = 8,
                    open = 226.0F,
                    close = 228.1F,
                    shadowHigh = 228.1F,
                    shadowLow = 225.1F
                ),
                CSStock(
                    createdAt = 9,
                    open = 227.6F,
                    close = 228.9F,
                    shadowHigh = 230.9F,
                    shadowLow = 226.5F
                ),
                CSStock(
                    createdAt = 10,
                    open = 228.6F,
                    close = 228.6F,
                    shadowHigh = 230.9F,
                    shadowLow = 228.0F
                )
            )
        }
    }
}