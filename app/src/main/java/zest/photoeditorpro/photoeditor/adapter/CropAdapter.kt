package zest.photoeditorpro.photoeditor.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import zest.photoeditorpro.photoeditor.R
import zest.photoeditorpro.photoeditor.data.Crop

class CropAdapter(private val onCropSelected: OnCropSelected, val cropList: List<Crop>) :
    RecyclerView.Adapter<CropAdapter.ViewHolder>() {


    interface OnCropSelected {
        fun onCropSelected(crop: Crop)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val name: TextView = itemView.findViewById(R.id.name)

        init {
            itemView.setOnClickListener {
                onCropSelected.onCropSelected(
                    cropList[layoutPosition]
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_common, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return cropList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        val item = cropList[i]
        holder.image.setImageResource(item.image)
        holder.name.text = item.name
    }

}