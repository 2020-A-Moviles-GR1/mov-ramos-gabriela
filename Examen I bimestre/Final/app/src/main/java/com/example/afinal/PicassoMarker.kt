package com.example.afinal

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.LoadedFrom
import com.squareup.picasso.Target
import java.lang.Exception


/**
 * Picasso target - load image into marker (Google map icon on map)
 * Created by Tomas Krabac[tomas.krabac@ackee.cz] on {16. 7. 2015}
 */
class PicassoMarker internal constructor(var mMarker: Marker) : com.squareup.picasso.Target {
    override fun hashCode(): Int {
        return mMarker.hashCode()
    }

    override fun equals(o: Any?): Boolean {
        return if (o is PicassoMarker) {
            val marker = o.mMarker
            mMarker == marker
        } else {
            false
        }
    }

    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
        mMarker.setIcon(BitmapDescriptorFactory.fromBitmap(bitmap))
    }

   // override fun onBitmapFailed(errorDrawable: Drawable?) {}
    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
        TODO("Not yet implemented")
    }

}
