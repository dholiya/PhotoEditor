package zest.photoeditorpro.photoeditor.adapter

import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import zest.photoeditorpro.photoeditor.R
import zest.photoeditorpro.photoeditor.data.Effect
import zest.photoeditorpro.photoeditor.util.Str

class EffectAdapter(
    private val onEffectSelected: OnEffectSelected,
    var context: Context,
    val effectList: List<Effect>,
) :
    RecyclerView.Adapter<EffectAdapter.ViewHolder>() {


    interface OnEffectSelected {
        fun onEffectSelected(
            effects: Effect,
            effectCV: MaterialCardView,
            icon: ImageView,
            image: ImageView,
            layoutPosition: Int,
        )
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val effectCV: MaterialCardView = itemView.findViewById(R.id.filerCV)
        val effectImgNone: ImageView = itemView.findViewById(R.id.filterImgNone)
        val image: ImageView = itemView.findViewById(R.id.filterImg)
        val icon: ImageView = itemView.findViewById(R.id.filerIcon)
        val name: TextView = itemView.findViewById(R.id.filterName)

        init {
            itemView.setOnClickListener {
                onEffectSelected.onEffectSelected(
                    effectList[layoutPosition], effectCV, icon, image, layoutPosition
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_filter, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return effectList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        val item = effectList[i]
        holder.name.text = item.name
        holder.effectCV.strokeWidth = 0
        holder.icon.visibility = View.GONE
        holder.image.setColorFilter(ContextCompat.getColor(context, R.color.trans))
        holder.effectImgNone.visibility = View.GONE


        if(item.id == Str.MODE_NONE){
            holder.effectImgNone.visibility = View.VISIBLE
            holder.image.setColorFilter(ContextCompat.getColor(context, R.color.trans), PorterDuff.Mode.SRC_IN)
        }else{
            holder.image.setColorFilter(ContextCompat.getColor(context, R.color.trans), PorterDuff.Mode.SRC_ATOP)
        }

    }

}