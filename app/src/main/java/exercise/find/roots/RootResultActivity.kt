package exercise.find.roots

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RootResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root_result)

        val numberText: TextView = findViewById(R.id.numberText)
        val root1Text: TextView = findViewById(R.id.root1Text)
        val root2Text: TextView = findViewById(R.id.root2Text)
        val calculationTimeText: TextView = findViewById(R.id.calculationTimeText)

        // display results
        numberText.text = intent.getLongExtra("original_number", 0).toString()
        root1Text.text = intent.getLongExtra("root1", 0).toString()
        root2Text.text = intent.getLongExtra("root2", 0).toString()
        calculationTimeText.text = "calculation took ${intent.getLongExtra("calculations_time", 0)} seconds"
    }
}