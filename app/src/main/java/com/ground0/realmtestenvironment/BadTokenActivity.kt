package com.ground0.realmtestenvironment

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.WorkerThread
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import butterknife.BindAnim
import butterknife.BindView

import butterknife.ButterKnife

/**
 * Created by 00-00-00 on 27/12/17.
 */

class BadTokenActivity : AppCompatActivity() {

    lateinit var logText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_leak)
        logText = findViewById(R.id.a_leak_log) as TextView

        appendLog("Starting activity")
        appendLog("Starting thread")


        AsyncExample().execute()

        Thread(Runnable {
            appendLog("Thread1 sleeping for 3 seconds")
            Thread.sleep(3 * 1000)
            appendLog("Finishing activity")
            this@BadTokenActivity.finish()
        }).start()
    }

    private fun showDialog() {
        if (this.isDestroyed || this.isFinishing) return
        AlertDialog.Builder(this).setMessage("Crash ?").show()
    }


    private var log: String = "Starting..."

    @SuppressLint("SetTextI18n")
    fun appendLog(string: String) {
        log = "$log\n$string"
        Log.d("Debug", string)
    }

    inner class AsyncExample : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg p0: String): String {
            Thread(Runnable { Thread.sleep(6 * 1000) }).start()
            return ""
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            this@BadTokenActivity.appendLog("Thread2 sleeping for 6 seconds")
            Thread.sleep(6 * 1000)
            this@BadTokenActivity.appendLog("Show dialog")
            this@BadTokenActivity.showDialog()
        }
    }

}
