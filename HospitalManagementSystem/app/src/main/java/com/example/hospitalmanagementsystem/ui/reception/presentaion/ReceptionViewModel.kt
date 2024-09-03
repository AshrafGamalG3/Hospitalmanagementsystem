package com.example.hospitalmanagementsystem.ui.reception.presentaion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalmanagementsystem.ui.reception.data.model.CreationCall



import com.example.hospitalmanagementsystem.ui.reception.data.mapper.CallsMapperImpl
import com.example.hospitalmanagementsystem.ui.reception.data.model.AllUserData

import com.example.hospitalmanagementsystem.ui.reception.domain.model.ModelCalls
import com.example.hospitalmanagementsystem.ui.reception.domain.usecase.CallsUseCase
import com.magdy.hospitalsystem.data.CallData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ReceptionViewModel @Inject constructor(
    private val callsUseCase: CallsUseCase,
    private val callsMapper : CallsMapperImpl

) :ViewModel() {
    private var _mutableLiveData = MutableLiveData<List<ModelCalls>>()
    val mutableLiveData get() = _mutableLiveData

    private var _createCallLiveData=MutableLiveData<CreationCall>()
    val creationCall get() = _createCallLiveData

    private var _selectDoctor=MutableLiveData<List<AllUserData>>()
    val selectDoctorData get() = _selectDoctor

    private var _callData=MutableLiveData<CallData>()
    val callData get() = _callData

    fun getAllCalls(date:String){
        try {
            viewModelScope.launch(IO){
                val data=callsUseCase(date)
                val calls=callsMapper.getAllCalls(data)
                withContext(Main){
        mutableLiveData.value=calls
                }
            }

        }catch (e:Exception){
        throw  e
        }
    }


    fun createCall(name: String,age: String,doctorId: Int,phone: String,description: String){
        try {
            viewModelScope.launch(IO){
                val data =callsUseCase(name, age, doctorId, phone, description)
                withContext(Main){
                    creationCall.value=data
                }
            }

        }catch (e:Exception){
            throw  e
        }
    }

    fun getAllDoctors(type:String){
        try {
            viewModelScope.launch(IO){
                val data =callsUseCase(type,0)
                withContext(Main){
                    selectDoctorData.value=data.data
                }
            }

        }catch (e:Exception){
            throw  e
        }
    }

    fun getCallById(id:Int){
        try {
            viewModelScope.launch(IO) {
                val data=callsUseCase(id)
                withContext(Main){
                    callData.value=data
                }
            }
        }
        catch (e:Exception){
        throw e
        }
    }
}