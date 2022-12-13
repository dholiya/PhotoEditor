package zest.photoeditorpro.photoeditor.data

import android.content.res.Resources
import zest.photoeditorpro.photoeditor.R
import zest.photoeditorpro.photoeditor.util.Str


data class Adjust(
    val id: Int,
    val name: String,
    val image: Int,
)


fun getAdjustList(resources: Resources): List<Adjust> {
    return listOf(
        Adjust(
            id = Str.BRIGHTNESS,
            name = resources.getString(R.string.brightness),
            image = R.drawable.adjust_brightness
        ),
        Adjust(
            id = Str.CONTRAST,
            name = resources.getString(R.string.contrast),
            image = R.drawable.adjust_contrast
        ),
        Adjust(
            id = Str.VIGNETTE,
            name = resources.getString(R.string.vignette),
            image = R.drawable.adjust_vignette
        ),
        Adjust(
            id = Str.SATURATION,
            name = resources.getString(R.string.saturation),
            image = R.drawable.adjust_saturation
        ),
        Adjust(
            id = Str.TEMP,
            name = resources.getString(R.string.temp),
            image = R.drawable.adjust_temp
        )
    );
}
