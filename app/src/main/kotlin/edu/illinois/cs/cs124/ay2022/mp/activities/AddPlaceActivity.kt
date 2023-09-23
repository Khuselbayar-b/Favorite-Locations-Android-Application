package edu.illinois.cs.cs124.ay2022.mp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import edu.illinois.cs.cs124.ay2022.mp.R
import edu.illinois.cs.cs124.ay2022.mp.models.Place
import edu.illinois.cs.cs124.ay2022.mp.models.ResultMightThrow
import edu.illinois.cs.cs124.ay2022.mp.network.Client
import java.util.function.Consumer

//import org.w3c.dom.Text
//import java.util.function.Consumer

private val TAG = AddPlaceActivity::class.java.name
class AddPlaceActivity : AppCompatActivity(),  Consumer<ResultMightThrow<Boolean>> {
    override fun accept(t: ResultMightThrow<Boolean>) {
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addplace)
        val returnMain = Intent(this, MainActivity::class.java)
        returnMain.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK + Intent.FLAG_ACTIVITY_NEW_TASK
        val cancelButton = findViewById<Button>(R.id.cancel_button)
        cancelButton.setOnClickListener {
            startActivity(returnMain)
        }
        val saveButton = findViewById<Button>(R.id.save_button)
        saveButton.setOnClickListener {
            val latitude = intent.getStringExtra("latitude")
            val longitude = intent.getStringExtra("longitude")
            val description = findViewById<EditText>(R.id.description).text.toString()
            val newPlace = Place(
                "cee8fe0f-e648-4aaf-87fd-21764f88f2a4",
                "KB",
                latitude!!.toDouble(),
                longitude!!.toDouble(),
                description
            )
            Log.d(TAG, latitude.toString())
            Log.d(TAG, newPlace.description)
            Client.postFavoritePlace(newPlace, this)
            startActivity(returnMain)
        }
    }
}
