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
import zest.photoeditorpro.photoeditor.data.Filters
import zest.photoeditorpro.photoeditor.fragment.FilterFragment
import zest.photoeditorpro.photoeditor.util.Str

class FilterAdapter(
    private val onFilterSelected: OnFilterSelected,
    var context: Context,
    val filterList: List<Filters>
) :
    RecyclerView.Adapter<FilterAdapter.ViewHolder>() {


    interface OnFilterSelected {
        fun onFilterSelected(filters: Filters, filterCV: MaterialCardView, icon:ImageView,image:ImageView, layoutPosition: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val filterCV: MaterialCardView = itemView.findViewById(R.id.filerCV)
        val image: ImageView = itemView.findViewById(R.id.filterImg)
        val filterImgNone: ImageView = itemView.findViewById(R.id.filterImgNone)
        val icon: ImageView = itemView.findViewById(R.id.filerIcon)
        val name: TextView = itemView.findViewById(R.id.filterName)

        init {
            itemView.setOnClickListener {
                onFilterSelected.onFilterSelected(
                    filterList[layoutPosition], filterCV, icon,image, layoutPosition
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
        return filterList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        val item = filterList[i]
        holder.name.text = item.name
        holder.filterCV.strokeWidth =  0
        holder.icon.visibility = View.GONE
        holder.filterImgNone.visibility = View.GONE
        holder.image.setColorFilter(ContextCompat.getColor(context, R.color.trans))

        if(item.id == Str.MODE_NONE){
            holder.filterImgNone.visibility = View.VISIBLE
            holder.image.setColorFilter(ContextCompat.getColor(context, R.color.trans),PorterDuff.Mode.SRC_IN)
        }else{
            holder.image.setColorFilter(ContextCompat.getColor(context, R.color.trans),PorterDuff.Mode.SRC_ATOP)
        }

    }

}