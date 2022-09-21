package dev.syoritohatsuki.bluemapadvancedmarker.dto

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val icons: Map<String, String> = mapOf(
        "black_banner" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/banner/black_banner.png?raw=true",
        "blue_arrow" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/arrow/blue_arrow.png?raw=true",
        "blue_banner" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/banner/blue_banner.png?raw=true",
        "brown_banner" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/banner/brown_banner.png?raw=true",
        "cyan_banner" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/banner/cyan_banner.png?raw=true",
        "gray_banner" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/banner/gray_banner.png?raw=true",
        "green_arrow" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/arrow/green_arrow.png?raw=true",
        "green_banner" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/banner/green_banner.png?raw=true",
        "large_white_dot" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/other/large_white_dot.png?raw=true",
        "light_blue_banner" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/banner/light_blue_banner.png?raw=true",
        "light_gray_banner" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/banner/light_gray_banner.png?raw=true",
        "light_green_banner" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/banner/light_green_banner.png?raw=true",
        "magenta_banner" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/banner/magenta_banner.png?raw=true",
        "mansion" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/location/mansion.png?raw=true",
        "monument" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/location/monument.png?raw=true",
        "orange_banner" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/banner/orange_banner.png?raw=true",
        "pink_banner" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/banner/pink_banner.png?raw=true",
        "purple_banner" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/banner/purple_banner.png?raw=true",
        "red_arrow" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/arrow/red_arrow.png?raw=true",
        "red_banner" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/banner/red_banner.png?raw=true",
        "red_cross" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/other/red_cross.png?raw=true",
        "red_triangle" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/other/red_triangle.png?raw=true",
        "small_white_dot" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/other/small_white_dot.png?raw=true",
        "white_arrow" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/arrow/white_arrow.png?raw=true",
        "white_banner" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/banner/white_banner.png?raw=true",
        "white_cross" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/other/white_cross.png?raw=true",
        "yellow_banner" to "https://github.com/syorito-hatsuki/bluemap-advanced-marker/blob/icons/icons/banner/yellow_banner.png?raw=true",
    )
)