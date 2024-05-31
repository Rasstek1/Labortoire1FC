package com.racine.laboratoire1fc

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import android.widget.EditText
import com.racine.laboratoire1fc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(
                left = systemBars.left,
                top = systemBars.top,
                right = systemBars.right,
                bottom = systemBars.bottom
            )
            insets
        }

        // Initialisation des Spinners
        val spinnerVille: Spinner = findViewById(R.id.ville)
        val spinnerClasse: Spinner = findViewById(R.id.classe_type)
        val nbPassagers: EditText = findViewById(R.id.nb_passenger)

        // Creation des adaptateurs pour les Spinners
        val villesAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.ville_array,
            android.R.layout.simple_spinner_item
        )
        villesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val classesAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.classe_array,
            android.R.layout.simple_spinner_item
        )
        classesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Affectation des adaptateurs aux Spinners
        spinnerVille.adapter = villesAdapter
        spinnerClasse.adapter = classesAdapter

        // Definir l'action du bouton pour ouvrir Activity_2 et passer les donnees
        binding.saveButton.setOnClickListener {
            val selectedVille = spinnerVille.selectedItem.toString()// Recuperer la valeur selectionnee dans le Spinner
            val selectedClasse = spinnerClasse.selectedItem.toString()// Recuperer la valeur selectionnee dans le Spinner
            val nbPassagersText = nbPassagers.text.toString()   // Recuperer le texte de l'EditText


            // Creer un intent pour lancer Activity_2 et passer les donnees. Dans le fond cest
            //un package de donnees qui est envoye a une autre activite via un intent
            val intent = Intent(this, Activity_2::class.java).apply {
                putExtra("VILLE", selectedVille)
                putExtra("CLASSE", selectedClasse)
                putExtra("NB_PASSAGERS", nbPassagersText)

            }


            startActivity(intent)
        }
    }


    private fun enableEdgeToEdge() {

    }
}