package com.example.tomorrow.ui.product_progress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tomorrow.databinding.FragmentProductProgressBinding;

public class ProductProgressFragment extends Fragment {

    private FragmentProductProgressBinding binding;

        public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProductProgressModel productProgressModel =
                new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory() ).get(ProductProgressModel.class);
        binding = FragmentProductProgressBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.productprogresstext;
        productProgressModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}