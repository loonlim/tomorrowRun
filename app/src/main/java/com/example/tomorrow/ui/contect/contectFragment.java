package com.example.tomorrow.ui.contect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tomorrow.databinding.FragmentContectBinding;

public class contectFragment extends Fragment {

    private FragmentContectBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contectModel contectModelModel =
                new ViewModelProvider( this, (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory() ).get(contectModel.class);

        binding = FragmentContectBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.chontectpage;
        contectModelModel.getText().observe( getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}