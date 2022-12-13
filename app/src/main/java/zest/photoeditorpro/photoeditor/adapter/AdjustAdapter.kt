package zest.photoeditorpro.photoeditor.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import zest.photoeditorpro.photoeditor.R
import zest.photoeditorpro.photoeditor.data.Adjust

class AdjustAdapter(
    private val onAdjustSelected: OnAdjustSelected, var context: Context,
    val adjustList: List<Adjust>,
) :
    RecyclerView.Adapter<AdjustAdapter.ViewHolder>() {


    interface OnAdjustSelected {
        fun onAdjustSelected(adjust: Adjust, layoutPosition: Int, image: ImageView, name: TextView)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val name: TextView = itemView.findViewById(R.id.name)

        init {

            itemView.setOnClickListener {
                onAdjustSelected.onAdjustSelected(
                    adjustList[layoutPosition], layoutPosition, image, name
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
        return adjustList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        val item = adjustList[i]
        holder.image.setImageResource(item.image)
        holder.name.text = item.name

        holder.image.setColorFilter(ContextCompat.getColor(context, R.color.disabletrans))
        holder.name.setTextColor(ContextCompat.getColor(context, R.color.disabletrans))

    }

}