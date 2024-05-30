package com.example.qual_o_mat.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.qual_o_mat.databinding.FragmentAboutBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.noties.markwon.Markwon;

public class AboutFragment extends Fragment {

    private FragmentAboutBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AboutViewModel aboutViewModel = new ViewModelProvider(this).get(AboutViewModel.class);

        binding = FragmentAboutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAbout;

        String markdownContent = readAssetFile("README.md");
        aboutViewModel.setText(markdownContent);

        // Erstelle eine Markwon-Instanz
        Markwon markwon = Markwon.create(requireContext());

        // Beobachte die LiveData im ViewModel und aktualisiere den TextView mit gerendertem Markdown
        aboutViewModel.getText().observe(getViewLifecycleOwner(), text -> {
            // Render Markdown im TextView
            markwon.setMarkdown(textView, text);
        });

        return root;
    }

    private String readAssetFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getActivity().getAssets().open(fileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}