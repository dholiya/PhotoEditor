package zest.photoeditorpro.photoeditor.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.slider.Slider
import zest.photoeditorpro.photoeditor.data.Tools
import zest.photoeditorpro.photoeditor.databinding.FragmentRemoveBinding
import zest.photoeditorpro.photoeditor.fragment.RemoveFragment.*


class RemoveFragment : BaseEditFragment(), Slider.OnSliderTouchListener, Slider.OnChangeListener  {

    private lateinit var binding: FragmentRemoveBinding;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRemoveBinding.inflate(inflater, container, false)
        binding.slider.addOnChangeListener(this);
        return binding.root
    }

    override fun onShow() {}
    override fun backToMain() {}
    override fun onStartTrackingTouch(slider: Slider) {}
    override fun onStopTrackingTouch(slider: Slider) {}

    override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
        binding.sizeDraw.requestLayout()
        binding.sizeDraw.layoutParams.height = value.toInt()
        binding.sliderText.text = value.toInt().toString()
    }


}