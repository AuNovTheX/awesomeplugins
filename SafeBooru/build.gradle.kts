version = "1.0.1" // Plugin version. Increment this to trigger the updater
description = "safebooru" // Plugin description that will be shown to user

aliucord {
    // Changelog of your plugin
    changelog.set("""
        # Made pid random, this will make images random (in a tiny way)
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
