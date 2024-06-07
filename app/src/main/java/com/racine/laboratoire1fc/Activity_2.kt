package com.racine.laboratoire1fc

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.widget.Toast

class Activity_2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        // Récupérer les données passées par l'intent
        val ville = intent.getStringExtra("VILLE")
        val classe = intent.getStringExtra("CLASSE")
        val nbPassagers = intent.getStringExtra("NB_PASSAGERS")
        val total = intent.getIntExtra("TOTAL", 0)

        // Afficher les données dans les TextViews
        findViewById<TextView>(R.id.destination_value).text = ville
        findViewById<TextView>(R.id.classe_value).text = classe
        findViewById<TextView>(R.id.nb_passagers_value).text = nbPassagers
        findViewById<TextView>(R.id.total_value).text = "$ $total"

        Log.d("Activity_2", "Données reçues: Ville=$ville, Classe=$classe, Passagers=$nbPassagers, Total=$total")

        // Ajouter un click listener au bouton Retour pour afficher un Toast personnalisé et retourner à MainActivity
        findViewById<Button>(R.id.retour_button).setOnClickListener {
            showCustomToast()
            Log.d("Activity_2", "Bouton Retour cliqué")
            finish() // Ferme Activity_2 et retourne à MainActivity
        }
    }

    private fun showCustomToast() {
        // Crée un toast personnalisé
        val inflater: LayoutInflater = layoutInflater // Récupère le LayoutInflater du contexte actuel
        val layout = inflater.inflate(R.layout.dialog_layout, null) // Inflate le layout dialog_layout.xml

        // Modifier le texte du TextView dans le layout
        layout.findViewById<TextView>(R.id.edit_text).text = "Merci pour votre Réservation!!"

        // Crée et affiche le toast personnalisé
        val toast = Toast(applicationContext)
        toast.duration = Toast.LENGTH_LONG // Durée du toast
        toast.view = layout
        toast.setGravity(Gravity.TOP, 0, 0) // Positionne le toast au centre de l'écran
        toast.show()
    }
}
