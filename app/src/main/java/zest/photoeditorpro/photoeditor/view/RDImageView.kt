package zest.photoeditorpro.photoeditor.view


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.util.SparseArray
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView


class RDImageView : AppCompatImageView, canvasInterface {

    private val initialTapeline: Tapeline = Tapeline()
    private val transformTapeline: Tapeline = Tapeline()
    private val initialPoint = SparseArray<PointF>()
    var lastPointer = 0
    var lastAction = 0
    private var time = System.currentTimeMillis()
    private val ZOOM_TIME_DIFF_THRESHOLD = 400


    var scaleF = 0f
    var px = 0f
    var py = 0f

    var dx = 0f
    var dy = 0f

    //////////////
    var mpaint = Paint()
    var clearCanvas = false
    var downx = 0f
    var downy = 0f
    var upx = 0f
    var upy = 0f
    var minWidth = 0f

    companion object {
        lateinit var mbitmap: Bitmap
        lateinit var mcanvas: Canvas


        var fit_height = 0
        var fit_width = 0

        private fun fitTranslate(
            matrix: Matrix,
            rect: RectF,
            frame: RectF
        ): Boolean {
            val dstRect = RectF()
            matrix.mapRect(dstRect, rect)
            val x = dstRect.left
            val y = dstRect.top
            val w = dstRect.width()
            val h = dstRect.height()
            val fw = frame.width()
            val fh = frame.height()
            val minX = frame.right - w
            val minY = frame.bottom - h
            val dx = if (w > fw) Math.max(
                minX - x,
                Math.min(frame.left - x, 0f)
            ) else (frame.left + Math.round((fw - w) * .5f)) - x
            val dy = if (h > fh) Math.max(
                minY - y,
                Math.min(frame.top - y, 0f)
            ) else (frame.top + Math.round((fh - h) * .5f)) - y
            if (dx != 0f || dy != 0f) {
                matrix.postTranslate(dx, dy)
                return true
            }
            return false
        }
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initCon()
    }

    constructor(context: Context) : super(context) {
        initCon()
    }

