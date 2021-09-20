package com.example.calculatrice

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //variable pour stocker le signe
    private var operateur: String = ""

    //variable pour stocker la premiere operande
    private var op1: String = ""

    //variable pour stocker la deuxieme operande
    private var op2: String = ""

    //variable contenant toute l'operation
    private var operation: String = ""
    //preferences partagées
    //private var old_data : SharedPreferences = d


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    //Lecture des données sauvegarder
        val old_data = this.getSharedPreferences("old_data",0)
         if(old_data != null){
             op1 = old_data.getString("op1","").toString()
             op2 = old_data.getString("op2","").toString()
             operateur = old_data.getString("operateur","").toString()
             operation = old_data.getString("operation","").toString()
         }

        val ecran: TextView = findViewById(R.id.ecran)
        val b0: Button = findViewById(R.id.btn0)
        val b1: Button = findViewById(R.id.btn1)
        val b2 = findViewById<Button>(R.id.btn2)
        val b3 = findViewById<Button>(R.id.btn3)
        val b4 = findViewById<Button>(R.id.btn4)
        val b5 = findViewById<Button>(R.id.btn5)
        val b6 = findViewById<Button>(R.id.btn6)
        val b7 = findViewById<Button>(R.id.btn7)
        val b8 = findViewById<Button>(R.id.btn8)
        val b9 = findViewById<Button>(R.id.btn9)
        val badd = findViewById<Button>(R.id.btnAdd)
        val bminus = findViewById<Button>(R.id.btnMinus)
        val bmulti = findViewById<Button>(R.id.btnMulti)
        val bdivis = findViewById<Button>(R.id.btnDivision)
        val bmodulo = findViewById<Button>(R.id.btnModulo)
        val breset = findViewById<Button>(R.id.btnReset)
        val bdel = findViewById<Button>(R.id.btnDel)
        val bvirgule = findViewById<Button>(R.id.btnComa)
        val bsigne = findViewById<Button>(R.id.btnSigne)
        val bresult = findViewById<Button>(R.id.btnResult)

        ecran.text = operation
        // Ecouteur du bouton 0
        b0.setOnClickListener {
            btn("0")
            ecran.text = operation
        }

        // Ecouteur du bouton 1
        b1.setOnClickListener {
            btn("1")
            ecran.text = operation
        }

        // Ecouteur du bouton 2
        b2.setOnClickListener {
            btn("2")
            ecran.text = operation
        }

        // Ecouteur du bouton 3
        b3.setOnClickListener {
            btn("3")
            ecran.text = operation
        }

        // Ecouteur du bouton 4
        b4.setOnClickListener {
            btn("4")
            ecran.text = operation
        }

        // Ecouteur du bouton 5
        b5.setOnClickListener {
            btn("5")
            ecran.text = operation
        }

        // Ecouteur du bouton 6
        b6.setOnClickListener {
            btn("6")
            ecran.text = operation
        }

        // Ecouteur du bouton 7
        b7.setOnClickListener {
            btn("7")
            ecran.text = operation
        }

        // Ecouteur du bouton 8
        b8.setOnClickListener {
            btn("8")
            ecran.text = operation
        }

        // Ecouteur du bouton 9
        b9.setOnClickListener {
            btn("9")
            ecran.text = operation
        }

        // Ecouteur du bouton Plus
        badd.setOnClickListener {
            signe("+")
            ecran.text = operation
        }

        // Ecouteur du bouton Moins
        bminus.setOnClickListener {
            signe("-")
            ecran.text = operation
        }

        // Ecouteur du bouton multiplication
        bmulti.setOnClickListener {
            signe("*")
            ecran.text = operation
        }

        // Ecouteur du bouton Division
        bdivis.setOnClickListener {
            signe("/")
            ecran.text = operation
        }

        // Ecouteur du bouton modulo
        bmodulo.setOnClickListener {
            signe("%")
            ecran.text = operation
        }

        // Ecouteur du bouton reset
        breset.setOnClickListener {
            operateur = ""
            op1 = ""
            op2 = ""
            operation = ""
            ecran.text = operation
        }

        // Ecouteur du bouton delete
        bdel.setOnClickListener {
            delete()
            ecran.text = operation
        }

        // Ecouteur du bouton virgule
        bvirgule.setOnClickListener {
            btn(".")
            ecran.text = operation
        }

        // Ecouteur du bouton signe
        bsigne.setOnClickListener {
            changer()
            ecran.text = operation
        }

        // Ecouteur du bouton resultat
        bresult.setOnClickListener {
            calcul()
            ecran.text = operation
            operation = ""
        }
    }


    //methode pour la valeur des boutons
    private fun btn(valeur: String) {
        if (operateur.isEmpty()) {
            op1 += valeur
            operation += valeur
        } else {
            op2 += valeur
            operation += valeur
        }
    }

    //methode pour l'operateur
    private fun signe(operande: String) {
        if (operation.contains("+")
            || operation.contains("-")
            || operation.contains("*")
            || operation.contains("/") || operation.contains("mod")
        ) {

            calcul()
            op1 = operation
            operation += operande
            operateur = operande
        } else {
            operateur = operande
            operation += operande

        }

    }

    //methode pour supprimer le dernier element
    private fun delete() {
        if (operateur.isNotEmpty()) {
            if (op2.isEmpty()) {
                operateur = ""
                //operation = operation.replace(operateur,"")
                operation = operation.substring(0, operation.length - 1)
            } else {
                op2 = op2.substring(0, op2.length - 1)
                operation = operation.substring(0, operation.length - 1)
            }
        } else {
            op1 = op1.substring(0, op1.length - 1)
            operation = operation.substring(0, operation.length - 1)
        }
    }

    //methode pour changer de signe
    private fun changer() {
        if (operateur.isNotEmpty()) {
            op2 = "(-$op2)"
            operation = op1 + operateur + op2
        } else {
            op1 = "-$op1"
            operation = op1 + operateur + op2
        }
    }

    //methode pour la valeur des calculs
    private fun calcul() {
        val res: Double
        when (operateur) {
            "+" -> {
                res = op1.toDouble() + op2.toDouble()
                operation = res.toString()
                op1 = ""
                op2 = ""
                operateur = ""
            }
            "-" -> {
                res = op1.toDouble() - op2.toDouble()
                operation = res.toString()
                op1 = ""
                op2 = ""
                operateur = ""
            }
            "*" -> {
                res = op1.toDouble() * op2.toDouble()
                operation = res.toString()
                op1 = ""
                op2 = ""
                operateur = ""
            }
            "/" -> {
                if (op2.toDouble() != 0.0) {
                    res = op1.toDouble() / op2.toDouble()
                    operation = res.toString()
                    op1 = ""
                    op2 = ""
                    operateur = ""
                } else {
                    op1 = ""
                    op2 = ""
                    operateur = ""
                    operation = "Erreur arithmetic"
                }

            }
            "%" -> {
                res = op1.toDouble() % op2.toDouble()
                operation = res.toString()
                op1 = ""
                op2 = ""
                operateur = ""
            }
        }
    }

    override fun onStop(){
        super.onStop()
        //creation et ecriture des données partagées
        val old_operation = this.getSharedPreferences("old_data",0)
        val editor = old_operation.edit()
        editor.putString("op1",op1)
        editor.putString("op2",op1)
        editor.putString("operateur",operateur)
        editor.putString("operation",operation)
        editor.apply()
    }
}