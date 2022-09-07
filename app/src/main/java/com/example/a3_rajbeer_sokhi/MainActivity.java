package com.example.a3_rajbeer_sokhi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.a3_rajbeer_sokhi.adapters.CharacterAdapter;
import com.example.a3_rajbeer_sokhi.databinding.ActivityMainBinding;
import com.example.a3_rajbeer_sokhi.models.CharacterResponseObject;
import com.example.a3_rajbeer_sokhi.models.ZeldaCharacters;
import com.example.a3_rajbeer_sokhi.network.API;
import com.example.a3_rajbeer_sokhi.network.RetrofitClient;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<ZeldaCharacters> itemList = new ArrayList<>();
    private CharacterAdapter adapter;
    private List<ZeldaCharacters> charactersFromAPI = new ArrayList<>();
    private List<String> listOfLocations = new ArrayList<>();

    private API api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new CharacterAdapter(this,itemList);
        binding.rvItems.setAdapter(adapter);
        binding.rvItems.setLayoutManager(new LinearLayoutManager(this));

        listOfLocations.add("Choose a location");
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,listOfLocations);

        binding.spLocation.setAdapter(locationAdapter);

        this.api = RetrofitClient.getInstance().getApi();

        Call<CharacterResponseObject> request = api.getAllData();
        request.enqueue(new Callback<CharacterResponseObject>() {
            @Override
            public void onResponse(Call<CharacterResponseObject> call, Response<CharacterResponseObject> response) {
                if(response.isSuccessful() == false){
                    Log.d("ABC","Error from API: " +response.code());
                    return;
                }
                CharacterResponseObject obj = response.body();
                charactersFromAPI = obj.getData();


                for(int i = 0;i<charactersFromAPI.size();i++){
                    if(charactersFromAPI.get(i).getLocation() != null) {
                        if(charactersFromAPI.get(i).getLocation().size() != 0) {
                            for(int j = 0;j<charactersFromAPI.get(i).getLocation().size();j++) {
                                if(listOfLocations.contains(charactersFromAPI.get(i).getLocation().get(j)) == false)
                                listOfLocations.add(charactersFromAPI.get(i).getLocation().get(j));
                            }
                        }
                    }
                }

                locationAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<CharacterResponseObject> call, Throwable t) {
                Log.d("ABC",t.getMessage());
            }
        });


        binding.spLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0) {// Is equal to "choose a location"
                    itemList.clear();
                }
                else {
                    isFromLocation(listOfLocations.get(i + 1));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameFromUI = binding.etName.getText().toString();
                if(nameFromUI.isEmpty()){
                    itemList.clear();
                }
                else {
                    isNamed(nameFromUI);
                }
                adapter.notifyDataSetChanged();
            }
        });



    }

    public void isNamed(String name){
        itemList.clear();
        for(int i = 0; i<charactersFromAPI.size();i++){
            if(charactersFromAPI.get(i).getName().contains(name.toLowerCase())){
                itemList.add(charactersFromAPI.get(i));
            }
        }
    }

    public void isFromLocation(String location){
        itemList.clear();
        for(int i = 0;i < charactersFromAPI.size();i++){
            if(charactersFromAPI.get(i).getLocation() != null) {
                if (charactersFromAPI.get(i).getLocation().contains(location)) {
                    itemList.add(charactersFromAPI.get(i));
                }
            }
        }
    }
}