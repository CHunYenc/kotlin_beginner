package com.example.wifidemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    //    TODO init
    private var wifiManager: WifiManager? = null
    private var listView: ListView? = null
    private val size = 0
    private var results: List<ScanResult>? = null
    private val arrayList = arrayListOf<String>("123", "456")
    private var adapter: ArrayAdapter<*>? = null
    private var wifiPanel: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wifiPanel = Intent(Settings.Panel.ACTION_WIFI)

//      TODO use
        listView = findViewById(R.id.wifiList)
        val buttonScan = findViewById<Button>(R.id.scanBtn)
        wifiManager = getSystemService(Context.WIFI_SERVICE) as WifiManager

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)
//      TODO 驚嘆號的意思是 不為空的時候執行
        listView!!.adapter = adapter

        if (wifiManager!!.isWifiEnabled == false) {
            Toast.makeText(this, "WIFI 尚未開啟 .... ", Toast.LENGTH_SHORT).show()
            startActivity(wifiPanel)
        } else {
            Toast.makeText(this, "WIFI 已經開啟 .... ", Toast.LENGTH_SHORT).show()
        }

        buttonScan.setOnClickListener() {
            Toast.makeText(this, "Scanning WiFi ...", Toast.LENGTH_SHORT).show()
            arrayList.clear()
            scanWIFI()
        }
    }

    fun scanWIFI() {
        if (wifiManager!!.isWifiEnabled == true) {
            Toast.makeText(this, "請稍等 WiFi ...", Toast.LENGTH_LONG).show()
            registerReceiver(wifiReceiver, IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
        } else {
            Toast.makeText(this, "WIFI 尚未開啟 .... ", Toast.LENGTH_SHORT).show()
            startActivity(wifiPanel)
        }
    }

    var wifiReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
//            TODO("Not yet implemented")
            results = wifiManager!!.scanResults
            unregisterReceiver(this)
//            Log.d("WIFIScannerActivity", "WIFI SSID :" + wifiManager!!.scanResults)

            for (i in results!!) {
                var wifiSSID = ""
                wifiSSID = i.SSID
                Log.d("WIFIScannerActivity", "WIFI SSID :" + wifiSSID)

                Log.d(
                        "WIFIScannerActivity",
                        "scanResult.SSID: " + i.SSID + ", scanResult.capabilities: " + i.capabilities
                )
                arrayList.add(i.SSID + " - " + i.BSSID + " - " + i.level)
                arrayList.sortBy { i.level }
                adapter!!.notifyDataSetChanged()
            }
        }
    }
}