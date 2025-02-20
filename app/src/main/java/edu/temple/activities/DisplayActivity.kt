package edu.temple.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts

class DisplayActivity : AppCompatActivity() {

    // TODO Step 1: Launch TextSizeActivity when button clicked to allow selection of text size value

    // TODO Step 3: Use returned value for lyricsDisplayTextView text size

  private lateinit var lyricsDisplayTextView: TextView
  private lateinit var textSizeSelectorButton: Button

  // Register for activity result to receive the selected text size from TextSizeActivity
  private val textSizeLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
    if (result.resultCode == RESULT_OK) {
      // Get the selected text size from the result Intent
      val selectedSize = result.data?.getIntExtra("selectedSize", -1)
      if (selectedSize != -1) {
        // Step 3: Update the text size of lyricsDisplayTextView with the selected value
        lyricsDisplayTextView.textSize = selectedSize?.toFloat() ?: 14f
      }
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_display)

    // Initialize the TextView and Button by finding them by their ID in the layout
    lyricsDisplayTextView = findViewById(R.id.lyricsDisplayTextView)
    textSizeSelectorButton = findViewById(R.id.textSizeSelectorButton)

    // Set up the button's click listener to launch TextSizeActivity for a result
    textSizeSelectorButton.setOnClickListener {
      // Create an Intent to launch TextSizeActivity
      val intent = Intent(this, TextSizeActivity::class.java)

      // Step 1: Launch TextSizeActivity expecting a result
      textSizeLauncher.launch(intent)
    }
  }
}
