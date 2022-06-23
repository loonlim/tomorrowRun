package com.example.tomorrow.ui.customerinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tomorrow.databinding.FragmentCustomerInformationBinding;

public class CustomerInfomationFragment extends Fragment {

        private FragmentCustomerInformationBinding binding;
    private TextView textViewId;
    private TextView textViewUsername;
    private TextView textViewEmail;
    private TextView textViewfullname;

    public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            CustomerInfomationViewModel customerInfomationViewModel = new ViewModelProvider( this,
                    (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory() ).get( CustomerInfomationViewModel.class );

            binding = FragmentCustomerInformationBinding.inflate(inflater, container, false);
            View root = binding.getRoot();

            final TextView textView = binding.customerinfopage;
            customerInfomationViewModel.getText().observe( getViewLifecycleOwner(), textView::setText);
            return root;

        }


}