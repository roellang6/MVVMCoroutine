package eme.casa.ganhedinheiroemcasa.UiDesign.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import eme.casa.ganhedinheiroemcasa.Data.Model.ApiModel
import eme.casa.ganhedinheiroemcasa.R
import eme.casa.ganhedinheiroemcasa.UiDesign.Adapter.AdvanceAdapter
import eme.casa.ganhedinheiroemcasa.UiDesign.ViewModel.GanheViewModel

class AdvanceFragment : Fragment() {

    private lateinit var swpatAdv: SwipeRefreshLayout

    private lateinit var pbatAdv: ProgressBar
    private lateinit var tvatAdv: TextView
    private lateinit var rvatAdv: RecyclerView

    private lateinit var ganheViewModel: GanheViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewlayout =  inflater.inflate(R.layout.fragment_advance, container, false)

        swpatAdv = viewlayout.findViewById(R.id.advSwp)
        pbatAdv = viewlayout.findViewById(R.id.advPb)
        tvatAdv = viewlayout.findViewById(R.id.advTv)
        rvatAdv = viewlayout.findViewById(R.id.advrv)

        ganheViewModel = ViewModelProvider(this).get(GanheViewModel::class.java)

        swpatAdv.setOnRefreshListener { checkMainGanhe() }

        checkMainGanhe()

        return viewlayout
    }

    private fun checkMainGanhe() {
        ganheViewModel.funAdvance()
        ganheViewModel.liveganheAdv.observe(viewLifecycleOwner, {
            if (it != null) {
                displayDataGet(it)

            } else {
                backupDataCheck()
            }
        })
    }

    private fun backupDataCheck() {
        ganheViewModel.funAdvanceBp()
        ganheViewModel.liveganheAdvBp.observe(viewLifecycleOwner, {
            if (it != null) {
                displayDataGet(it)

            } else {
                tvatAdv.visibility = View.VISIBLE
                pbatAdv.visibility = View.GONE
                rvatAdv.visibility = View.GONE
                swpatAdv.isRefreshing = false
            }
        })
    }

    private fun displayDataGet(it: List<ApiModel>) {
        val gdata = AdvanceAdapter(requireContext())
        gdata.datasetAdv(it)

        rvatAdv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = gdata
        }
        rvatAdv.visibility = View.VISIBLE
        pbatAdv.visibility = View.GONE
        tvatAdv.visibility = View.GONE
        swpatAdv.isRefreshing = false

    }
}