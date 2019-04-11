package com.dexler.gachades.installations


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.dexler.gachades.R
import com.dexler.gachades.emails.Email
import com.dexler.gachades.emails.EmailFragmentAdapter
import com.dexler.gachades.http.Response
import com.dexler.gachades.http.RetrofitClient
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class InstallationFragment : Fragment() {
    lateinit var rv: RecyclerView
    lateinit var pb: ProgressBar
    lateinit var adapter: InstallationFragmentAdapter
    val TAG = "InstallationFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_email, container, false)
        pb = view.findViewById(R.id.progressBar)
        rv = view.findViewById(R.id.rv)
        getInstallations();
        return view
    }

    private fun getInstallations() {
        val retrofitClient = RetrofitClient(context!!)
        retrofitClient.apiService.getInstallations().enqueue(object : Callback<Response<ArrayList<Installation>>> {
            override fun onFailure(call: Call<Response<ArrayList<Installation>>>, t: Throwable) {
                Log.e(TAG,t.toString())
            }

            override fun onResponse(call: Call<Response<ArrayList<Installation>>>, response: retrofit2.Response<Response<ArrayList<Installation>>>) {
                Log.e(TAG,response.body().toString())
                if(response.isSuccessful && response.body()!!.status == 1){
                    initRv(response.body()!!.data)
                }else{
                    Snackbar.make(view!!,response.errorBody().toString(), Snackbar.LENGTH_SHORT)
                    Log.e(TAG,response.errorBody().toString())
                }
            }

        })    }


    private fun initRv(installations:ArrayList<Installation>) {
        pb.visibility = View.GONE
        rv.visibility = View.VISIBLE
        //init adapter
        //adapt it to RV
        val llm = LinearLayoutManager(context)
        rv.layoutManager = llm
        rv.itemAnimator = DefaultItemAnimator()
        adapter = InstallationFragmentAdapter(context,installations)
        rv.adapter = adapter
    }



}
