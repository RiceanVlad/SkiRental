package com.example.skirental.models

/**
 * Final product data class
 * @param firma brand
 * @param descriere description
 * @param imagine image
 * @param tip type of product
 */
data class ProdusFinal (
        var firma: String,
        var descriere: String,
        var imagine: String,
        var tip: String,
) {

}