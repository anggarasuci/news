package co.anggarasuci.news.util.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapResource
import java.security.MessageDigest

class RoundedCornersTransformation @JvmOverloads constructor(
    private val mBitmapPool: BitmapPool,
    private val mRadius: Int,
    private val mMargin: Int,
    private val mCornerType: CornerType = CornerType.ALL
) : Transformation<Bitmap> {

    enum class CornerType { ALL }

    @JvmOverloads
    constructor(
        context: Context,
        radius: Int,
        margin: Int,
        cornerType: CornerType = CornerType.ALL
    ) : this(Glide.get(context).bitmapPool, radius, margin, cornerType)

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        // no action
    }

    override fun transform(
        context: Context,
        resource: Resource<Bitmap>,
        outWidth: Int,
        outHeight: Int
    ): BitmapResource {
        val source = resource.get()

        val width = source.width
        val height = source.height

        var bitmap: Bitmap? = mBitmapPool.get(width, height, Bitmap.Config.ARGB_8888)
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        }

        bitmap?.let {
            val canvas = Canvas(it)
            val paint = Paint()
            paint.isAntiAlias = true
            paint.shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            drawRoundRect(canvas, paint, width.toFloat(), height.toFloat())
        }

        val defBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        return BitmapResource.obtain(bitmap, mBitmapPool) ?: BitmapResource(defBitmap, mBitmapPool)
    }

    private fun drawRoundRect(canvas: Canvas, paint: Paint, width: Float, height: Float) {
        val right = width - mMargin
        val bottom = height - mMargin

        canvas.drawRoundRect(
            RectF(mMargin.toFloat(), mMargin.toFloat(), right, bottom),
            mRadius.toFloat(), mRadius.toFloat(), paint
        )
    }
}
