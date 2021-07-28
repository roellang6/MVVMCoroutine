package eme.casa.ganhedinheiroemcasa.UiDesign.Adapter

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eme.casa.ganhedinheiroemcasa.Data.Model.ApiModel
import eme.casa.ganhedinheiroemcasa.R
import kotlinx.android.synthetic.main.advancely.view.*

class AdvanceAdapter(var context: Context): RecyclerView.Adapter<AdvanceAdapter.DataHolderAdv>() {

    private var dlist = emptyList<ApiModel>()

    class DataHolderAdv(vView: View) : RecyclerView.ViewHolder(vView) {

        fun databindAdv(sdata: ApiModel) {
            itemView.apply {
                idtitleadv.text = sdata.subtitle
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataHolderAdv(
        LayoutInflater.from(parent.context).inflate(
            R.layout.advancely, parent, false
        )
    )

    override fun onBindViewHolder(holder: DataHolderAdv, position: Int) {
        holder.databindAdv(dlist[position])

        val listadv = dlist[position]

        val dlgAdv = Dialog(context)
        dlgAdv.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlgAdv.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dlgAdv.window?.setGravity(Gravity.BOTTOM)
        dlgAdv.setContentView(R.layout.viewadvance)

        val advSub = dlgAdv.findViewById<TextView>(R.id.tvadvancesub)
        val advDes = dlgAdv.findViewById<TextView>(R.id.tvadvancedes)

        holder.itemView.setOnClickListener {
            advSub.text = listadv.subtitle
            advDes.text = listadv.descriptions
            dlgAdv.show()
        }
    }

    override fun getItemCount(): Int {
        return dlist.size
    }

    fun datasetAdv(gdata: List<ApiModel>) {
        this.dlist = gdata
        notifyDataSetChanged()
    }
}