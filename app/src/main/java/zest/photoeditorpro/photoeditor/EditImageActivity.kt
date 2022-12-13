package zest.photoeditorpro.photoeditor

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import zest.photoeditorpro.photoeditor.util.Str
import zest.photoeditorpro.photoeditor.adapter.ToolsAdapter
import zest.photoeditorpro.photoeditor.data.Tools
import zest.photoeditorpro.photoeditor.data.getToolList
import zest.photoeditorpro.photoeditor.databinding.ActivityEditImageBinding
import zest.photoeditorpro.photoeditor.fragment.*

//https://thenounproject.com/icon/flip-2825113/

class EditImageActivity : AppCompatActivity(), ToolsAdapter.OnToolSelected {
    private val TAG: String = "EditImageActivity"

    var isToolSelected = false;
    var layoutPosition = 0;
    var recylerViewState: Parcelable? = null;
    private var toolsAdapter: ToolsAdapter? = null
    private lateinit var binding: ActivityEditImageBinding

    var mode: Int = Str.MODE_NONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolsAdapter = ToolsAdapter(this, getToolList(resources), this, isToolSelected, null)
        binding.rvTools.adapter = toolsAdapter
    }


    private fun reloadAdapter() {
        recylerViewState = binding.rvTools.getLayoutManager()!!.onSaveInstanceState()
        toolsAdapter =
            ToolsAdapter(this, getToolList(resources), this, isToolSelected, layoutPosition)
        binding.rvTools.adapter = toolsAdapter
        binding.rvTools.getLayoutManager()!!.onRestoreInstanceState(recylerViewState);
    }

    private fun loadFragment(fragment: Fragment, tag: String) {
        isToolSelected = true
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            setCustomAnimations(R.anim.fade_in_from_bottom, R.anim.fade_out)
            replace(R.id.subToolFL, fragment, tag)
        }
    }

    private fun removeFragment(fragment: Fragment) {
        isToolSelected = false
        supportFragmentManager.commit {
            remove(fragment)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onToolSelected(
        tools: Tools,
        name: TextView,
        image: ImageView,
        layoutPosition: Int
    ) {
        this.layoutPosition = layoutPosition
        when (tools.id) {
            Str.CROP -> {
                if (supportFragmentManager.findFragmentById(R.id.subToolFL) is CropFragment)
                    removeFragment(supportFragmentManager.findFragmentById(R.id.subToolFL) as CropFragment)
                else loadFragment(CropFragment(), Str.CROP.toString())
                reloadAdapter()
                return
            }
            Str.REMOVE -> {
                if (supportFragmentManager.findFragmentById(R.id.subToolFL) is RemoveFragment)
                    removeFragment(supportFragmentManager.findFragmentById(R.id.subToolFL) as RemoveFragment)
                else loadFragment(RemoveFragment(), Str.REMOVE.toString())
                editMode()
                return
            }
            Str.ENHANCE -> {
                if (supportFragmentManager.findFragmentById(R.id.subToolFL) is EnhanceFragment)
                    removeFragment(supportFragmentManager.findFragmentById(R.id.subToolFL) as EnhanceFragment)
                else loadFragment(EnhanceFragment(), Str.ENHANCE.toString())
                reloadAdapter()
                return
            }
            Str.FILTER -> {
                if (supportFragmentManager.findFragmentById(R.id.subToolFL) is FilterFragment)
                    removeFragment(supportFragmentManager.findFragmentById(R.id.subToolFL) as FilterFragment)
                else loadFragment(FilterFragment(), Str.FILTER.toString())
                editMode()
                return
            }
            Str.ADJUST -> {
                if (supportFragmentManager.findFragmentById(R.id.subToolFL) is AdjustFragment)
                    removeFragment(supportFragmentManager.findFragmentById(R.id.subToolFL) as AdjustFragment)
                else loadFragment(AdjustFragment(), Str.ADJUST.toString())
                editMode()
                return
            }
            Str.EFFECT -> {
                if (supportFragmentManager.findFragmentById(R.id.subToolFL) is EffectFragment)
                    removeFragment(supportFragmentManager.findFragmentById(R.id.subToolFL) as EffectFragment)
                else loadFragment(EffectFragment(), Str.EFFECT.toString())
                editMode()
                return
            }
        }
    }

    private fun editMode() {
        binding.rvTools.visibility = View.GONE
        binding.include.appBarSave.visibility = View.GONE

        binding.includesave.appBar.visibility = View.VISIBLE
    }

    private fun editModeBack() {
        binding.rvTools.visibility = View.VISIBLE
        binding.include.appBarSave.visibility = View.VISIBLE

        binding.includesave.appBar.visibility = View.GONE
    }

    override fun onBackPressed() {
        var fm: Fragment? = null;

        if (supportFragmentManager.fragments.size != 0)
            fm = supportFragmentManager.fragments[0]


        when (fm?.tag) {
            Str.CROP.toString() -> {
                removeFragment(fm as CropFragment)
                reloadAdapter()
                return
            }
            Str.REMOVE.toString() -> {
                removeFragment(fm as RemoveFragment)
                editModeBack()
                reloadAdapter()
                return
            }
            Str.ENHANCE.toString() -> {
                removeFragment(fm as EnhanceFragment)
                reloadAdapter()
                return
            }
            Str.FILTER.toString() -> {
                removeFragment(fm as FilterFragment)
                editModeBack()
                reloadAdapter()
                return
            }
            Str.ADJUST.toString() -> {
                removeFragment(fm as AdjustFragment)
                editModeBack()
                reloadAdapter()
                return
            }
            Str.EFFECT.toString() -> {
                removeFragment(fm as EffectFragment)
                editModeBack()
                reloadAdapter()
                return
            }
            else -> {
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setMessage("Are you sure!!")
                    .setCancelable(false)
                    .setPositiveButton("YES") { dialog, id -> finish() }
                    .setNegativeButton("NO") { dialog, id -> dialog.cancel() }

                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
            }
        }
    }


}