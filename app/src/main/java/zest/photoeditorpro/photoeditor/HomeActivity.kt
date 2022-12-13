package zest.photoeditorpro.photoeditor

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import zest.photoeditorpro.photoeditor.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.cardRemoveObject.setOnClickListener(this);
        binding.cardEdit.setOnClickListener(this);
        binding.cardCamera.setOnClickListener(this);

        startActivity(Intent(this, EditImageActivity::class.java))


    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.card_removeObject -> {
                if (ParentActivity().checkPermissionAndOpenGallery(this)) {

                }
            }
            R.id.card_edit -> {
                if (ParentActivity().checkPermissionAndOpenGallery(this)) {
                    startActivity(Intent(this, EditImageActivity::class.java))
                }
            }
            R.id.card_camera -> {
                if (ParentActivity().checkPermissionAndOpenGallery(this)) {

                }
            }
        }
    }


}