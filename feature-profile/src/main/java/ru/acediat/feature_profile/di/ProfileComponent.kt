package ru.acediat.feature_profile.di

import android.content.Context
import dagger.Component
import ru.acediat.core_android.di.AndroidModule
import ru.acediat.feature_profile.ProfileFragment
import ru.acediat.feature_profile.ProfileViewModel
import ru.acediat.feature_profile.ShopFragment
import ru.acediat.feature_profile.ShopViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ProfileModule::class,
    ShopModule::class,
])
interface ProfileComponent {

    fun inject(model : ProfileViewModel)
    fun inject(model: ShopViewModel)

    fun inject(profileFragment: ProfileFragment)
    fun inject(shopFragment: ShopFragment)

    companion object{

        @JvmStatic
        fun init(context: Context) : ProfileComponent = DaggerProfileComponent.builder()
            .androidModule(AndroidModule(context))
            .profileModule(ProfileModule())
            .shopModule(ShopModule())
            .build()

    }

}