package com.example.hospitalmanagementsystem.ui.doctor.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalmanagementsystem.ui.doctor.data.model.DataDoctorCalls
import com.example.hospitalmanagementsystem.ui.doctor.data.model.DataDoctorCases
import com.example.hospitalmanagementsystem.ui.doctor.data.model.DataOfCase
import com.example.hospitalmanagementsystem.ui.doctor.data.model.ModelDoctorCalls
import com.example.hospitalmanagementsystem.ui.doctor.data.model.ModelDoctorCases
import com.example.hospitalmanagementsystem.ui.doctor.data.model.ModelDoctorCasesDetails
import com.example.hospitalmanagementsystem.ui.doctor.data.model.ModelSuccess
import com.example.hospitalmanagementsystem.ui.doctor.domain.usecase.DoctorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject
@HiltViewModel
class DoctorViewModel @Inject constructor(private val doctorUseCase: DoctorUseCase) : ViewModel() {
    private var _doctorCalls = MutableLiveData<ModelDoctorCalls>()
    val doctorCalls get() = _doctorCalls

    private var _callSuccess = MutableLiveData<ModelSuccess>()
    val callSuccess get() = _callSuccess

    private var _doctorCases = MutableLiveData<List<DataDoctorCases>>()
    val doctorCases get() = _doctorCases

    private var _caseById = MutableLiveData<ModelDoctorCasesDetails>()
    val caseById get() = _caseById

    private val _analysisList = MutableLiveData<MutableList<String>>(mutableListOf())
    val analysisList: LiveData<MutableList<String>> get() = _analysisList

     fun getDoctorCalls(){
        try {
            viewModelScope.launch {
                val data=doctorUseCase.getDoctorCalls()

                withContext(Main){
                    doctorCalls.value=data
                }
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }
    fun getAcceptORRejectCall(id: Int, status: String) {
        viewModelScope.launch {
            try {
                val data = doctorUseCase.getAcceptORRejectCall(id, status)
                withContext(Main) {
                    callSuccess.value = data
                }
            } catch (e: HttpException) {
                if (e.code() == 404) {

                    Log.e("Error", "Resource not found")
                } else {

                    Log.e("Error", "HTTP error: ${e.message()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun getDoctorCases(){
        viewModelScope.launch {
            try {
                val data = doctorUseCase.getDoctorCases()
                withContext(Main){
                    doctorCases.value=data.data
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun getCaseById(id:Int){
        viewModelScope.launch {
            try {
                viewModelScope.launch {
                    val data=doctorUseCase.getCaseById(id)
                    withContext(Main){
                        caseById.value=data
                    }
                }
            }
            catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun addNurse(caseId:Int,nurseId:Int){
        viewModelScope.launch {
            try {
                val data=doctorUseCase.addNurse(caseId,nurseId)
                withContext(Main){
                    callSuccess.value=data
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun requestAnalysis(caseId:Int,doctorId:Int,note:String,types:List<String>){
        viewModelScope.launch {
            try {
                val data=doctorUseCase.requestAnalysis(caseId,doctorId,note,types)
                withContext(Main){
                    callSuccess.value=data
                }
        }catch (e:Exception){
            e.printStackTrace()


            }
        }
    }


    fun addItem(item: String) {
        _analysisList.value?.add(item)
        _analysisList.value = _analysisList.value // Trigger LiveData update
    }

    fun removeItem(item: String) {
        _analysisList.value?.remove(item)
        _analysisList.value = _analysisList.value // Trigger LiveData update
    }

    fun setList(list: List<String>) {
        _analysisList.value = list.toMutableList()
    }

}