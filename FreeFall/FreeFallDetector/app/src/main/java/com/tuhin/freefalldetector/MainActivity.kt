package com.tuhin.freefalldetector

import com.tuhin.skfreefall.FallDetectorCallback
import com.tuhin.skfreefall.FallDetector
import com.tuhin.skfreefall.data.model.FreeFallData

import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var fallDetector: FallDetector
    private lateinit var fallEventsRecyclerView: RecyclerView
    private var viewAdapter: RecyclerView.Adapter<*>? = null
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val dataList: MutableList<FreeFallData> = ArrayList()
    private val disposable = CompositeDisposable()

    companion object {
        private const val MIN_THRESHOLD = 1.0
        private const val MAX_THRESHOLD = 9.0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initGUI()
        initFreeFall()
    }

    override fun onDestroy() {
        super.onDestroy()
        fallDetector.stopDetection()
        disposable.dispose()
    }

    private fun initGUI() {
        viewManager = LinearLayoutManager(this)
        viewAdapter = FreeFallAdapter(this, dataList)

        fallEventsRecyclerView =
            findViewById<RecyclerView>(R.id.fallListRecyclerView).apply {
                layoutManager = viewManager
                adapter = viewAdapter
            }

        clearButton.setOnClickListener {
            disposable.add(fallDetector.deleteAllEvents().observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Toast.makeText(
                        this,
                        getString(R.string.success_message),
                        Toast.LENGTH_SHORT
                    ).show()
                })
        }
    }

    private fun initFreeFall() {
        fallDetector = FallDetector(this, object : FallDetectorCallback {
            override fun onFallDetected(freeFallData: FreeFallData) {
                val latestFallData: String = String.format(
                    getString(R.string.event_info),
                    freeFallData.timeStamp,
                    freeFallData.duration
                )
                runOnUiThread {
                    fallText.text = latestFallData
                }
            }
        })

        if (!fallDetector.isRunning()) {
            fallDetector.apply {
                setMinThreshold(MIN_THRESHOLD)
                setMaxThreshold(MAX_THRESHOLD)
                startDetection()
                disposable.add(fallDetector.getAllEvents().observeOn(AndroidSchedulers.mainThread()).subscribe(
                    {
                        (viewAdapter as FreeFallAdapter).updateData(it)
                    }) {
                    Log.d(TAG, "${it.localizedMessage}")
                })
            }
        }
    }

}

