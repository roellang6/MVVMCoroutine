package eme.casa.ganhedinheiroemcasa.UiDesign.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import eme.casa.ganhedinheiroemcasa.Data.Model.ApiModel
import eme.casa.ganhedinheiroemcasa.R
import eme.casa.ganhedinheiroemcasa.UiDesign.Adapter.MoneyAdapter
import eme.casa.ganhedinheiroemcasa.UiDesign.ViewModel.GanheViewModel

class MakeMoneyFragment : Fragment() {

    private lateinit var swpatMoney: SwipeRefreshLayout

    private lateinit var pbatMoney: ProgressBar
    private lateinit var tvatMoney: TextView
    private lateinit var rvatMoney: RecyclerView

    private lateinit var ganheViewModel: GanheViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewlayout =  inflater.inflate(R.layout.fragment_make_money, container, false)

        swpatMoney = viewlayout.findViewById(R.id.moneySwp)
        pbatMoney = viewlayout.findViewById(R.id.moneyPb)
        tvatMoney = viewlayout.findViewById(R.id.moneyTv)
        rvatMoney = viewlayout.findViewById(R.id.moneyrv)

        ganheViewModel = ViewModelProvider(this).get(GanheViewModel::class.java)

        swpatMoney.setOnRefreshListener { checkMainGanhe() }

        checkMainGanhe()

        return viewlayout
    }

    private fun checkMainGanhe() {
        ganheViewModel.funEarn()
        ganheViewModel.liveganheEarn.observe(viewLifecycleOwner, {
            if (it != null) {
                displayDataGet(it)

            } else {
                backupDataCheck()
            }
        })
    }

    private fun backupDataCheck() {
        ganheViewModel.funEarnBp()
        ganheViewModel.liveganheEarnBp.observe(viewLifecycleOwner, {
            if (it != null) {
                displayDataGet(it)

            } else {
                tvatMoney.visibility = View.VISIBLE
                pbatMoney.visibility = View.GONE
                rvatMoney.visibility = View.GONE
                swpatMoney.isRefreshing = false
            }
        })
    }

    private fun displayDataGet(it: List<ApiModel>) {
        val gdata = MoneyAdapter(requireContext())
        gdata.datasMoney(it)

        rvatMoney.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            adapter = gdata
        }
        rvatMoney.visibility = View.VISIBLE
        pbatMoney.visibility = View.GONE
        tvatMoney.visibility = View.GONE
        swpatMoney.isRefreshing = false

    }
}