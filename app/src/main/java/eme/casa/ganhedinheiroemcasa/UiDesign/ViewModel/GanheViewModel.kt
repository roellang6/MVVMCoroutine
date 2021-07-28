package eme.casa.ganhedinheiroemcasa.UiDesign.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eme.casa.ganhedinheiroemcasa.Data.Api.ApiDataServices
import eme.casa.ganhedinheiroemcasa.Data.Model.ApiModel
import kotlinx.coroutines.*

class GanheViewModel : ViewModel() {


    private val apidataServices = ApiDataServices()

    private var ganheMainlist = ArrayList<ApiModel>()

    private val ganheAbt = MutableLiveData<List<ApiModel>>()
    val liveganheAbt: LiveData<List<ApiModel>> get() = ganheAbt

    private val ganheEarn = MutableLiveData<List<ApiModel>>()
    val liveganheEarn: LiveData<List<ApiModel>> get() = ganheEarn

    private val ganheAdv = MutableLiveData<List<ApiModel>>()
    val liveganheAdv: LiveData<List<ApiModel>> get() = ganheAdv

    private val ganheDis = MutableLiveData<List<ApiModel>>()
    val liveganheDis: LiveData<List<ApiModel>> get() = ganheDis


    val exemA = CoroutineExceptionHandler { _, _ ->
        ganheAbt.postValue(null)
    }

    fun funAbout(): MutableLiveData<List<ApiModel>> {
        ganheMainlist.clear()
        viewModelScope.launch(exemA + Dispatchers.IO) {
            val response = apidataServices.mainGanhe().body()!!
            for (datam in response) {
                if (datam.status == "Ganhe dinheiro em casa") {
                    ganheMainlist.add(datam)
                }
                ganheAbt.postValue(ganheMainlist)
            }
        }
        return ganheAbt
    }

    val exemE = CoroutineExceptionHandler { _, _ ->
        ganheEarn.postValue(null)
    }

    fun funEarn(): MutableLiveData<List<ApiModel>> {
        ganheMainlist.clear()
        viewModelScope.launch(exemE + Dispatchers.IO) {
            val response = apidataServices.mainGanhe().body()!!
            for (datam in response) {
                if (datam.status == "make money Portuguese") {
                    ganheMainlist.add(datam)
                }
                ganheEarn.postValue(ganheMainlist)
            }
        }
        return ganheEarn
    }

    val exeemA = CoroutineExceptionHandler { _, _ ->
        ganheAdv.postValue(null)
    }

    fun funAdvance(): MutableLiveData<List<ApiModel>> {
        ganheMainlist.clear()
        viewModelScope.launch(exeemA + Dispatchers.IO) {
            val response = apidataServices.mainGanhe().body()!!
            for (datam in response) {
                if (datam.status == "Advantage Portuguese") {
                    ganheMainlist.add(datam)
                }
                ganheAdv.postValue(ganheMainlist)
            }
        }
        return ganheAdv
    }

    val exeemD = CoroutineExceptionHandler { _, _ ->
        ganheDis.postValue(null)
    }

    fun funDisadvance(): MutableLiveData<List<ApiModel>> {
        ganheMainlist.clear()
        viewModelScope.launch(exeemD + Dispatchers.IO) {
            val response = apidataServices.mainGanhe()
            for (datam in response.body()!!) {
                if (datam.status == "Disadvantage Portuguese") {
                    ganheMainlist.add(datam)
                }
                ganheDis.postValue(ganheMainlist)
            }
        }
        return ganheDis
    }


    private var ganheBackuplist = ArrayList<ApiModel>()

    private val ganheAbtBp = MutableLiveData<List<ApiModel>>()
    val liveganheAbtBp: LiveData<List<ApiModel>> get() = ganheAbtBp

    private val ganheEarnBp = MutableLiveData<List<ApiModel>>()
    val liveganheEarnBp: LiveData<List<ApiModel>> get() = ganheEarnBp

    private val ganheAdvBp = MutableLiveData<List<ApiModel>>()
    val liveganheAdvBp: LiveData<List<ApiModel>> get() = ganheAdvBp

    private val ganheDisBp = MutableLiveData<List<ApiModel>>()
    val liveganheDisBp: LiveData<List<ApiModel>> get() = ganheDisBp

    val exeAb = CoroutineExceptionHandler { _, _ ->
        ganheAbtBp.postValue(null)
    }

    fun funAboutBp(): MutableLiveData<List<ApiModel>> {
        ganheBackuplist.clear()
        viewModelScope.launch(exeAb + Dispatchers.IO) {
            val response = apidataServices.backupGanhe().body()!!
            for (datam in response) {
                if (datam.status == "Ganhe dinheiro em casa") {
                    ganheBackuplist.add(datam)
                }
                ganheAbtBp.postValue(ganheBackuplist)
            }
        }
        return ganheAbtBp
    }

    val exeEb = CoroutineExceptionHandler { _, _ ->
        ganheEarnBp.postValue(null)
    }

    fun funEarnBp(): MutableLiveData<List<ApiModel>> {
        ganheBackuplist.clear()
        viewModelScope.launch(exeEb + Dispatchers.IO) {
            val response = apidataServices.mainGanhe()
            for (datam in response.body()!!) {
                if (datam.status == "make money Portuguese") {
                    ganheBackuplist.add(datam)
                }
                ganheEarnBp.postValue(ganheBackuplist)
            }
        }
        return ganheEarnBp
    }

    val exeeAb = CoroutineExceptionHandler { _, _ ->
        ganheAdvBp.postValue(null)
    }

    fun funAdvanceBp(): MutableLiveData<List<ApiModel>> {
        ganheBackuplist.clear()
        viewModelScope.launch(exeeAb + Dispatchers.IO) {
            val response = apidataServices.mainGanhe()
            for (datam in response.body()!!) {
                if (datam.status == "Advantage Portuguese") {
                    ganheBackuplist.add(datam)
                }
                ganheAdvBp.postValue(ganheBackuplist)
            }
        }
        return ganheAdvBp
    }

    val exeeDb = CoroutineExceptionHandler { _, _ ->
        ganheDisBp.postValue(null)
    }
    fun funDisadvanceBp(): MutableLiveData<List<ApiModel>> {
        ganheBackuplist.clear()
        viewModelScope.launch(exeeDb + Dispatchers.IO) {
            val response = apidataServices.mainGanhe()
            for (datam in response.body()!!) {
                if (datam.status == "Disadvantage Portuguese") {
                    ganheBackuplist.add(datam)
                }
                ganheDisBp.postValue(ganheBackuplist)
            }
        }
        return ganheDisBp
    }

}