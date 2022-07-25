package om.ega.sunkey.Booru

import android.content.Context
import android.view.*
import android.widget.*
import android.graphics.*

import com.aliucord.Utils
import com.aliucord.Logger
import com.aliucord.api.CommandsAPI
import com.aliucord.api.CommandsAPI.CommandResult
import com.aliucord.entities.CommandContext
import com.aliucord.Http

import com.aliucord.fragments.SettingsPage
import com.discord.app.AppFragment
import android.text.*

import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin
import com.aliucord.patcher.*

import java.util.regex.Pattern

import com.discord.api.commands.ApplicationCommandType

@AliucordPlugin(requiresRestart = false)
class Booru : Plugin() {
    override fun start(context: Context) {
        commands.registerCommand("Booru", "Search images in a booru", commandoptions) {
		var keyw: String = it.getString("tag")!!;
		Utils.openPageWithProxy(context, BooruPage(keyw));
 
		return@registerCommand CommandResult(null)
	}
   }

val commandoptions = listOf(
	Utils.createCommandOption(
		ApplicationCommandType.STRING, "tag", "PLEASE insert the tag you want to search.", null,
		required = true,
		default = true,
		channelTypes = emptyList(),
		choices = emptyList(),
		subCommandOptions = emptyList(),
		autocomplete = false
	)
)

    override fun stop(context: Context) {
        commands.unregisterAll()
    }
}

class BooruPage(tag: String?) : SettingsPage() {
	override fun onViewBound(view: View) {
		super.onViewBound(view)
		setActionBarTitle("Booru Browser")
		
		val context = view.getContext()

		val search = Http.simpleGet("https://safebooru.org/index.php?page=dapi&s=post&q=index&limit=20&pid=1&tags=${tag}")
		
		var image = ImageView(context)
		image.setMaxHeight(500)
		image.setMaxWidth(720)

		val result = search.toString()
		val matcher = Pattern.compile("file_url=\"(https:\\/\\/[\\w.\\/-]*)\"").matcher(result)
		while (matcher.find()) {
			matcher.group(1)?.let { res -> image.setImageUri(res); addView(image) }
		}
		
		//val real = end.toString().replace("[", "").replace("]", "").replace(",", "")
	}
}
