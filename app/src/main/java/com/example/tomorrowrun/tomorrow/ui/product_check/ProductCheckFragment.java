package com.example.tomorrowrun.tomorrow.ui.product_check;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tomorrow.databinding.FragmentProductCheckBinding;

public class ProductCheckFragment extends Fragment {
    private  FragmentProductCheckBinding binding;
    Button btn123;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ProductCheckViewModel productCheckViewModel =
                new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory() ).get(ProductCheckViewModel.class);

        binding = FragmentProductCheckBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textView7;
        productCheckViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    }


