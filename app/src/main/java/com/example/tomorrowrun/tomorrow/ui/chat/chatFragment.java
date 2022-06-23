package com.example.tomorrowrun.tomorrow.ui.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tomorrow.databinding.FragmentChatBinding;

public class chatFragment extends Fragment {


    private FragmentChatBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        chatModel chatModel =
                new ViewModelProvider( this, (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory() ).
                        get(chatModel.class);

        binding = FragmentChatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.chatpage;
        chatModel.getText().observe( getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}