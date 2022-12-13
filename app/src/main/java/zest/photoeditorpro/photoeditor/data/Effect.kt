package zest.photoeditorpro.photoeditor.data

import android.content.res.Resources
import zest.photoeditorpro.photoeditor.R
import zest.photoeditorpro.photoeditor.util.Str


data class Effect(
    val id: Int,
    val name: String,
)

fun getEffectList(resources: Resources): List<Effect> {
    return listOf(
        Effect(
            id = Str.MODE_NONE,
            name = resources.getString(R.string.none)
        ),
        Effect(
            id = Str.HIGHPASS,
            name = resources.getString(R.string.highpass)
        ),
        Effect(
            id = Str.PASTELSKETCH,
            name = resources.getString(R.string.pastelsketch),
        ),
        Effect(
            id = Str.NEWSPRINT,
            name = resources.getString(R.string.newsprint),
        ),
        Effect(
            id = Str.OLDFILM,
            name = resources.getString(R.string.oldfilm),
        ),
        Effect(
            id = Str.MOTIONBLUR,
            name = resources.getString(R.string.motionblur),
        ),
        Effect(
            id = Str.PENCILSKETCH,
            name = resources.getString(R.string.pencilsketch),
        ),
        Effect(
            id = Str.HSV,
            name = resources.getString(R.string.hsv),
        )
    );
}
