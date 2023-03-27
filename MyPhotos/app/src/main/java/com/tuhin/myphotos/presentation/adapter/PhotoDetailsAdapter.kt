package com.tuhin.myphotos.presentation.adapter

import com.tuhin.myphotos.constant.RemoteConstant
import com.tuhin.myphotos.domain.model.PhotoDetails
import com.tuhin.myphotos.databinding.PhotoDetailsItemBinding

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

private const val TAG = "PhotoDetailsAdapter"

/**
 * This is a adapter class for photo details
 */
class PhotoDetailsAdapter(private val context: Context, photoDetailsList: List<PhotoDetails>) :
    RecyclerView.Adapter<PhotoDetailsAdapter.ViewHolder>() {

    private var photoDetailsList: List<PhotoDetails>? = photoDetailsList

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoDetailsAdapter.ViewHolder {
        val itemBinding =
            PhotoDetailsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    private fun setDataToView(holder: PhotoDetailsAdapter.ViewHolder, position: Int) {
        with(holder) {
            photoTitleText.text = photoDetailsList?.get(position)?.photoTitle
            albumNameText.text = photoDetailsList?.get(position)?.albumName
            userNameText.text = photoDetailsList?.get(position)?.userName
            imageProgress.setVisibility(View.VISIBLE)
        }
    }

    private fun downloadImageAndAttachToView(holder: PhotoDetailsAdapter.ViewHolder, position: Int) {
        val imageLink = "${photoDetailsList?.get(position)?.thumbnailUrl}"
        val photoUrl = GlideUrl(
            imageLink, LazyHeaders.Builder()
                .addHeader(RemoteConstant.IMAGE_HEADER_KEY, RemoteConstant.IMAGE_HEADER_VALUE)
                .build()
        )

        Glide.with(context)
            .load(photoUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d(TAG, "${e?.causes}")
                    holder.imageProgress.setVisibility(View.GONE)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.imageProgress.setVisibility(View.GONE)
                    return false
                }
            })
            .centerCrop()
            .into(holder.photoImageView)

    }

    override fun onBindViewHolder(holder: PhotoDetailsAdapter.ViewHolder, position: Int) {
        setDataToView(holder, position)
        downloadImageAndAttachToView(holder, position)
    }

    override fun getItemCount(): Int {
        return photoDetailsList?.size!!
    }

    override fun getItemId(position: Int): Long {
        return photoDetailsList?.get(position)?.hashCode()!!.toLong()
    }

    /**
     * Update adapter data
     * @param photoDetailsList list of photo details
     */
    fun setMovieList(photoDetailsList: List<PhotoDetails>) {
        this.photoDetailsList = photoDetailsList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemBinding: PhotoDetailsItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        val photoTitleText = itemBinding.photoTitleText
        val albumNameText = itemBinding.albumNameText
        val photoImageView = itemBinding.photoImageView
        val userNameText = itemBinding.userNameText
        val imageProgress = itemBinding.imageProgress
    }
}