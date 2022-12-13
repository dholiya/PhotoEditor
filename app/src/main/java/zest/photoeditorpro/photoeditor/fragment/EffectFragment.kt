package zest.photoeditorpro.photoeditor.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView
import com.google.android.material.slider.Slider
import zest.photoeditorpro.photoeditor.R
import zest.photoeditorpro.photoeditor.adapter.EffectAdapter
import zest.photoeditorpro.photoeditor.util.Str
import zest.photoeditorpro.photoeditor.adapter.FilterAdapter
import zest.photoeditorpro.photoeditor.data.Effect
import zest.photoeditorpro.photoeditor.data.Filters
import zest.photoeditorpro.photoeditor.data.getEffectList
import zest.photoeditorpro.photoeditor.data.getFilterList
import zest.photoeditorpro.photoeditor.databinding.FragmentEffectBinding
import zest.photoeditorpro.photoeditor.databinding.FragmentFilterBinding


class EffectFragment : BaseEditFragment(), EffectAdapter.OnEffectSelected, Slider.OnChangeListener{
    private var lastPosition: Int? = null
    private lateinit var binding: FragmentEffectBinding;
    var effectAdapter: EffectAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        effectAdapter = EffectAdapter(this, activity, getEffectList(resources))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEffectBinding.inflate(inflater, container, false)
        binding.rvEffect.adapter = effectAdapter;
        binding.slider.addOnChangeListener(this)

        return binding.root
    }

    override fun onShow() {}
    override fun backToMain() {}


    override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
        binding.sliderText.text = value.toString()
    }



    override fun onEffectSelected(
        effects: Effect,
        effectCV: MaterialCardView,
        icon: ImageView,
        image: ImageView,
        layoutPosition: Int,
    ) {
        if (lastPosition == layoutPosition) {
            if (layoutPosition != 0)
                if (binding.sliderLLC.visibility == View.VISIBLE)
                    binding.sliderLLC.visibility = View.GONE;
                else binding.sliderLLC.visibility = View.VISIBLE;

        } else {
            binding.sliderLLC.visibility = View.GONE;
            if (lastPosition != null) {
                binding.rvEffect.itemAnimator!!.changeDuration = 0
                binding.rvEffect.itemAnimator!!.addDuration = 0
                binding.rvEffect.itemAnimator!!.removeDuration = 0

                binding.rvEffect.adapter!!.notifyItemChanged(lastPosition!!);
            }
            if (layoutPosition != 0) {
                icon.visibility = View.VISIBLE
                image.setColorFilter(ContextCompat.getColor(activity, R.color.grayTransExtralite))
            }
            binding.slider.value = 50F;
            effectCV.strokeWidth = resources.getDimensionPixelSize(R.dimen.strokeWidth)
            lastPosition = layoutPosition;
        }

        when (effects.id) {

            Str.GOLD -> {

            }
            Str.HDR -> {

            }
            Str.BNW -> {

            }

        }
    }
}