package com.example.complexjson

import android.app.ProgressDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private val URLstring = "http://167.172.46.142:1337/pest-alert-requests"
    internal lateinit var dataModelArrayList: ArrayList<DataModel>
    internal lateinit var imageArrayList: ArrayList<DataModel.ImageUrl>
    private var rvAdapter: RvAdapter? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler)

        fetchingJSON()

    }

    private fun fetchingJSON() {

        showSimpleProgressDialog(this, "Loading...", "Fetching Json", false)

        val stringRequest = StringRequest(
            Request.Method.GET, URLstring,
            { response ->
                removeSimpleProgressDialog()
                val obj = JSONArray(response)
                dataModelArrayList = ArrayList()
                imageArrayList = ArrayList()
//                val dataArray = obj.getJSONArray("data")
                for (i in 0 until obj.length()) {
                    val playerModel = DataModel()
                    val playerImage = DataModel.ImageUrl()
                    val dataobj = obj.getJSONObject(i)
                    playerModel.setNames(dataobj.getString("subject"))
                    playerModel.setCountrys(dataobj.getString("description"))
                    val newdata = dataobj.getJSONArray("images")
                    for (j in 0 until newdata.length()) {
                        val imagedata = newdata.getJSONObject(j)
                        Log.i("MyImageData: ", imagedata.getString("url"))

                        playerImage.setimageurl(imagedata.getString("url"))
                        imageArrayList.add(playerImage)
                    }
//                    playerModel.setCitys(dataobj.getString("city"))
//                    playerModel.setimgURLs(dataobj.getString("imgURL"))
                    dataModelArrayList.add(playerModel)
                }
                setupRecycler()

            },
            { error ->
                //displaying the error in toast if occurrs
                Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show()
            })

        // request queue
        val requestQueue = Volley.newRequestQueue(this)

        requestQueue.add(stringRequest)
    }

    private fun setupRecycler() {

        rvAdapter = RvAdapter(this, dataModelArrayList, imageArrayList)
        recyclerView!!.adapter = rvAdapter
        recyclerView!!.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

    }

    companion object {
        private var mProgressDialog: ProgressDialog? = null

        fun removeSimpleProgressDialog() {
            try {
                if (mProgressDialog != null) {
                    if (mProgressDialog!!.isShowing) {
                        mProgressDialog!!.dismiss()
                        mProgressDialog = null
                    }
                }
            } catch (ie: IllegalArgumentException) {
                ie.printStackTrace()

            } catch (re: RuntimeException) {
                re.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        fun showSimpleProgressDialog(
            context: Context, title: String,
            msg: String, isCancelable: Boolean
        ) {
            try {
                if (mProgressDialog == null) {
                    mProgressDialog = ProgressDialog.show(context, title, msg)
                    mProgressDialog!!.setCancelable(isCancelable)
                }

                if (!mProgressDialog!!.isShowing) {
                    mProgressDialog!!.show()
                }

            } catch (ie: IllegalArgumentException) {
                ie.printStackTrace()
            } catch (re: RuntimeException) {
                re.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}