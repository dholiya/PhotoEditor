package zest.photoeditorpro.photoeditor.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import zest.photoeditorpro.photoeditor.databinding.FragmentEnhanceBinding


class EnhanceFragment : BaseEditFragment() {
    private lateinit var binding: FragmentEnhanceBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEnhanceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onShow() {

    }

    override fun backToMain() {
    }
}