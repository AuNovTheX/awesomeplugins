version = "1.1.0" // Plugin version. Increment this to trigger the updater
description = "Search in SafeBooru." // Plugin description that will be shown to user

aliucord {
    // Changelog of your plugin
    changelog.set("""
        # 1.0.1
        • Made pid random, this will make images random (in a tiny way) | Hice el pid random, esto hace las imagenes random (en una pequeña forma)
        # 1.0.2
        • So i've discovered that making limit=1 and changing pages actually changes posts. So now you can choose the "page" for searching and sending different posts instead of making it random. It is required to set a number. | Descubrí que hacer limit=1 y cambiar de paginas en realidad cambiaba de imagenes. Ahora puedes elegir la "pagina" para buscar y enviar diferentes imagenes en vez de ser random. Un numero es requerido.
        # 1.1.0 
        • Now you can set the amount of images you wanna send. Please note that doing limit=999 will do nothing, the site has a hardcode limit of 100. | Ahora puedes elegir la cantidad de imágenes que quieres enviar. Por favor recuerda que hacer limit=999 hará nada, el sitio tiene un limite forzado de 100. 
    """.trimIndent())
    // Image or Gif that will be shown at the top of your changelog page
    //changelogMedia.set("https://cdn.discordapp.com/attachments/929565544334647356/957419019500146708/Screenshot_20220326-182112113.jpg")

    // Add additional authors to this plugin
    // author("Name", 0)
    // author("Name", 0)

    // Excludes this plugin from the updater, meaning it won't show up for users.
    // Set this if the plugin is unfinished
    excludeFromUpdaterJson.set(false)
}
