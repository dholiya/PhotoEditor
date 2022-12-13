package zest.photoeditorpro.photoeditor.data//package zest.photoeditorpro.photoeditor.data.tools

import android.content.res.Resources
import zest.photoeditorpro.photoeditor.R
import zest.photoeditorpro.photoeditor.util.Str


data class Tools(
    var id: Int,
    val name: String,
    val image: Int,
)

fun getToolList(resources: Resources): List<Tools> {
    return listOf(
        Tools(
            id = Str.CROP,
            name = resources.getString(R.string.crop),
            image = R.drawable.tool_crop_24
        ),
        Tools(
            id = Str.REMOVE,
            name = resources.getString(R.string.remove),
            image = R.drawable.remove_erase
        ),
        Tools(
            id = Str.ENHANCE,
            name = resources.getString(R.string.enhance),
            image = R.drawable.tool_enhance
        ),
        Tools(
            id = Str.FILTER,
            name = resources.getString(R.string.filter),
            image = R.drawable.tool_filter
        ),
        Tools(
            id = Str.ADJUST,
            name = resources.getString(R.string.adjust),
            image = R.drawable.filter_slider
        ),
        Tools(
            id = Str.EFFECT,
            name = resources.getString(R.string.effect),
            image = R.drawable.tool_effect
        ),
        Tools(
            id = Str.BEAUTIFY,
            name = resources.getString(R.string.beautify),
            image = R.drawable.tool_beautify
        )
    );
}
