package com.example.qual_o_mat.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.qual_o_mat.databinding.FragmentHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.example.qual_o_mat.Election;
import com.example.qual_o_mat.JsonHelper;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private static final String TAG = "HomeFragment";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTitle;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Load JSON from the assets folder
        String jsonString = JsonHelper.loadJSONFromAsset(getContext(), "qual-o-mat-data/election.json");

        // Parse the JSON
        List<Election> electionList = parseJsonData(jsonString);

        // Display the data (for simplicity, we display the first election's name)
        if (!electionList.isEmpty()) {
            Election lastelection = electionList.get(electionList.size()-1);
            textView.setText(lastelection.getName());
        }

        return root;
    }

    private List<Election> parseJsonData(String jsonString) {
        List<Election> electionList = new ArrayList<>();
        try {
            // Assuming the JSON root is an array
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                // Extract data from the JSON object
                int id = obj.getInt("id");
                String name = obj.getString("name");
                String date = obj.getString("date");
                String path = obj.getString("path");

                // Create a new data model object and add it to the list
                Election election = new Election(id, name, date, path);
                electionList.add(election);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return electionList;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
