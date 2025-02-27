package orlo.example.googlecastvktest

import android.content.Context
import com.google.android.gms.cast.framework.CastOptions
import com.google.android.gms.cast.framework.OptionsProvider
import com.google.android.gms.cast.framework.SessionProvider
import com.google.android.gms.cast.framework.media.CastMediaOptions

    class CastOptionsProvider : OptionsProvider {
        override fun getCastOptions(context: Context): CastOptions {
            val receiverAppId = "CC1AD845"
            val mediaOptions = CastMediaOptions.Builder().build()

            return CastOptions.Builder()
                .setReceiverApplicationId(receiverAppId)
                .setCastMediaOptions(mediaOptions)
                .build()
        }

        override fun getAdditionalSessionProviders(context: Context): List<SessionProvider>? {
            return null
        }
    }
