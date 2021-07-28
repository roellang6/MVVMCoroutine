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
import eme.casa.ganhedinheiroemcasa.UiDesign.Adapter.DisAdvanceAdapter
import eme.casa.ganhedinheiroemcasa.UiDesign.ViewModel.GanheViewModel

class DisadvanceFragment : Fragment() {

    private lateinit var swpatDis: SwipeRefreshLayout

    private lateinit var pbatDis: ProgressBar
    private lateinit var tvatDis: TextView
    private lateinit var rvatDis: RecyclerView

    private lateinit var ganheViewModel: GanheViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewlayout =  inflater.inflate(R.layout.fragment_disadvance, container, false)

        swpatDis = viewlayout.findViewById(R.id.disSwp)
        pbatDis = viewlayout.findViewById(R.id.disPb)
        tvatDis = viewlayout.findViewById(R.id.disTv)
        rvatDis = viewlayout.findViewById(R.id.disrv)

        ganheViewModel = ViewModelProvider(this).get(GanheViewModel::class.java)

        swpatDis.setOnRefreshListener { checkMainGanhe() }

        checkMainGanhe()

        return viewlayout
    }

    private fun checkMainGanhe() {
        ganheViewModel.funDisadvance()
        ganheViewModel.liveganheDis.observe(viewLifecycleOwner, {
            if (it != null) {
                displayDataGet(it)

            } else {
                backupDataCheck()
            }
        })
    }

    private fun backupDataCheck() {
        ganheViewModel.funDisadvanceBp()
        ganheViewModel.liveganheDisBp.observe(viewLifecycleOwner, {
            if (it != null) {
                displayDataGet(it)

            } else {
                tvatDis.visibility = View.VISIBLE
                pbatDis.visibility = View.GONE
                rvatDis.visibility = View.GONE
                swpatDis.isRefreshing = false
            }
        })
    }

    private fun displayDataGet(it: List<ApiModel>) {
        val gdata = DisAdvanceAdapter()
        gdata.datasetDis(it)

        rvatDis.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = gdata
        }
        rvatDis.visibility = View.VISIBLE
        pbatDis.visibility = View.GONE
        tvatDis.visibility = View.GONE
        swpatDis.isRefreshing = false

    }
}