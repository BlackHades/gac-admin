package com.dexler.gachades.installations

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.dexler.gachades.databinding.InstallationRowBinding
import com.dexler.gachades.emails.Email

class InstallationFragmentAdapter(context: Context?, results: ArrayList<Installation>) : RecyclerView.Adapter<InstallationFragmentAdapter.ViewHolder>(),
    Filterable {


    private val mInflater: LayoutInflater = LayoutInflater.from(context!!)
    private var mFilter: EmailFilter? = null
    private val TAG = "InstallationFragmentAdapter"
    private var installations: ArrayList<Installation> = results
    private var filteredData: ArrayList<Installation> = results


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val installationRowBinding = InstallationRowBinding.inflate(mInflater, parent, false)
        return ViewHolder(installationRowBinding)
    }

    override fun getItemCount(): Int {
        return installations.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val installation = installations[position]
        holder.installationRowBinding.installation = installation
    }

    override fun getFilter(): Filter {
        if (mFilter == null) {
            mFilter = EmailFilter()
        }
        return mFilter!!
    }

    inner class ViewHolder(var installationRowBinding: InstallationRowBinding) :
        RecyclerView.ViewHolder(installationRowBinding.root)


    private inner class EmailFilter : Filter() {

        override fun performFiltering(constraint: CharSequence?): Filter.FilterResults {
            val results = Filter.FilterResults()
            if (constraint != null && constraint.isNotEmpty()) {
                val filterList = ArrayList<Email>()
//                for (i in filteredData.indices) {
//                    if (filteredData[i].uuid.toUpperCase().contains(constraint.toString().toUpperCase())) {
//                        val hh = Email(filteredData[i]._id, filteredData[i].uuid, filteredData[i].createdAt)
//                        filterList.add(hh)
//                    }
//                }
                results.count = filterList.size
                results.values = filterList
            } else {
                results.count = filteredData.size
                results.values = filteredData
            }

            return results
        }

        override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
            installations = filterResults.values as ArrayList<Installation>
            notifyDataSetChanged()
        }
    }

}