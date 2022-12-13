package zest.photoeditorpro.photoeditor.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.coroutines.delay
import zest.photoeditorpro.photoeditor.R
import zest.photoeditorpro.photoeditor.adapter.AdjustAdapter
import zest.photoeditorpro.photoeditor.data.Adjust
import zest.photoeditorpro.photoeditor.data.getAdjustList
import zest.photoeditorpro.photoeditor.databinding.FragmentAdjustBinding
//https://developer.android.com/reference/androidx/constraintlayout/utils/widget/ImageFilterView#setCrossfade(float)
class AdjustFragment : BaseEditFragment(), AdjustAdapter.OnAdjustSelected {

    private var lastPosition: Int? = null
    var adjustAdapter: AdjustAdapter? = null;
    lateinit var binding: FragmentAdjustBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adjustAdapter = AdjustAdapter(this, activity, getAdjustList(resources))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAdjustBinding.inflate(inflater, container, false)
        binding.rvAdjust.adapter = adjustAdapter;
        Handler(Looper.getMainLooper()).postDelayed({
            binding.rvAdjust.findViewHolderForAdapterPosition(0)!!.itemView.performClick()
        }, 1)
        return binding.root
    }

    override fun onShow() {
    }

    override fun backToMain() {
    }

    override fun onAdjustSelected(
        adjust: Adjust,
        layoutPosition: Int,
        image: ImageView,
        name: TextView,
    ) {
        if (lastPosition != null) binding.rvAdjust.adapter!!.notifyItemChanged(lastPosition!!, null)
        image.setColorFilter(ContextCompat.getColor(activity, R.color.trans))
        name.setTextColor(ContextCompat.getColor(activity, R.color.white))
        lastPosition = layoutPosition;

    }

}