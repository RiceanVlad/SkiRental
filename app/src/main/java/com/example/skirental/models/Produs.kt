package com.example.skirental.models

import java.io.Serializable

/**
 * Product data class
 * @param firma brand
 * @param descriere description
 * @param imagine image
 */
data class Produs (
        var firma: String,
        var descriere: String,
        var imagine: String,
) : Serializable{

}