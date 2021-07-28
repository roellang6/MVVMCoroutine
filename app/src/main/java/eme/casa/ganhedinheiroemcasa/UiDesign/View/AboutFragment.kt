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
import eme.casa.ganhedinheiroemcasa.UiDesign.Adapter.AboutAdapter
import eme.casa.ganhedinheiroemcasa.UiDesign.ViewModel.GanheViewModel

class AboutFragment : Fragment() {

    private lateinit var swpatAbt: SwipeRefreshLayout

    private lateinit var pbatAbt: ProgressBar
    private lateinit var tvatAbt: TextView
    private lateinit var rvatAbt: RecyclerView

    private lateinit var ganheViewModel: GanheViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewlayout =  inflater.inflate(R.layout.fragment_about, container, false)

        swpatAbt = viewlayout.findViewById(R.id.abtSwp)
        pbatAbt = viewlayout.findViewById(R.id.abtPb)
        tvatAbt = viewlayout.findViewById(R.id.abtTv)
        rvatAbt = viewlayout.findViewById(R.id.abtrv)

        ganheViewModel = ViewModelProvider(this).get(GanheViewModel::class.java)

        swpatAbt.setOnRefreshListener { checkMainGanhe() }

        checkMainGanhe()

        return viewlayout
    }

    private fun checkMainGanhe() {
        ganheViewModel.funAbout()
        ganheViewModel.liveganheAbt.observe(viewLifecycleOwner, {
            if (it != null) {
                displayDataGet(it)

            } else {
                backupDataCheck()
            }
        })
    }

    private fun backupDataCheck() {
        ganheViewModel.funAboutBp()
        ganheViewModel.liveganheAbtBp.observe(viewLifecycleOwner, {
            if (it != null) {
                displayDataGet(it)

            } else {
                tvatAbt.visibility = View.VISIBLE
                pbatAbt.visibility = View.GONE
                rvatAbt.visibility = View.GONE
                swpatAbt.isRefreshing = false
            }
        })
    }

    private fun displayDataGet(it: List<ApiModel>) {
        val gdata = AboutAdapter()
        gdata.datasetAbt(it)

        rvatAbt.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = gdata
        }
        rvatAbt.visibility = View.VISIBLE
        pbatAbt.visibility = View.GONE
        tvatAbt.visibility = View.GONE
        swpatAbt.isRefreshing = false

    }
}