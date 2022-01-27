package com.example.googlemapsuteq;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.googlemapsuteq.databinding.ActivityMapsBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private List<Constructor> constructor = new ArrayList<>();

    String GetUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getAllDataLocation();
    }

    private void getAllDataLocation() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        RetrofitGet retrofitGet = RetrofitBase.getRetrofit().create(RetrofitGet.class);
        Call<ListModelo> call = retrofitGet.getAllLocation();
        call.enqueue(new Callback<ListModelo>() {
            @Override
            public void onResponse(Call<ListModelo> call, Response<ListModelo> response) {
                progressDialog.dismiss();
                constructor = response.body().getLmData();
                initMarker(constructor);
            }

            @Override
            public void onFailure(Call<ListModelo> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MapsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initMarker(List<Constructor> listModelos) {
        for (int i=0; i<listModelos.size(); i++)
        {
            LatLng latLng = new LatLng(Double.parseDouble(listModelos.get(i).getLatitud()),
                    Double.parseDouble(listModelos.get(i).getLongitud()));

            Marker marker = mMap.addMarker(new MarkerOptions().position(latLng)
            .title(listModelos.get(i).getFacultad())
            .snippet(listModelos.get(i).getLogo()));

            Constructor info = new Constructor();
            info.setLogo(listModelos.get(i).getLogo());
            info.setFacultad(listModelos.get(i).getFacultad());
            info.setDecano(listModelos.get(i).getDecano());
            info.setUbicacion(listModelos.get(i).getUbicacion());

            marker.setTag(info);

            LatLng latLng1 = new LatLng(Double.parseDouble(listModelos.get(0).getLatitud()),
                    Double.parseDouble(listModelos.get(0).getLongitud()));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng1.latitude, latLng1.longitude), 17.5f));

            if (listModelos.size()!=0)
            {
                TestInfoWindowAdapter testInfoWindowAdapter = new TestInfoWindowAdapter(this);
                mMap.setInfoWindowAdapter(testInfoWindowAdapter);
            }
        }
    }

    private class TestInfoWindowAdapter implements GoogleMap.InfoWindowAdapter{
        private Context context;
        public TestInfoWindowAdapter(Context context)
        {
            this.context = context;
        }
        @Nullable
        @Override
        public View getInfoWindow(@NonNull Marker marker)
        {
            return null;
        }

        @Nullable
        @Override
        public View getInfoContents(@NonNull Marker marker)
        {
            String url = "";
            String facultad = "";
            String decano = "";
            String ubicacion="";
            View view = ((Activity)context).getLayoutInflater().inflate(R.layout.info_wa, null);

            ImageView img = view.findViewById(R.id.info_window_imagen);
            TextView fac = view.findViewById(R.id.info_window_facultad);
            TextView dec = view.findViewById(R.id.info_window_decano);
            TextView ubi = view.findViewById(R.id.info_window_ubicacion);

            Constructor infomodel = (Constructor) marker.getTag();
            url = infomodel.getLogo();
            facultad = infomodel.getFacultad();
            decano = infomodel.getDecano();
            ubicacion = infomodel.getUbicacion();

            Picasso.get()
                    .load(url)
                    .error(R.mipmap.ic_launcher)
                    .into(img, new MarkerCallBack(marker));

            fac.setText(facultad);
            dec.setText(decano);
            ubi.setText(ubicacion);

            return view;
        }
    }

    private class MarkerCallBack implements com.squareup.picasso.Callback {
        Marker marker = null;
        public MarkerCallBack(Marker marker) {
            this.marker = marker;
        }

        @Override
        public void onSuccess() {
            if(marker != null && marker.isInfoWindowShown()){
                marker.hideInfoWindow();
                marker.showInfoWindow();
            }
        }

        @Override
        public void onError(Exception e) {
            Log.e(getClass().getSimpleName(), "Error...");
        }
    }
}