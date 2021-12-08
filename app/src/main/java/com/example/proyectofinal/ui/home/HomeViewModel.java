package com.example.proyectofinal.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Universidad Tecnológica de Panamá \n"  +
                "Factultad de Ingeniería de Sistemas Computacionales\n" +
                "Lic. en Ingeniería de Sistemas y Computación\n\n" +
                "Proyecto Semestral - Sensores\n\n" +
                "Integrantes: \n" +
                "Aizprúa, Pamela \n" +
                "Castillo, Gilberto \n" +
                "Tack, Mark \n\n" +
                "Profesora: \n" +
                "Marlina Sanchez\n\n" +
                "II Semestre-2021\n");
    }

    public LiveData<String> getText() {
        return mText;
    }
}