package om.ega.sunkey.userpfp

import android.content.Context
import com.aliucord.Logger
import com.aliucord.Utils
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin
import om.ega.sunkey.userpfp.model.AbstractDatabase
import om.ega.sunkey.userpfp.model.USRPFP
import kotlin.Throws

@AliucordPlugin
class UserPFP : Plugin() {
    init {
        USRPFP = om.ega.sunkey.userpfp.model.USRPFP
    }
    @Throws(NoSuchMethodException::class)
    override fun start(ctx: Context){ 
	USRPFP.init(ctx, settings, patcher)
    }

    override fun stop(ctx: Context) {
        patcher.unpatchAll()
    }

    companion object {
        lateinit var USRPFP: USRPFP

        val log: Logger = Logger("UserPFP")
        const val REFRESH_CACHE_TIME = (6 * 60).toLong()
    }
    
    init {
     settingsTab = SettingsTab(PluginSettings::class.java).withArgs(settings)
    }
}