    fun initCon() {

        mpaint.setARGB(200, 255, 99, 99)
        mpaint.strokeWidth = 20f
        setFocusable(true);

        mpaint.alpha = 200
        mpaint.strokeCap = Paint.Cap.ROUND
        mpaint.strokeJoin = Paint.Join.ROUND
        this.setLayerType(LAYER_TYPE_SOFTWARE, null)

    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        fit_height = h
        fit_width = w

        mbitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        mcanvas = Canvas()

        mcanvas.setBitmap(mbitmap)
        mcanvas.drawColor(Color.TRANSPARENT)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.save();

        if (clearCanvas) {
            mcanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            clearCanvas = false;
        }


        canvas.drawBitmap(mbitmap, 0f, 0f, null)
//        mcanvas.drawLine(downx, downy, upx, upy, mpaint)
        canvas.restore();

    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event!!.pointerCount == 1) {

            val i = event.actionIndex
            val point = initialPoint[event.getPointerId(i)]
            if (point != null) {
                dx = event.getX(i) - point.x;
                dy = event.getY(i) - point.y
            }
            Log.i("time", "${System.currentTimeMillis() - time}")

            if (lastAction != MotionEvent.ACTION_POINTER_UP) {
                drawLine(event)
            } else if (lastAction == MotionEvent.ACTION_POINTER_UP && System.currentTimeMillis() - time > ZOOM_TIME_DIFF_THRESHOLD) {
                drawLine(event)
                lastAction = event.action
            }

        } else {
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                    initTransform(event, -1)
                    Log.i("data", "M ACTION_DOWN")
                    return true
                }
                MotionEvent.ACTION_MOVE -> {
                    transform(event)
                    Log.i("data", "M ACTION_Move")
                    return true
                }
                MotionEvent.ACTION_POINTER_UP -> {
                    initTransform(event, event.actionIndex)
                    Log.i("data", "M ACTION_POINTER_UP")
                    lastAction = MotionEvent.ACTION_POINTER_UP
                    time = System.currentTimeMillis()
                    return true
                }
                MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> return true
            }

        }

        return true
    }

    private fun drawLine(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                downx = getPointerCoords(event)[0]
                downy = getPointerCoords(event)[1]
                Log.i("data 1", "ACTION_DOWN")
            }
            MotionEvent.ACTION_MOVE -> {
                upx = getPointerCoords(event)[0]
                upy = getPointerCoords(event)[1]
                mcanvas.drawLine(downx, downy, upx, upy, mpaint)
                invalidate()
                downx = upx
                downy = upy
                Log.i("data 1", "ACTION_MOVE")

            }
            MotionEvent.ACTION_UP -> {
                upx = getPointerCoords(event)[0]
                upy = getPointerCoords(event)[1]
                mcanvas.drawLine(downx, downy, upx, upy, mpaint)
                invalidate()
                Log.i("data 1", "ACTION_UP")
            }
        }

    }

    class Tapeline() {
        public var length = 0f
        public var pivotX = 0f
        public var pivotY = 0f
        public operator fun set(event: MotionEvent, p1: Int, p2: Int) {
            val x1 = event.getX(p1)
            val y1 = event.getY(p1)
            val x2 = event.getX(p2)
            val y2 = event.getY(p2)
            val dx = x2 - x1
            val dy = y2 - y1
            length = Math.hypot(dx.toDouble(), dy.toDouble()).toFloat()
            pivotX = (x1 + x2) * .5f
            pivotY = (y1 + y2) * .5f

        }
    }


    private fun initTransform(event: MotionEvent, ignoreIndex: Int) {
        var p1 = 0xffff
        var p2 = 0xffff
        var i = 0
        val l = event.pointerCount
        lastPointer = l;
        while (i < l) {
            initialPoint.put(
                event.getPointerId(i), PointF(
                    event.getX(i),
                    event.getY(i)
                )
            )
            if (i == ignoreIndex) {
                ++i
                continue
            } else if (p1 == 0xffff) {
                p1 = i
            } else {
                p2 = i
                break
            }
            ++i
        }
        if (p2 != 0xffff) {
            initialTapeline.set(event, p1, p2)
        }
    }


    private fun transform(event: MotionEvent) {

        val pointerCount = event.pointerCount
        lastPointer = pointerCount;

        if (pointerCount == 1) {
            val i = event.actionIndex
            val point = initialPoint[event.getPointerId(i)]
            if (point != null) {
                px = event.getX(i) - point.x;
                py = event.getY(i) - point.y
            }
        } else if (pointerCount > 1) {
            transformTapeline.set(event, 0, 1)
            val scale = transformTapeline.length / initialTapeline.length;

            scaleF *= scale;
            scaleF = Math.max(1.0F, Math.min(scaleF, 4.0F))

            px = initialTapeline.pivotX
            py = initialTapeline.pivotY


            dx = transformTapeline.pivotX - initialTapeline.pivotX
            dy = transformTapeline.pivotY - initialTapeline.pivotY

            val maxDx: Float = (getWidth() - getWidth() / scaleF) / 2 * scaleF
            val maxDy: Float = (getHeight() - getHeight() / scaleF) / 2 * scaleF
            dx = Math.min(Math.max(dx, -maxDx), maxDx)
            dy = Math.min(Math.max(dy, -maxDy), maxDy)

        }


        setScaleX(scaleF)
        setScaleY(scaleF)

        setTranslationX(dx)
        setTranslationY(dy)

        setPivotX(px)
        setPivotY(py)
    }

    private fun fitScale(
        matrix: Matrix,
        rect: RectF,
        scale: Float
    ): Float {
        val dstRect = RectF()
        matrix.mapRect(dstRect, rect)
        val w = dstRect.width()
        return if (w * scale < minWidth) minWidth / w else scale
    }

    fun getPointerCoords(e: MotionEvent): FloatArray {
        val index = e.actionIndex
        val coords = floatArrayOf(e.getX(index), e.getY(index))
        val matrix = Matrix()
        matrix.postTranslate(scrollX.toFloat(), scrollY.toFloat())
        matrix.mapPoints(coords)
        return coords
    }

    override fun returnMask(): Bitmap {
        return mbitmap;
    }

    override fun clearMask() {
        invalidate()
        clearCanvas = true
    }


}

