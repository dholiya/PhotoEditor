package zest.photoeditorpro.photoeditor.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import zest.photoeditorpro.photoeditor.EditImageActivity;

public abstract class BaseEditFragment extends Fragment {
    protected EditImageActivity activity;

    protected EditImageActivity ensureEditActivity() {
        if (activity == null) {
            activity = (EditImageActivity) getActivity();
        }
        return activity;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ensureEditActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        ensureEditActivity();
    }

    public abstract void onShow();

    public abstract void backToMain();
}
