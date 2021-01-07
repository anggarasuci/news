package co.anggarasuci.news.util.image

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import co.anggarasuci.news.R
import co.anggarasuci.news.util.getColorCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import java.io.File

class ImageUtilities {
    companion object {
        fun loadImageBitmap(view: ImageView, file: File) {
            Glide.with(view.context)
                .load(file)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .fitCenter()
                .into(view)
        }

        fun loadImageUrl(view: ImageView, url: String, defaultErrorImage: Int) {
            Glide.with(view.context)
                .load(url)
                .fitCenter()
                .error(defaultErrorImage)
                .into(view)
        }

        fun loadImageUrlWithProgress(view: ImageView, url: String) {
            val circularProgressDrawable = CircularProgressDrawable(view.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.setColorSchemeColors(view.context.getColorCompat(R.color.black))
            circularProgressDrawable.start()

            Glide.with(view.context)
                .load(url)
                .placeholder(circularProgressDrawable)
                .fitCenter()
                .error(R.drawable.ic_empty_icon)
                .into(view)
        }

        fun loadDrawableStretchRoundedCorner(
            view: ImageView,
            imageResource: String,
            radius: Int = 8
        ) {
            Glide.with(view.context)
                .load(if (imageResource.isNotBlank()) imageResource else R.drawable.ic_empty_icon)
                .apply(transformRoundedCorner(view.context, radius))
                .listener(generateListenerForClipToOutline(view))
                .error(R.drawable.ic_empty_icon)
                .into(view)
        }

        private fun transformRoundedCorner(context: Context, radius: Int): RequestOptions {
            return RequestOptions.bitmapTransform(
                RoundedCornersTransformation(
                    context, radius, 0,
                    RoundedCornersTransformation.CornerType.ALL
                )
            )
        }

        private fun generateListenerForClipToOutline(view: ImageView): RequestListener<Drawable> {
            return object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    println("Error Load Image to ImageView")
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    view.clipToOutline = true
                    return false
                }
            }
        }
    }
}
