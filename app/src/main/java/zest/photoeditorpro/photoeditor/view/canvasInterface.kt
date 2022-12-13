package zest.photoeditorpro.photoeditor.view

import android.graphics.Bitmap

interface canvasInterface {
    public fun returnMask():Bitmap
    public fun clearMask()

}