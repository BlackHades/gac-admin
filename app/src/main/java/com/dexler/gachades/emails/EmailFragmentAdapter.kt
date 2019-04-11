package com.dexler.gachades.emails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.dexler.gachades.databinding.EmailRowBinding
import androidx.core.content.ContextCompat.getSystemService





class EmailFragmentAdapter(context: Context?, results: ArrayList<Email>) : RecyclerView.Adapter<EmailFragmentAdapter.ViewHolder>(),
    Filterable {


    private val mInflater: LayoutInflater = LayoutInflater.from(context!!)
    private var mFilter: EmailFilter? = null
    private val TAG = "EmailFragmentAdapter"
    private var emails: ArrayList<Email> = results
    private var filteredData: ArrayList<Email> = results


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val emailRowBinding = EmailRowBinding.inflate(mInflater, parent, false)
        return ViewHolder(emailRowBinding)
    }

    override fun getItemCount(): Int {
        return emails.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val email = emails[position]
        holder.emailRowBinding.setEmail(email)
    }

    override fun getFilter(): Filter {
        if (mFilter == null) {
            mFilter = EmailFilter()
        }
        return mFilter!!
    }

    inner class ViewHolder(var emailRowBinding: EmailRowBinding) :
        RecyclerView.ViewHolder(emailRowBinding.root)


    private inner class EmailFilter : Filter() {

        override fun performFiltering(constraint: CharSequence?): Filter.FilterResults {
            val results = Filter.FilterResults()
            if (constraint != null && constraint.isNotEmpty()) {
                val filterList = ArrayList<Email>()
                for (i in filteredData.indices) {
                    if (filteredData[i].email.toUpperCase().contains(constraint.toString().toUpperCase())) {
                        val hh = Email(filteredData[i]._id, filteredData[i].email, filteredData[i].createdAt)
                        filterList.add(hh)
                    }
                }
                results.count = filterList.size
                results.values = filterList
            } else {
                results.count = filteredData.size
                results.values = filteredData
            }

            return results
        }

        override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
            emails = filterResults.values as ArrayList<Email>
            notifyDataSetChanged()
        }
    }

}
