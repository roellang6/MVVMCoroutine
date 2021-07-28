package eme.casa.ganhedinheiroemcasa.UiDesign.Adapter

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import eme.casa.ganhedinheiroemcasa.Data.Model.ApiModel
import eme.casa.ganhedinheiroemcasa.R
import kotlinx.android.synthetic.main.makemoneyly.view.*

class MoneyAdapter(var context: Context): RecyclerView.Adapter<MoneyAdapter.DataHolderMoney>() {

    private var dlist = emptyList<ApiModel>()

    class DataHolderMoney(vView: View) : RecyclerView.ViewHolder(vView) {

        fun databindMoney(sdata: ApiModel) {
            itemView.apply {
                Glide.with(this).load(sdata.image).into(moneImgid)
                titleid.text = sdata.subtitle
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataHolderMoney(
        LayoutInflater.from(parent.context).inflate(
            R.layout.makemoneyly, parent, false
        )
    )

    override fun onBindViewHolder(holder: DataHolderMoney, position: Int) {
        holder.databindMoney(dlist[position])

        val dearn = dlist[position]

        val dlgEarn = Dialog(context)
        dlgEarn.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlgEarn.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dlgEarn.window?.setGravity(Gravity.BOTTOM)
        dlgEarn.setContentView(R.layout.viewmakemoney)

        val subEarn = dlgEarn.findViewById<TextView>(R.id.tvmoneysub)
        val desEarn = dlgEarn.findViewById<TextView>(R.id.tvmoneydes)

        holder.itemView.setOnClickListener {
            subEarn.text = dearn.subtitle
            desEarn.text = dearn.descriptions
            dlgEarn.show()
        }


    }

    override fun getItemCount(): Int {
        return dlist.size
    }

    fun datasMoney(gdata: List<ApiModel>) {
        this.dlist = gdata
        notifyDataSetChanged()
    }
}