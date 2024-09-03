package com.example.hospitalmanagementsystem.di

import com.example.hospitalmanagementsystem.utils.Const.BASE_URL
import com.example.hospitalmanagementsystem.utils.MySharedPreferences
import com.example.hospitalmanagementsystem.network.ApiCalls

import com.example.hospitalmanagementsystem.ui.hr.data.HrRepo
import com.example.hospitalmanagementsystem.ui.hr.domain.repo.IHrRepo
import com.example.hospitalmanagementsystem.ui.login.domain.repo.ILoginRepo
import com.example.hospitalmanagementsystem.ui.login.data.repo.LoginRepo

import com.example.hospitalmanagementsystem.ui.common.profile.domain.repo.IProfileRepo
import com.example.hospitalmanagementsystem.ui.common.profile.data.repo.ProfileRepo
import com.example.hospitalmanagementsystem.ui.common.reports.data.repo.IReportRepo
import com.example.hospitalmanagementsystem.ui.common.reports.domain.repo.ReportRepo
import com.example.hospitalmanagementsystem.ui.common.tasks.data.repo.ITasksRepo
import com.example.hospitalmanagementsystem.ui.common.tasks.domain.repo.TasksRepo
import com.example.hospitalmanagementsystem.ui.doctor.data.repo.IDoctorRepo
import com.example.hospitalmanagementsystem.ui.doctor.domain.repo.DoctorRepo

import com.example.hospitalmanagementsystem.ui.reception.data.repo.CallsRepo
import com.example.hospitalmanagementsystem.ui.reception.domain.repo.ICallsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyModel {


    @Singleton
    @Provides
    fun getRetrofit(): ApiCalls {
        val client = OkHttpClient.Builder()

            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(Interceptor { chain ->
                val originalRequest = chain.request()
                val originalUrl = originalRequest.url
                val url = originalUrl.newBuilder().build()
                val requestBuilder = originalRequest.newBuilder().url(url)
                    .addHeader("Accept", "application/json")
                    .addHeader("Authorization", "Bearer ${MySharedPreferences.getUserToken()}"
                    )
                val request = requestBuilder.build()
                val response = chain.proceed(request)
                response.code//status code
                response
            })
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(BASE_URL)
            .build().create(ApiCalls::class.java)
    }

    //Login
    @Provides
    @Singleton
    fun provideUserRepository(apiCalls: ApiCalls): ILoginRepo {
        return LoginRepo(apiCalls)
    }


    //profile
    @Provides
    @Singleton
    fun provideProfileRepository(apiCalls: ApiCalls): IProfileRepo {
        return ProfileRepo(apiCalls)
    }


    //reception
    @Provides
    @Singleton
    fun provideCallRepository(apiCalls: ApiCalls): ICallsRepo {
        return CallsRepo(apiCalls)
    }

    //hr
    @Provides
    @Singleton
    fun provideHrRepository(apiCalls: ApiCalls): IHrRepo {
        return HrRepo(apiCalls)
    }

    //doctor
    @Provides
    @Singleton
    fun provideDoctorRepository(apiCalls: ApiCalls): IDoctorRepo {
        return DoctorRepo(apiCalls)
    }
    //report
    @Provides
    @Singleton
    fun provideReportRepository(apiCalls: ApiCalls): IReportRepo {
        return ReportRepo(apiCalls)
    }

    //tasks
    @Provides
    @Singleton
    fun provideTasksRepository(apiCalls: ApiCalls): ITasksRepo {
        return TasksRepo(apiCalls)
    }
}

