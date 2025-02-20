package edu.temple.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TextSizeActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Create array of integers that are multiples of 5
    val textSizes = Array(20) { (it + 1) * 5 }

    Log.d("Array values", textSizes.contentToString())

    with(findViewById(R.id.textSizeSelectorRecyclerView) as RecyclerView) {

      // Step 2: Pass selected value back to the activity that launched TextSizeActivity
      adapter = TextSizeAdapter(textSizes) { selectedSize ->
        // Create an intent to pass the result back
        val resultIntent = Intent().apply {
          putExtra("selectedSize", selectedSize)
        }
        // Set the result and finish the activity
        setResult(RESULT_OK, resultIntent)
        finish()  // Close TextSizeActivity and return to DisplayActivity
      }
      layoutManager = LinearLayoutManager(this@TextSizeActivity)
    }
  }
}

/* RecyclerView Adapter */
class TextSizeAdapter(private val textSizes: Array<Int>, private val callback: (Int) -> Unit) : RecyclerView.Adapter<TextSizeAdapter.TextSizeViewHolder>() {

  inner class TextSizeViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView) {
    init {
      // Call the callback with the selected size when a TextView is clicked
      textView.setOnClickListener { callback(textSizes[adapterPosition]) }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextSizeViewHolder {
    return TextSizeViewHolder(TextView(parent.context).apply {
      setPadding(5, 20, 0, 20)
    })
  }

  override fun onBindViewHolder(holder: TextSizeViewHolder, position: Int) {
    holder.textView.apply {
      text = textSizes[position].toString()
      textSize = textSizes[position].toFloat()
    }
  }

  override fun getItemCount(): Int {
    return textSizes.size
  }
}








