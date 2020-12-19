package com.example.brusha.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

// fortæller at her skal der checkes for en database klasse
public class Commercial {


    private Long commercialId;

    // attributter til oprettelse af objekt med constructor. her er der så relationer med
    String commercial;

    // mangler mange til mange relation til subscriptions " mange subscriptions kan have mange commercial

}