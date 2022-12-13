package zest.photoeditorpro.photoeditor.data

import android.content.res.Resources
import zest.photoeditorpro.photoeditor.R
import zest.photoeditorpro.photoeditor.util.Str


data class Crop(
    val id: Int,
    val name: String,
    val image: Int,
)

fun getCropList(resources: Resources): List<Crop> {
    return listOf(
        Crop(
            id = Str.CROP,
            name = resources.getString(R.string.crop),
            image = R.drawable.tool_crop_24
        ),
        Crop(
            id = Str.ROTATE,
            name = resources.getString(R.string.rotate),
            image = R.drawable.crop_rotate
        ),
        Crop(
            id = Str.TRANSFORM,
            name = resources.getString(R.string.transform),
            image = R.drawable.crop_transfom
        ),
        Crop(
            id = Str.HORIZONTAL,
            name = resources.getString(R.string.horizontal),
            image = R.drawable.crop_flip_lf
        ),
        Crop(
            id = Str.VERTICAL,
            name = resources.getString(R.string.vertical),
            image = R.drawable.crop_flip_tb
        )
    );
}
