package om.ega.sunkey.Booru

import android.content.Context
import android.view.*

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
        commands.registerCommand("SafeBooru", "Search images in safebooru", commandoptions) {
		var keyw = it.getString("tag") 

		//var number = it.getString("number")?.toInt()
		//val LOG: Logger = Logger("FC")
		//var limit = it.getString("limit")!!.toInt()
		//if (limit > 5) limit = 5 //hardcode limit because discord embedding limits it to 5 lolll
		//val search = Http.simpleGet("https://safebooru.org/index.php?page=dapi&s=post&q=index&limit=${limit}&pid=${number}&tags=${keyw}")
		
		
		//var end = arrayListOf<String>()
		
		//val result = search.toString()
		//val matcher = Pattern.compile("file_url=\"(https:\\/\\/[\\w.\\/-]*)\"").matcher(result)
		/* while (matcher.find()) { 
		matcher.group(1)?.let { res -> end.add(res) }
		} */
		//val real = end.toString().replace("[", "").replace("]", "").replace(",", "") 
		//please dont ask about this horror code 
		return@registerCommand CommandResult(Utils.openPageWithProxy(context, BooruPage(keyw)))
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
	)/* ,
	Utils.createCommandOption(
		ApplicationCommandType.STRING, "number", "Insert the page you want to send. (changing pages will show different results).", null,
		required = true,
		default = false,
		channelTypes = emptyList(),
		choices = emptyList(),
		subCommandOptions = emptyList(),
		autocomplete = false
	),
	Utils.createCommandOption(
		ApplicationCommandType.STRING, "limit", "Insert the limit of images you want to send. The limit is hardcoded to 5 because discord embedding limits it to 5.", null,
		required = true,
		default = false,
		channelTypes = emptyList(),
		choices = emptyList(),
		subCommandOptions = emptyList(),
		autocomplete = false
	)*/
)

	/* fun booru(keywt: String) {
		val search = Http.simpleGet("https://safebooru.org/index.php?page=dapi&s=post&q=index&limit=1&tags=${keywt}")
		//LOG.debug(search)
		val r = search.toString()
	} */

    override fun stop(context: Context) {
        commands.unregisterAll()
    }
}

class BooruPage(tag: String) : SettingsPage() {
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
