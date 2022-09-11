package dev.syoritohatsuki.bluemapadvancedmarker.config

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val icons: Map<String, String> = mapOf(
        "black_banner" to "https://github.com/url-to-icon-here",
        "blue_arrow" to "https://github.com/url-to-icon-here",
        "blue_banner" to "https://github.com/url-to-icon-here",
        "brown_banner" to "https://github.com/url-to-icon-here",
        "cyan_banner" to "https://github.com/url-to-icon-here",
        "gray_banner" to "https://github.com/url-to-icon-here",
        "green_arrow" to "https://github.com/url-to-icon-here",
        "green_banner" to "https://github.com/url-to-icon-here",
        "large_white_dot" to "https://github.com/url-to-icon-here",
        "light_blue_banner" to "https://github.com/url-to-icon-here",
        "light_gray_banner" to "https://github.com/url-to-icon-here",
        "light_green_banner" to "https://github.com/url-to-icon-here",
        "magenta_banner" to "https://github.com/url-to-icon-here",
        "mansion" to "https://github.com/url-to-icon-here",
        "monument" to "https://github.com/url-to-icon-here",
        "orange_banner" to "https://github.com/url-to-icon-here",
        "pink_banner" to "https://github.com/url-to-icon-here",
        "purple_banner" to "https://github.com/url-to-icon-here",
        "red_arrow" to "https://github.com/url-to-icon-here",
        "red_banner" to "https://github.com/url-to-icon-here",
        "red_cross" to "https://github.com/url-to-icon-here",
        "red_triangle" to "https://github.com/url-to-icon-here",
        "small_white_Dot" to "https://github.com/url-to-icon-here",
        "white_arrow" to "https://github.com/url-to-icon-here",
        "white_banner" to "https://github.com/url-to-icon-here",
        "white_cross" to "https://github.com/url-to-icon-here",
        "yellow_banner" to "https://github.com/url-to-icon-here",
    )
)