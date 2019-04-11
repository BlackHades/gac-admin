package com.dexler.gachades.emails


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Callback

import com.dexler.gachades.R
import com.dexler.gachades.http.RetrofitClient
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Response
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager


/**
 * A simple [Fragment] subclass.
 *
 */
class EmailFragment : Fragment() {

    lateinit var rv: RecyclerView
    lateinit var pb: ProgressBar
    lateinit var adapter: EmailFragmentAdapter
    val TAG = "EmailFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_email, container, false)
        pb = view.findViewById(R.id.progressBar)
        rv = view.findViewById(R.id.rv)
        getEmails()
        return view
    }

    private fun getEmails() {
        val retrofitClient = RetrofitClient(context!!)
        retrofitClient.apiService.getEmails().enqueue(object : Callback<com.dexler.gachades.http.Response<ArrayList<Email>>>{
            override fun onFailure(call: Call<com.dexler.gachades.http.Response<ArrayList<Email>>>, t: Throwable) {
                Log.e(TAG,t.toString())
            }

            override fun onResponse(call: Call<com.dexler.gachades.http.Response<ArrayList<Email>>>, response: Response<com.dexler.gachades.http.Response<ArrayList<Email>>>) {
                Log.e(TAG,response.body().toString())
                if(response.isSuccessful && response.body()!!.status == 1){
                    initRv(response.body()!!.data)
                }else{
                    Snackbar.make(view!!,response.errorBody().toString(),Snackbar.LENGTH_SHORT)
                    Log.e(TAG,response.errorBody().toString())
                }
            }

        })
    }


    private fun initRv(emails:ArrayList<Email>) {
        pb.visibility = View.GONE
        rv.visibility = View.VISIBLE
        //init adapter
        //adapt it to RV
        val llm = LinearLayoutManager(context)
        rv.layoutManager = llm
        rv.itemAnimator = DefaultItemAnimator()
        adapter = EmailFragmentAdapter(context,emails)
        rv.adapter = adapter
    }


}
