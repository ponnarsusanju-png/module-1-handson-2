package com.example.module1handson2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.module1handson2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnProcess.setOnClickListener {
            runAgent()
        }

        binding.btnClear.setOnClickListener {
            binding.etUserInput.text.clear()

            binding.tvPerception.text = "Waiting for input..."
            binding.tvReasoning.text = "Waiting for reasoning..."
            binding.tvAction.text = "Waiting for action..."
            binding.tvResult.text = "Agent result will appear here."
        }
    }

    private fun runAgent() {

        val input = binding.etUserInput.text.toString().trim()

        if (input.isEmpty()) {
            binding.etUserInput.error = "Please enter a message"
            return
        }

        // -------------------------
        // 1. PERCEPTION
        // -------------------------
        val perception = input.lowercase()

        binding.tvPerception.text =
            "Input detected:\n\"$input\""

        // -------------------------
        // 2. REASONING
        // -------------------------
        val reasoning: String
        val action: String
        val result: String

        when {
            perception.contains("hello") ||
                    perception.contains("hi") -> {

                reasoning = "The input is recognized as a greeting."
                action = "Respond with a friendly greeting."
                result = "Hello! 👋 How can I help you?"
            }

            perception.contains("time") -> {

                reasoning = "The user is asking for the current time."
                action = "Read the device system time."
                result = "Current time: ${java.text.SimpleDateFormat(
                    "hh:mm a",
                    java.util.Locale.getDefault()
                ).format(java.util.Date())}"
            }

            perception.contains("study") ||
                    perception.contains("learn") -> {

                reasoning = "The input is related to learning or studying."
                action = "Provide a study recommendation."
                result = "Try studying for 45 minutes and then take a 10-minute break. 📚"
            }

            perception.contains("weather") -> {

                reasoning = "The input contains a weather-related request."
                action = "Explain that this demo does not access live weather data."
                result = "This rule-based agent cannot access live weather data."
            }

            perception.contains("bye") ||
                    perception.contains("goodbye") -> {

                reasoning = "The user wants to end the conversation."
                action = "Send a farewell response."
                result = "Goodbye! 👋 Have a great day."
            }

            else -> {

                reasoning = "No predefined rule matched the user's input."
                action = "Return a fallback response."
                result = "I understood your input, but I don't have a rule for it yet."
            }
        }

        // -------------------------
        // DISPLAY REASONING
        // -------------------------

        binding.tvReasoning.text = reasoning
        binding.tvAction.text = action
        binding.tvResult.text = result
    }
}