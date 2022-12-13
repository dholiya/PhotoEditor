package zest.photoeditorpro.photoeditor.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import zest.photoeditorpro.photoeditor.adapter.CropAdapter
import zest.photoeditorpro.photoeditor.data.Crop
import zest.photoeditorpro.photoeditor.data.getCropList
import zest.photoeditorpro.photoeditor.databinding.FragmentCropBinding

class CropFragment : BaseEditFragment(), CropAdapter.OnCropSelected {

    var cropAdapter: CropAdapter? = null
    private lateinit var binding: FragmentCropBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cropAdapter = CropAdapter(this, getCropList(resources))

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCropBinding.inflate(inflater, container, false)
        binding.rvCrop.adapter = cropAdapter;
        return binding.root
    }

    override fun onShow() {
    }


    override fun backToMain() {

    }

    override fun onCropSelected(crop: Crop) {
        Snackbar.make(binding.root, crop.name, Snackbar.LENGTH_LONG).show()

    }

}