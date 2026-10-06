package com.example.agentworkflow

class AgentEngine {

    fun processInput(input: String): AgentResult {

        val text = input.trim().lowercase()

        // PERCEPTION
        val intent = when {
            text.contains("hello") ||
                    text.contains("hi") ||
                    text.contains("hey") -> "GREETING"

            text.contains("time") -> "TIME_REQUEST"

            text.contains("study") ||
                    text.contains("learn") -> "STUDY"

            text.contains("weather") -> "WEATHER"

            text.contains("help") -> "HELP"

            text.isEmpty() -> "EMPTY"

            else -> "UNKNOWN"
        }

        // REASONING + ACTION
        return when (intent) {

            "GREETING" -> AgentResult(
                intent,
                "Greeting detected",
                "Respond with a welcome message",
                "Hello! 👋 I'm your rule-based AI assistant."
            )

            "TIME_REQUEST" -> AgentResult(
                intent,
                "Time-related request detected",
                "Provide the current device time",
                "Current time: ${
                    java.text.SimpleDateFormat(
                        "hh:mm a",
                        java.util.Locale.getDefault()
                    ).format(java.util.Date())
                }"
            )

            "STUDY" -> AgentResult(
                intent,
                "Study-related request detected",
                "Provide a simple study suggestion",
                "Try a focused 25-minute study session, then take a 5-minute break. 📚"
            )

            "WEATHER" -> AgentResult(
                intent,
                "Weather request detected",
                "Explain that live weather requires an external service",
                "I can handle weather requests, but live weather data requires a weather API."
            )

            "HELP" -> AgentResult(
                intent,
                "Help request detected",
                "Display available capabilities",
                "Try asking about study, time, weather, or simply say hello."
            )

            "EMPTY" -> AgentResult(
                intent,
                "No input detected",
                "Ask the user for input",
                "Please enter a message first."
            )

            else -> AgentResult(
                intent,
                "No matching rule detected",
                "Return a fallback response",
                "I don't understand that yet. Try: 'help', 'study', 'time', or 'hello'."
            )
        }
    }
}

data class AgentResult(
    val intent: String,
    val perception: String,
    val reasoning: String,
    val action: String
)