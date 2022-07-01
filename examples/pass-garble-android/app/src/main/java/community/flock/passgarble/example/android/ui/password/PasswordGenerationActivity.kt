package community.flock.passgarble.example.android.ui.password

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import community.flock.passgarble.common.CommonPasswordGenerator
import community.flock.passgarble.example.android.databinding.ActivityLoginBinding
import kotlinx.coroutines.runBlocking

class PasswordGenerationActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val generatePasswordAction = binding.actionGeneratePassword
        val generatedPasswordField: TextView = binding.generatedPassword as TextView
        generatedPasswordField.setOnClickListener {
            Log.d(LOG_TAG, "Clicked on generated password");
        }

        generatePasswordAction.setOnClickListener {
            Log.d(LOG_TAG, "Clicked on Generate password button");

            val passLength = binding.passwordLength.text?.toString()?.toInt() ?: 25
            val checkedLowercase = binding.checkboxLowercase?.isChecked ?: true
            val checkedUppercase = binding.checkboxUppercase?.isChecked ?: true
            val checkedNumbers = binding.checkboxNumbers?.isChecked ?: true
            val checkedSpecialChars = binding.checkboxSpecialChars?.isChecked ?: true

            Log.i(
                LOG_TAG,
                "Passlength: $passLength, " +
                        "lowercase: $checkedLowercase, " +
                        "upper: $checkedUppercase, " +
                        "numbers: $checkedNumbers, " +
                        "special: $checkedSpecialChars"
            )

            val generatedPassword = runBlocking {
                CommonPasswordGenerator.generate(
                    passwordLength = passLength,
                    includeLowerCase = checkedLowercase,
                    includeUpperCase = checkedUppercase,
                    includeNumbers = checkedNumbers,
                    includeSpecialChars = checkedSpecialChars
                )
            }
            generatedPasswordField.text = generatedPassword

        }
    }

    companion object {
        private const val LOG_TAG = "TAG"
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}