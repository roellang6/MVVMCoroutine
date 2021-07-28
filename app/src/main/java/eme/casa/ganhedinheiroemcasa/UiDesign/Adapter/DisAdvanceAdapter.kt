package eme.casa.ganhedinheiroemcasa.UiDesign.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eme.casa.ganhedinheiroemcasa.Data.Model.ApiModel
import eme.casa.ganhedinheiroemcasa.R
import kotlinx.android.synthetic.main.disadvancely.view.*


class DisAdvanceAdapter : RecyclerView.Adapter<DisAdvanceAdapter.DataHolderDis>() {

    private var dlist = emptyList<ApiModel>()

    class DataHolderDis(vView: View) : RecyclerView.ViewHolder(vView) {
        fun databindDis(sdata: ApiModel) {
            itemView.apply {
                tvdissub.text = sdata.subtitle
                tvdisdes.text = sdata.descriptions
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataHolderDis(
        LayoutInflater.from(parent.context).inflate(
            R.layout.disadvancely, parent, false
        )
    )

    override fun onBindViewHolder(holder: DataHolderDis, position: Int) {
        holder.databindDis(dlist[position])
    }

    override fun getItemCount(): Int {
        return dlist.size
    }

    fun datasetDis(gdata: List<ApiModel>) {
        this.dlist = gdata
        notifyDataSetChanged()
    }
}