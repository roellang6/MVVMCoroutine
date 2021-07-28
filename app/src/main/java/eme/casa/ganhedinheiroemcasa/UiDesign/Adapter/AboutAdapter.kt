package eme.casa.ganhedinheiroemcasa.UiDesign.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eme.casa.ganhedinheiroemcasa.Data.Model.ApiModel
import eme.casa.ganhedinheiroemcasa.R
import kotlinx.android.synthetic.main.aboutlyt.view.*

class AboutAdapter : RecyclerView.Adapter<AboutAdapter.DataHolderAbt>() {

    private var dlist = emptyList<ApiModel>()

    class DataHolderAbt(vView: View) : RecyclerView.ViewHolder(vView) {
        fun databindAbt(sdata: ApiModel) {
            itemView.apply {
                tvabtdes.text = sdata.descriptions
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataHolderAbt(
        LayoutInflater.from(parent.context).inflate(
            R.layout.aboutlyt, parent, false
        )
    )

    override fun onBindViewHolder(holder: DataHolderAbt, position: Int) {
        holder.databindAbt(dlist[position])
    }

    override fun getItemCount(): Int {
        return dlist.size
    }

    fun datasetAbt(gdata: List<ApiModel>) {
        this.dlist = gdata
        notifyDataSetChanged()
    }
}