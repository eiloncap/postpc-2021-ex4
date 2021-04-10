package exercise.find.roots

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class RootResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root_result)

        var numberText: TextView = findViewById(R.id.numberText)
        var root1Text: TextView = findViewById(R.id.root1Text)
        var root2Text: TextView = findViewById(R.id.root2Text)

        numberText.text = intent.getLongExtra("original_number", 0).toString()
        root1Text.text = intent.getLongExtra("root1", 0).toString()
        root2Text.text = intent.getLongExtra("root2", 0).toString()
    }
}