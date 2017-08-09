package com.mo2o.template.infrastructure.ui.overview


import android.util.Log
import com.mo2o.template.*
import com.mo2o.template.infrastructure.api.TemplateService
import com.mo2o.template.infrastructure.api.model.User
import com.mo2o.template.infrastructure.extension.loadCircle
import com.mo2o.template.infrastructure.extension.setToolbar
import com.mo2o.template.infrastructure.ui.common.BaseFragment
import dagger.android.support.AndroidSupportInjection
import kategory.Either
import kotlinx.android.synthetic.main.fragment_overview.*
import retrofit2.Response
import javax.inject.Inject

class OverviewFragment : BaseFragment() {
    @Inject lateinit var template: TemplateService

    override fun getLayout() = R.layout.fragment_overview

    override fun onCreate() {
        setToolbar(R.string.main)
        AndroidSupportInjection.inject(this)

        Future {
            try {
                Either.Right(template.getUser().execute())
            } catch (e: Exception) {
                Either.Left(GenericError.ServerError)
            }
        }.onComplete {
            when (it) {
                is Either.Right -> onSuccess(it.b)
                is Either.Left -> onError()
            }
        }
    }

    private fun onError() = Log.e("Error", "not success")

    private fun onSuccess(response: Response<User>) =
            response.isSuccessful
                    .apply { show(response.body()!!) }
                    .also { Log.e("Error", "not success") }

    fun show(user: User) = with(user) {
        ivAvatar.loadCircle(user.avatarUrl)
        tvEmail.text = email
        tvFullName.text = name
        tvAlias.text = login
    }

}