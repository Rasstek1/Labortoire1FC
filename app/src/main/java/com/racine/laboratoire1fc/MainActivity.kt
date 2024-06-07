package com.racine.laboratoire1fc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
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

        // Définir l'action du bouton pour ouvrir Activity_2 et passer les données
        binding.saveButton.setOnClickListener {
            val selectedVille = spinnerVille.selectedItem.toString()
            val selectedClasse = spinnerClasse.selectedItem.toString()
            val nbPassagersText = nbPassagers.text.toString()

            // Validation des champs
            if (selectedVille.isEmpty() || selectedClasse.isEmpty() || nbPassagersText.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                Log.d("MainActivity", "Validation échouée: Un ou plusieurs champs sont vides")
                return@setOnClickListener
            }

            val nbPassagersInt = nbPassagersText.toIntOrNull()
            if (nbPassagersInt == null || nbPassagersInt <= 0) {
                Toast.makeText(this, "Veuillez entrer un nombre de passagers valide", Toast.LENGTH_SHORT).show()
                Log.d("MainActivity", "Validation échouée: Nombre de passagers invalide")
                return@setOnClickListener
            }

            // Calculer le total (exemple: 250$ par passager)
            val total = nbPassagersInt * 250

            // Créer un intent pour lancer Activity_2 et passer les données
            val intent = Intent(this, Activity_2::class.java).apply {
                putExtra("VILLE", selectedVille)
                putExtra("CLASSE", selectedClasse)
                putExtra("NB_PASSAGERS", nbPassagersText)
                putExtra("TOTAL", total)
            }

            Log.d("MainActivity", "Données envoyées à Activity_2: Ville=$selectedVille, Classe=$selectedClasse, Passagers=$nbPassagersText, Total=$total")

            startActivity(intent)
        }
    }

    private fun enableEdgeToEdge() {
        // Peut rester vide
    }
}
