package zest.photoeditorpro.photoeditor.data

import android.content.res.Resources
import zest.photoeditorpro.photoeditor.R
import zest.photoeditorpro.photoeditor.util.Str


data class Filters(
    val id: Int,
    val name: String
)

fun getFilterList(resources: Resources): List<Filters> {
    return listOf(
        Filters(
            id = Str.MODE_NONE,
            name = resources.getString(R.string.none)
        ),
        Filters(
            id = Str.GOLD,
            name = resources.getString(R.string.gold)
        ),
        Filters(
            id = Str.HDR,
            name = resources.getString(R.string.hdr),
        ),
        Filters(
            id = Str.BNW,
            name = resources.getString(R.string.bnw),
        )
    );
}
