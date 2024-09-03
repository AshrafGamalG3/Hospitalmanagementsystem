package com.example.hospitalmanagementsystem.network

import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ModelAllReports
import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ModelCreation
import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ModelShowReport
import com.example.hospitalmanagementsystem.ui.common.tasks.data.model.ModelAllTasks
import com.example.hospitalmanagementsystem.ui.common.tasks.data.model.ModelTaskDetails
import com.example.hospitalmanagementsystem.ui.reception.data.model.AllCalls
import com.example.hospitalmanagementsystem.ui.doctor.data.model.ModelDoctorCalls
import com.example.hospitalmanagementsystem.ui.doctor.data.model.ModelDoctorCases
import com.example.hospitalmanagementsystem.ui.doctor.data.model.ModelDoctorCasesDetails
import com.example.hospitalmanagementsystem.ui.doctor.data.model.ModelSuccess


import com.example.hospitalmanagementsystem.ui.reception.data.model.CreationCall
import com.example.hospitalmanagementsystem.ui.login.data.model.ModelUser
import com.example.hospitalmanagementsystem.ui.reception.data.model.AllUsers


import com.magdy.hospitalsystem.data.ModelShowCall
import retrofit2.http.DELETE

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiCalls {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("device_token") deviceToken: String
    ): ModelUser

    @FormUrlEncoded
    @POST("show-profile")
    suspend fun getProfileData(@Field("user_id") userId: Int): ModelUser


    @GET("calls")
    suspend fun getAllCalls(@Query("date") date: String): AllCalls


    @GET("doctors")
    suspend fun getAllByType(
        @Query("type") type: String
    ): AllUsers


    @FormUrlEncoded
    @POST("calls")
    suspend fun createCallReception(
        @Field("patient_name") name: String,
        @Field("age") age: String,
        @Field("doctor_id") doctorId: Int,
        @Field("phone") phone: String,
        @Field("description") description: String
    ): CreationCall


    @FormUrlEncoded
    @POST("register")
    suspend  fun registerUser(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("first_name") fName: String,
        @Field("last_name") lName: String,
        @Field("gender") gender: String,
        @Field("specialist") specialist: String,
        @Field("birthday") birthday: String,
        @Field("status") status: String,
        @Field("address") address: String,
        @Field("mobile") mobile: String,
        @Field("type") type: String
    ): ModelUser


    @GET("calls/{id}")
    suspend fun getCallById(@Path("id") id: Int): ModelShowCall


    @GET("calls")
    suspend fun getDoctorCalls(): ModelDoctorCalls

    @PUT("calls-accept/{id}")
    suspend fun getAcceptORRejectCall(
        @Path("id") id: Int,
        @Query("status") status: String
    ): ModelSuccess

    @GET("case")
    suspend fun getDoctorCases(): ModelDoctorCases

    @GET("case/{id}")
    suspend fun getCaseById(@Path("id") id: Int):ModelDoctorCasesDetails

    @POST("add-nurse")
    suspend fun addNurse(@Query("call_id") caseId: Int, @Query("user_id") doctorId: Int):ModelSuccess

    @FormUrlEncoded
    @POST("make-request")
    suspend fun requestAnalysis (@Field("call_id") callId : Int
                                 ,@Field("user_id") userId : Int
                                 ,@Field("note") note : String
                                 ,@Field("types[]") types : List<String> ) : ModelSuccess


    @GET("reports")
    suspend fun getAllReports(@Query("date") date: String): ModelAllReports

    @DELETE("reports/{id}")
    suspend fun endReport(@Path("id") id: Int): ModelCreation

    @GET("reports/{id}")
    suspend fun showReport (@Path("id") id : Int ) : ModelShowReport

    @FormUrlEncoded
    @PUT("reports/{id}")
    suspend fun answerReport (@Path("id") id : Int
                              , @Field("answer") answer :String) : ModelCreation

    @FormUrlEncoded
    @POST("reports")
    suspend fun createReport(
        @Field("report_name") reportName: String, @Field("description") description: String
    ): ModelCreation


    @GET("tasks")
    suspend fun showAllTasks(@Query("date") date: String): ModelAllTasks

    @FormUrlEncoded
    @POST("tasks")
    suspend fun createTasks (@Field("user_id") userId : Int
                             ,@Field("task_name") taskName  :String
                             ,@Field("description") description :String
                             ,@Field("todos[]") todoList : List<String> ) : ModelCreation
    @FormUrlEncoded
    @PUT("tasks/{id}")
    suspend fun execution(
        @Path("id") id: Int, @Field("note") note: String
    ): ModelCreation

    @GET("tasks/{id}")
    suspend fun showTask(@Path("id") id: Int): ModelTaskDetails

    @FormUrlEncoded
    @POST("attendance")
    suspend fun attendance(@Field("status") status: String): ModelCreation

    @FormUrlEncoded
    @POST("measurement")
    suspend fun uploadMeasurement (@Field("call_id") caseId : Int
                                   ,@Field("blood_pressure") bloodPressure :String
                                   ,@Field("sugar_analysis") sugarAnalysis :String
                                   ,@Field("tempreture") tempreture :String
                                   ,@Field("fluid_balance") fluidBalance :String
                                   ,@Field("respiratory_rate") respiratoryRate :String
                                   ,@Field("heart_rate") heartRate :String
                                   ,@Field("note") not :String
                                   ,@Field("status") status : String ) : ModelCreation

    @FormUrlEncoded
    @POST("send-notification")
    suspend fun sendNotification (@Field("to") userId  : Int
                                  ,@Field("title") title : String
                                  ,@Field("body") body : String) : ModelCreation



}
